package be.epsmarche.sgbd.pierre.springPierre.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.epsmarche.sgbd.pierre.springPierre.model.Article;
import be.epsmarche.sgbd.pierre.springPierre.model.Commande;
import be.epsmarche.sgbd.pierre.springPierre.model.ListeArticle;
import be.epsmarche.sgbd.pierre.springPierre.service.ArticleService;
import be.epsmarche.sgbd.pierre.springPierre.service.CommandeService;
import be.epsmarche.sgbd.pierre.springPierre.service.ListeArticleService;

@Controller
@RequestMapping(value = "/")
public class CommandeController {
	
	public CommandeController() {
		
	}
	
	@Autowired
	CommandeService cs;
	
	@Autowired
	ListeArticleService ls;
	
	@Autowired
	ArticleService as;

	/**
	 * Affichage de la liste de commandes
	 * @param model
	 * @return
	 */
	
	@GetMapping("commande")
	public String showAllCommandes(Model model) {
		
		//Mets la cloture de la commande à true si la date est dépassée
		
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		date = calendar.getTime();
		
		ArrayList<Commande> c = cs.getAllCommande();
		
		for(int i=0; i<c.size(); i++) {
			if(c.get(i).getDate().before(date))
				c.get(i).setCloture(true);
				cs.updateCommande(c.get(i));
		}
		
		model.addAttribute("commande", c);
		return "commande/commandes";
	}
	
	
	/**
	 * Affichage du formulaire d'ajout de commandes
	 * @param c
	 * @param model
	 * @return
	 */
	
	@GetMapping("commandeForm")
	public String showCommandeForm(Commande c, Model model) {
		return "commande/commande_add";
	}
	
	
	/**
	 * Ajout d'une commande et retour à la liste de commandes
	 * @param c
	 * @param result
	 * @param model
	 * @return
	 */
	
	@PostMapping("addCommande")
	public String addCommande(@Valid Commande c, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "commande/commande_add";
		}
		cs.addCommande(c);
		return "redirect:/commande";
	}
	
	
	/**
	 * Affichage du formulaire de modification de commande
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("editCommande/{id}")
	public String showUpdateCommande(@PathVariable("id") long id, Model model) {
		Commande c = cs.getCommandeById(id).get();
		model.addAttribute("commande", c);
		return "commande/commande_update";
	}
	
	
	/**
	 * Modification de la commande et retour à la liste
	 * @param id
	 * @param c
	 * @param result
	 * @param model
	 * @return
	 */
	
	@PostMapping("updateCommande/{id}")
	public String updateCommande(@PathVariable("id") long id, @Valid Commande c, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "commande/commande_update";
		}
		cs.updateCommande(c);
		return "redirect:/commande";
	}

	
	/**
	 * Supprimer la commande choisie
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("deleteCommande/{id}")
	public String deleteCommande(@PathVariable("id") long id, Model model) {
		Commande c = cs.getCommandeById(id).get();
		cs.deleteCommande(c);
		model.addAttribute("commande", cs.getAllCommande());
		return "redirect:/commande";
	}
	
	
	/**
	 * Affichage de la page de détail du commande avec les articles commandés et listeArticle
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("detailCommande/{id}")
	public String showCommandeById(@PathVariable("id") long id, Model model) {
		ArrayList<ListeArticle> la = ls.getAllListeArticleByIdCmd(id);
		
		model.addAttribute("commande", cs.getCommandeById(id).get());
		model.addAttribute("listeArticle", la);
		
		//Calcul du prix total
		
		float prixTotal = 0;
		for(int i=0; i!=la.size(); i++) {
			prixTotal += la.get(i).getPrixactuel();
		}
		model.addAttribute("prixTotal", prixTotal);
		
		return "commande/commande_detail";
	}
	
	
	/**
	 * Affichage des articles à commander dans une commande
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("editCmdArt/{id}")
	public String showCmdArt(@PathVariable("id") long id, Model model) {
		model.addAttribute("commande", cs.getCommandeById(id).get());
		model.addAttribute("article", as.getAllActifArticle()); // Affiche seulement les articles actifs
		return "commande/commande_select_art";
	}
	
	
	/**
	 * Affichage du formulaire d'ajout d'une quantité pour un article
	 * @param idArt
	 * @param idCmd
	 * @param model
	 * @param la
	 * @return
	 */
	
	@GetMapping("editCmdArtForm")
	public String showCmdArtForm(@RequestParam(defaultValue="empty") long idArt, @RequestParam(defaultValue="empty") long idCmd,
								Model model, ListeArticle la) {
		Article a = as.getArticleById(idArt).get(); 
		Commande c = cs.getCommandeById(idCmd).get();
		
		la.setArticle(a);
		la.setCommande(c);
		la.setQt(1);
		
		return "commande/commande_selectQtArticle";
	}
	
	
	/**
	 * Ajout d'une quantité + création d'une listeArticle puis retour au détail de la commande
	 * @param la
	 * @param result
	 * @param model
	 * @return
	 */
	
	@PostMapping("addListeArticle")
	public String addListeArticle(@Valid ListeArticle la, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "commande/commande_selectQtArticle";
		}
		
		//Récupérer le prixActuel = prix de l'article * quantité choisie
		
		long idArt = la.getArticle().getId();
		Article a = as.getArticleById(idArt).get();
		la.setPrixactuel(a.getPrix() * la.getQt());
		
		ls.addListeArticle(la);
		
		return "redirect:/detailCommande/" + la.getCommande().getId();
	}

	
	/**
	 * Supprimer un article dans une commande
	 * @param idLA
	 * @param model
	 * @return
	 */
	@GetMapping("deleteListeArticle/{id}")
	public String deleteArticleCmd(@PathVariable("id") long idLA, Model model) {
		
		ListeArticle la = ls.getListeArticleById(idLA).get();
		ls.deleteListeArticle(la);
		
		return "redirect:/detailCommande/" + la.getCommande().getId();
	}
}

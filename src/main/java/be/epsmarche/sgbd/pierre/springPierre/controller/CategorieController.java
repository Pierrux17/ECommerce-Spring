package be.epsmarche.sgbd.pierre.springPierre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.epsmarche.sgbd.pierre.springPierre.model.Categorie;
import be.epsmarche.sgbd.pierre.springPierre.service.CategorieService;

@Controller
@RequestMapping(value = "/")
public class CategorieController {
	
	public CategorieController() {
		
	}
	
	@Autowired
	CategorieService cs;
	
	/**
	 * Affichage de la liste des catégories
	 * @param model
	 * @return
	 */
	
	@GetMapping("categorie")
	public String showAllCategories(Model model) {
		model.addAttribute("categorie", cs.getAllCategorie());
		return "categorie/categories";
	}
	
	
	/**
	 * Affichage du formulaire d'ajout d'une catégorie
	 * @param c
	 * @param model
	 * @return
	 */
	
	@GetMapping("categorieForm")
	public String showCategorieForm(Categorie c, Model model) {
		return "categorie/categorie_add";
	}
	
	/**
	 * Ajout d'une catégorie et retour à la liste des catégories
	 * @param c
	 * @param result
	 * @param model
	 * @return
	 */
	
	@PostMapping("addCategorie")
	public String addCategorie(@Valid Categorie c, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "categorie/categorie_add";
		}
		cs.addCategorie(c);
		return "redirect:/categorie";
	}
	
	
	/**
	 * Affichage du formulaire de modification d'une catégorie
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("editCategorie/{id}")
	public String showUpdateCategorie(@PathVariable("id") long id, Model model) {
		Categorie c = cs.getCategorieById(id).get();
		model.addAttribute("categorie", c);
		return "categorie/categorie_update";
	}
	
	
	/**
	 * Modification de la catégorie et retour à la liste
	 * @param id
	 * @param c
	 * @param result
	 * @param model
	 * @return
	 */
	
	@PostMapping("updateCategorie/{id}")
	public String updateCategorie(@PathVariable("id") long id, @Valid Categorie c, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "categorie/categorie_update";
		}
		cs.updateCategorie(c);
		return "redirect:/categorie";
	}

	
	/**
	 * Supprimer la catégorie choisie
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("deleteCategorie/{id}")
	public String deleteCategorie(@PathVariable("id") long id, Model model) {
		Categorie c = cs.getCategorieById(id).get();
		
		try {
			cs.deleteCategorie(c);
			model.addAttribute("categorie", cs.getAllCategorie());			
		} catch(Exception e) {
			return "erreur/categorie_delete";
		}
		return "redirect:/categorie";
	}
	
}

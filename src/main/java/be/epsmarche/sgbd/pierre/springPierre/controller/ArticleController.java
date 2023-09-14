package be.epsmarche.sgbd.pierre.springPierre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.epsmarche.sgbd.pierre.springPierre.model.Article;
import be.epsmarche.sgbd.pierre.springPierre.service.ArticleService;
import be.epsmarche.sgbd.pierre.springPierre.service.CategorieService;

import java.util.ArrayList;

import javax.validation.Valid;

@Controller
//@RequestMapping(value = "/springPierre/")
@RequestMapping(value = "/")
public class ArticleController {
	
	public ArticleController() {
		
	}
	
	@Autowired
	ArticleService as;
	
	@Autowired
	CategorieService cs;
	
	/**
	 * Affichage de la liste d'articles
	 * @param model
	 * @return
	 */
	
	@GetMapping("article")
	public String showAllArticles(Model model) {
		model.addAttribute("article", as.getAllArticle());
		return "article/articles";
	}
	
	
	/**
	 * Affichage du formulaire d'ajout d'articles
	 * @param a
	 * @param model
	 * @return
	 */
	
	@GetMapping("articleForm")
	public String showArticleForm(Article a, Model model) {
		  model.addAttribute("categorie", cs.getAllCategorie());
		return "article/article_add";
	}
	
	/**
	 * Ajout d'un article et retour à la liste d'articles
	 * @param a
	 * @param result
	 * @param model
	 * @return
	 */
	
	@PostMapping("addArticle")
	public String addArticle(@Valid Article a, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "article/article_add";
		}
		as.addArticle(a);
		return "redirect:/article";
	}
	
	/**
	 * Affichage du formulaire de modification d'articles
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("editArticle/{id}")
	public String showUpdateArticle(@PathVariable("id") long id, Model model) {
		Article a = as.getArticleById(id).get();
		model.addAttribute("article", a);
		model.addAttribute("categorie", cs.getAllCategorie());
		return "article/article_update";
	}
	
	/**
	 * Modification de l'article et retour à la liste d'articles
	 * @param id
	 * @param a
	 * @param result
	 * @param model
	 * @return
	 */
	
	@PostMapping("updateArticle/{id}")
	public String updateArticle(@PathVariable("id") long id, @Valid Article a, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "article/article_update";
		}
		as.updateArticle(a);
		return "redirect:/article";
	}
	
	/**
	 * Supprimer l'article choisi
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("deleteArticle/{id}")
	public String deleteArticle(@PathVariable("id") long id, Model model) {
		Article a = as.getArticleById(id).get();
		try {
			as.deleteArticle(a);
			model.addAttribute("article", as.getAllArticle());
		} catch(Exception e) {
			return "erreur/article_delete";
		}
		
		return "redirect:/article";
	}
}

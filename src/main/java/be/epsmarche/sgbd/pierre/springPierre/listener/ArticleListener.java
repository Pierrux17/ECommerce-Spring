package be.epsmarche.sgbd.pierre.springPierre.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.epsmarche.sgbd.pierre.springPierre.SpringContext;
import be.epsmarche.sgbd.pierre.springPierre.model.Article;
import be.epsmarche.sgbd.pierre.springPierre.model.ListeArticle;
import be.epsmarche.sgbd.pierre.springPierre.repository.ArticleRepository;
import be.epsmarche.sgbd.pierre.springPierre.service.ArticleService;

@Service
public class ArticleListener {
	
	public ArticleListener() {
		
	}
	
	/**
	 * Modifie le stock quand un article est commandé + met l'actif de l'article à false si le stock est à 0
	 * @param l
	 */
	@PrePersist
	public void persistArt(ListeArticle l) {
		ArticleRepository AR = SpringContext.getBean(ArticleRepository.class);
		ArticleService as = SpringContext.getBean(ArticleService.class);
		int qt = l.getQt();
		
		long id = l.getArticle().getId();
		Article a = as.getArticleById(id).get();
		
		int stock = a.getStock();
		a.setStock(stock - qt);
		as.updateArticle(a);
		
		if(a.getStock() == 0) {
			a.setActif(false);
		}
	}
	
	/**
	 * Modifie le stock quand un article est supprimé dans la commande + met l'actif de l'article à true si le stock repasse au dessus de 0
	 * @param l
	 */
	@PreRemove
	public void removeArt(ListeArticle l) {
		ArticleRepository AR = SpringContext.getBean(ArticleRepository.class);
		int qt = l.getQt();
		Article a = l.getArticle();
		int stock = a.getStock();
		a.setStock(stock + qt);
		AR.save(a);
		
		if(a.getStock() > 0) {
			a.setActif(true);
		}
	}
}

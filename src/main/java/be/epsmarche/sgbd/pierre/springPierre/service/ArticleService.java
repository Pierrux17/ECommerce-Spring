package be.epsmarche.sgbd.pierre.springPierre.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.epsmarche.sgbd.pierre.springPierre.model.Article;
import be.epsmarche.sgbd.pierre.springPierre.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository artRep;
	
	public ArticleService(){
		
	}
	
	/**
	 * Add Article
	 * @param art
	 * @return
	 */
	public Article addArticle(Article art) {
		Article a = artRep.save(art);
		return a;
	}
	
	/**
	 * Select Article by id
	 * @param id
	 * @return
	 */
	public Optional<Article> getArticleById(long id) {
		Optional<Article> a = this.artRep.findById(id);
		return a;
	}
	
	/**
	 * Select all Articles
	 * @return
	 */
	public ArrayList<Article> getAllArticle(){
		return(ArrayList<Article>) artRep.findAll();
	}
	
	/**
	 * Update Article
	 * @param art
	 * @return
	 */
	public Article updateArticle(Article art) {
		Article a = artRep.save(art);
		return a;
	}
	
	/**
	 * Delete Article
	 * @param art
	 */
	public void deleteArticle(Article art) {
		artRep.delete(art);
	}
	
	/**
	 * Delete Article by id
	 * @param id
	 */
	public void deleteArticleById(long id) {
		ArticleService as = new ArticleService();
		Article a = as.getArticleById(id).get();
		
		artRep.delete(a);
	}
	
	/**
	 * Select all actif Article
	 * @return
	 */
	public ArrayList<Article> getAllActifArticle(){
		return(ArrayList<Article>) artRep.getAllActifArticle();
	}
}

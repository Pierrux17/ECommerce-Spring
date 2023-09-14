package be.epsmarche.sgbd.pierre.springPierre.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.epsmarche.sgbd.pierre.springPierre.model.ListeArticle;
import be.epsmarche.sgbd.pierre.springPierre.repository.ListeArticleRepository;

@Service
public class ListeArticleService {

	@Autowired
	private ListeArticleRepository lsRep;
	
	public ListeArticleService() {
		
	}
	
	/**
	 * Add ListeArticle
	 * @param ls
	 * @return
	 */
	public ListeArticle addListeArticle(ListeArticle ls) {
		ListeArticle l = lsRep.save(ls);
		return l;
	}
	
	/**
	 * Select ListeArticle by id
	 * @param id
	 * @return
	 */
	public Optional<ListeArticle> getListeArticleById(long id){
		Optional<ListeArticle> l = lsRep.findById(id);
		return l;
	}
	
	/**
	 * Select all ListeArticle
	 * @return
	 */
	public ArrayList<ListeArticle> getAllListeArticle(){
		return(ArrayList<ListeArticle>) lsRep.findAll();
	}
	
	/**
	 * Update ListeArticle
	 * @param ls
	 * @return
	 */
	public ListeArticle updateListeArticle(ListeArticle ls) {
		ListeArticle l = lsRep.save(ls);
		return l;
	}
	
	/**
	 * Delete ListeArticle
	 * @param ls
	 */
	public void deleteListeArticle(ListeArticle ls) {
		lsRep.delete(ls);
	}
	
	/**
	 * Select all ListeArticle by id Commande
	 * @param idCmd
	 * @return
	 */
	public ArrayList<ListeArticle> getAllListeArticleByIdCmd(long idCmd){	
		return(ArrayList<ListeArticle>) lsRep.findAllByCommandeId(idCmd);
	}
}

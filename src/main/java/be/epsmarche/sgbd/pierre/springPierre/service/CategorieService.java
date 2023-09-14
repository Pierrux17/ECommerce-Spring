package be.epsmarche.sgbd.pierre.springPierre.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.epsmarche.sgbd.pierre.springPierre.model.Categorie;
import be.epsmarche.sgbd.pierre.springPierre.repository.CategorieRepository;

@Service
public class CategorieService {
	
	@Autowired
	private CategorieRepository catRep;
	
	public CategorieService() {
		
	}
	
	/**
	 * Add Categorie
	 * @param cat
	 * @return
	 */
	public Categorie addCategorie(Categorie cat) {
		Categorie c = catRep.save(cat);
		return c;
	}
	
	/**
	 * Select Categorie by id
	 * @param id
	 * @return
	 */
	public Optional<Categorie> getCategorieById(long id){
		Optional<Categorie> c = this.catRep.findById(id);
		return c;
	}
	
	/**
	 * Select All Categorie
	 * @return
	 */
	public ArrayList<Categorie> getAllCategorie(){
		return(ArrayList<Categorie>) catRep.findAll();
	}
	
	/**
	 * Update Categorie
	 * @param cat
	 * @return
	 */
	public Categorie updateCategorie(Categorie cat) {
		Categorie c = catRep.save(cat);
		return c;
	}
	
	/**
	 * Delete Categorie
	 * @param cat
	 */
	public void deleteCategorie(Categorie cat) {
		catRep.delete(cat);
	}
}

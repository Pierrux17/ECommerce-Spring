package be.epsmarche.sgbd.pierre.springPierre.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.epsmarche.sgbd.pierre.springPierre.model.Commande;
import be.epsmarche.sgbd.pierre.springPierre.repository.CommandeRepository;

@Service
public class CommandeService {

	@Autowired
	private CommandeRepository comRep;
	
	public CommandeService() {
		
	}
	
	/**
	 * Add Commande
	 * @param com
	 * @return
	 */
	public Commande addCommande(Commande com) {
		Commande c = comRep.save(com);
		return c;
	}
	
	/**
	 * Select Commande by id
	 * @param id
	 * @return
	 */
	public Optional<Commande> getCommandeById(long id){
		Optional<Commande> c = comRep.findById(id);
		return c;
	}
	
	/**
	 * Select all Commande
	 * @return
	 */
	public ArrayList<Commande> getAllCommande(){
		return(ArrayList<Commande>) comRep.findAll();
	}
	
	/**
	 * Update Commande
	 * @param com
	 * @return
	 */
	public Commande updateCommande(Commande com) {
		Commande c = comRep.save(com);
		return c;
	}
	
	/**
	 * Delete Commande
	 * @param com
	 */
	public void deleteCommande(Commande com) {
		comRep.delete(com);
	}
}

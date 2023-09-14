package be.epsmarche.sgbd.pierre.springPierre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.epsmarche.sgbd.pierre.springPierre.model.Commande;
import be.epsmarche.sgbd.pierre.springPierre.service.CommandeService;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCommande {

	@Autowired
	private CommandeService cs;
	
	public TestCommande() {
		
	}
	
	@Test
	@Order(1)
	public void addCommandeDB() {
		System.out.println("Test 1 : ADD commande");
		
		String str="2022-03-30";  
	    Date date=Date.valueOf(str);
		
		Commande newC = new Commande("Roberfroid", "Pierre", date, true);
		Commande c = cs.addCommande(newC);
		
		assertThat(c.getNom()).isEqualTo("Roberfroid");
		assertThat(c.getPrenom()).isEqualTo("Pierre");
		assertThat(c.getDate()).isEqualTo(date);
		assertThat(c.isCloture()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	public void selectCommandeByIdBD() {
		System.out.println("Test 2 : SELECT Commande by Id");
		
		long idCom = 1;
		
		Optional<Commande> comOp = cs.getCommandeById(idCom);
		
		comOp.ifPresent(com -> {
			Long id = com.getId();
			assertEquals(idCom, id);
			String nom = com.getNom();
			assertThat("Roberfroid").isEqualTo(nom);
		});
	}
	
	@Test
	@Order(3)
	public void updateCommandeDB() {
		System.out.println("Test 3 : UPDATE Commande");
		
		long idCom = 1;
		String str="2022-03-30";  
	    Date date=Date.valueOf(str);
		
		Commande oldCom = cs.getCommandeById(idCom).get();
		oldCom.setNom("Roberchaud");
		
		Commande c = cs.updateCommande(oldCom);
		
		assertThat(c.getNom()).isEqualTo("Roberchaud");
		assertThat(c.getPrenom()).isEqualTo("Pierre");
		assertThat(c.getDate()).isEqualTo(date);
		assertThat(c.isCloture()).isEqualTo(true);
	}
	
	@Test
	@Order(4)
	public void getAllCommandeDB() {
		ArrayList<Commande> liste = cs.getAllCommande();
		assertThat(liste.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	@Order(5)
	@Disabled
	public void deleteCommandeDB() {
		System.out.println("Test 4 : DELETE Commande");
		
		long idCom = 1;
		Commande c = cs.getCommandeById(idCom).get();
		
		cs.deleteCommande(c);
		//assertThat(c).isNull();
	}

}

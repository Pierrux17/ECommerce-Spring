package be.epsmarche.sgbd.pierre.springPierre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.epsmarche.sgbd.pierre.springPierre.model.Categorie;
import be.epsmarche.sgbd.pierre.springPierre.service.CategorieService;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCategorie {

	@Autowired
	private CategorieService cs;
	
	public TestCategorie() {
		
	}
	

	@Test
	@Order(1)
	public void addCategorieDB() {
		System.out.println("Test 1 : ADD Categorie");
		
		Categorie newC = new Categorie("Informatique");
		Categorie c = cs.addCategorie(newC);
		
		assertThat(c.getNom()).isEqualTo("Informatique");
	}
	
	@Test
	@Order(2)
	public void selectCategorieByIdDB() {
		System.out.println("Test 2 : SELECT Categorie by Id");
		
		long idCat = 1;
		
		Optional<Categorie> catOp = cs.getCategorieById(idCat);
		
		catOp.ifPresent(cat -> {
			Long id = cat.getId();
			assertEquals(idCat, id);
			String nom = cat.getNom();
			assertThat("Informatique").isEqualTo(nom);
		});
	}
	
	@Test
	@Order(3)
	//@Disabled
	public void updateCategorieDB() {
		System.out.println("Test 3 : UPDATE Categorie");
		
		long idCat = 1;
		Categorie oldCat = cs.getCategorieById(idCat).get();
		oldCat.setNom("Jeux videos");
		
		Categorie c = cs.updateCategorie(oldCat);
		
		assertThat(c.getNom()).isEqualTo("Jeux videos");
	}
	
	@Test
	@Order(4)
	public void getAllCategorieDB() {
		ArrayList<Categorie> liste = cs.getAllCategorie();
		assertThat(liste.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	@Order(5)
	@Disabled
	public void deleteCategorieDB() {
		System.out.println("Test 4 : DELETE Categorie");
		
		long idCat = 1;
		Categorie c = cs.getCategorieById(idCat).get();
		
		cs.deleteCategorie(c);
	}
}

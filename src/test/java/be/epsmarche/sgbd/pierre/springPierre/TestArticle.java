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

import be.epsmarche.sgbd.pierre.springPierre.model.Article;
import be.epsmarche.sgbd.pierre.springPierre.model.Categorie;
import be.epsmarche.sgbd.pierre.springPierre.service.ArticleService;
import be.epsmarche.sgbd.pierre.springPierre.service.CategorieService;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestArticle {
	
	@Autowired
	private ArticleService as;
	
	@Autowired
	private CategorieService cs;

	public TestArticle() {
		
	}
	
	@Test
	@Order(1)
	public void addArticleDB() {
		System.out.println("Test 1 : ADD Article");
		
		long idCat = 1;
		Categorie c = cs.getCategorieById(idCat).get(); 
		
		Article newA = new Article("Ordinateur", 400, 250, true, c);
		Article a = as.addArticle(newA);
		
		assertThat(a.getDenomination()).isEqualTo("Ordinateur");
		assertThat(a.getPrix()).isEqualTo(400);
		assertThat(a.getStock()).isEqualTo(250);
		assertThat(a.isActif()).isEqualTo(true);
		assertThat(a.getCat()).isEqualTo(c);
	}
	
	@Test
	@Order(2)
	public void selectArticleByIdDB() {
		System.out.println("Test 2 : SELECT Article by Id");
		
		long idArt = 1;
		
		Optional<Article> artOp = as.getArticleById(idArt);
		
		artOp.ifPresent(art -> {
			Long id = art.getId();
			assertEquals(idArt, id);
			String nom = art.getDenomination();
			assertThat("Ordinateur").isEqualTo(nom);
		});
	}
	
	@Test
	@Order(3)
	//@Disabled
	public void updateArticleDB() {
		System.out.println("Test 3 : UPDATE Article");
	
		long idArt = 1;
		Article oldA = as.getArticleById(idArt).get();	
		oldA.setDenomination("Xbox Serie X");
		
		Article a = as.updateArticle(oldA);
		
		assertThat(a.getDenomination()).isEqualTo("Xbox Serie X");
		assertThat(a.getPrix()).isEqualTo(400);
		assertThat(a.getStock()).isEqualTo(250);
		assertThat(a.isActif()).isEqualTo(true);
	}
	
	@Test
	@Order(4)
	public void getAllArticleDB() {
		ArrayList<Article> liste = as.getAllArticle();
		assertThat(liste.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	@Order(5)
	@Disabled
	public void deleteArticleDB() {
		System.out.println("Test 4 : DELETE Article");
		
		long idArt = 1;
		Article a = as.getArticleById(idArt).get();
		
		as.deleteArticle(a);
		//assertThat(a).isNull();
		
		/*Article artDel = as.getArticleById(idArt).get();
		assertThat(artDel).isNull();*/
	}
}


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
import be.epsmarche.sgbd.pierre.springPierre.model.Commande;
import be.epsmarche.sgbd.pierre.springPierre.model.ListeArticle;
import be.epsmarche.sgbd.pierre.springPierre.service.ArticleService;
import be.epsmarche.sgbd.pierre.springPierre.service.CommandeService;
import be.epsmarche.sgbd.pierre.springPierre.service.ListeArticleService;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestListeArticle {

	@Autowired
	private ListeArticleService las;
	@Autowired
	private ArticleService as;
	@Autowired
	private CommandeService cs;

	public TestListeArticle() {
		
	}
	
	@Test
	@Order(1)
	public void addListeArticleDB() {
		System.out.println("Test 1 : ADD ListeArticle");
		
		long idOther = 1;
		Article a = as.getArticleById(idOther).get();
		Commande c = cs.getCommandeById(idOther).get();
;		
		ListeArticle newL = new ListeArticle(4, 200, c, a);
		ListeArticle l = las.addListeArticle(newL);
		
		assertThat(l.getQt()).isEqualTo(4);
		assertThat(l.getPrixactuel()).isEqualTo(200);
		assertThat(l.getCommande()).isEqualTo(c);
		assertThat(l.getArticle()).isEqualTo(a);
	}
	
	@Test
	@Order(2)
	public void selectListeArticleByIdBD() {
		System.out.println("Test 2 : SELECT ListeArticle by Id");
		
		/*long idOther = 1;
		Article a = as.getArticleById(idOther).get();
		Commande c = cs.getCommandeById(idOther).get();*/
		
		long idListArt = 1;
		Optional<ListeArticle> ListArtOp = las.getListeArticleById(idListArt);
		
		ListArtOp.ifPresent(listeArt ->{
			Long id = listeArt.getId();
			assertEquals(idListArt, id);
			});
	}
	
	@Test
	@Order(3)
	//@Disabled
	public void updateListeArticleDB() {
		System.out.println("Test 3 : UPDATE ListeArticle");
		
		long idOther = 1;
		Article a = as.getArticleById(idOther).get();
		Commande c = cs.getCommandeById(idOther).get();
		
		long idListArt = 1;
		ListeArticle oldL = las.getListeArticleById(idListArt).get();
		oldL.setQt(6);
		oldL.setPrixactuel(300);

		ListeArticle l = las.updateListeArticle(oldL);
		
		assertThat(l.getQt()).isEqualTo(6);
		assertThat(l.getPrixactuel()).isEqualTo(300);
	}
	
	@Test
	@Order(4)
	public void getAllListeArticleDB() {
		ArrayList<ListeArticle> liste = las.getAllListeArticle();
		assertThat(liste.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	@Order(5)
	@Disabled
	public void deleteListeArticleDB() {
		System.out.println("Test 4 : DELETE ListeArticle");
		
		long idListArt = 1;
		ListeArticle l = las.getListeArticleById(idListArt).get();
		
		las.deleteListeArticle(l);
		//assertThat(l).isNull();
	}
}

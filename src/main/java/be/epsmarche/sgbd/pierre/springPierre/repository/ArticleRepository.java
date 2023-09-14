package be.epsmarche.sgbd.pierre.springPierre.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import be.epsmarche.sgbd.pierre.springPierre.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

	@Query("SELECT a FROM Article a WHERE a.actif = true")
	ArrayList<Article> getAllActifArticle();
}


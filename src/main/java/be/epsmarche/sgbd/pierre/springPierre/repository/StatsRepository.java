package be.epsmarche.sgbd.pierre.springPierre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import be.epsmarche.sgbd.pierre.springPierre.model.Article;

@Repository
public interface StatsRepository extends JpaRepository<Article, Long> {
    
	@Query(value = "SELECT c.nom_categorie, COUNT(l.id_listearticle) FROM Categorie c INNER JOIN Article a ON a.id_categorie = c.id_categorie INNER JOIN Listearticle l ON l.id_article = a.id_article GROUP BY c.nom_categorie", nativeQuery = true)
    List<List<Object>> getArtCmdByCat();
}


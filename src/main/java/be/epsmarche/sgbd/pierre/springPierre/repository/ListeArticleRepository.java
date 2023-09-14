package be.epsmarche.sgbd.pierre.springPierre.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import be.epsmarche.sgbd.pierre.springPierre.model.ListeArticle;

@Repository
public interface ListeArticleRepository extends JpaRepository<ListeArticle, Long>{

	@Query("SELECT la FROM ListeArticle la WHERE la.commande.id = :id")
	ArrayList<ListeArticle> findAllByCommandeId(@Param("id") long id);

}

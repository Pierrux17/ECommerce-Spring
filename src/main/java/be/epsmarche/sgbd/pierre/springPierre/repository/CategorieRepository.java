package be.epsmarche.sgbd.pierre.springPierre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.epsmarche.sgbd.pierre.springPierre.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{
}

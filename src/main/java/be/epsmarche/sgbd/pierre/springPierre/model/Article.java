package be.epsmarche.sgbd.pierre.springPierre.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Check;

//import jakarta.persistence.*;

@Entity
@Table(name="article")
@Check(constraints = "stock_article >= 0")
public class Article implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_article")
	private long id;
	@Column(name = "denomination_article")
	@NotNull
	private String denomination;
	@Column(name = "prix_article")
	@NotNull
	@Size(min = 0)
	private float prix;
	@Column(name = "stock_article")
	@NotNull
	private int stock;
	@Column(name = "actif_article")
	@NotNull
	private boolean actif;
	
	@OneToMany(mappedBy = "article")
	private Set<ListeArticle> listeArticle;
	
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie cat;
	
	public Article() {
	}
	
	public Article(String denomination, float prix, int stock, boolean actif, Categorie cat) {
		this.denomination = denomination;
		this.prix = prix;
		this.stock = stock;
		this.actif = actif;
		this.cat = cat;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDenomination() {
		return denomination;
	}
	
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	public float getPrix() {
		return prix;
	}
	
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public boolean isActif() {
		return actif;
	}
	
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	public Categorie getCat() {
		return cat;
	}
	
	public void setCat(Categorie cat) {
		this.cat = cat;
	}
	
	public Set<ListeArticle> getListeArticle() {
		return listeArticle;
	}
	
	public void setListeArticle(Set<ListeArticle> listeArticle) {
		this.listeArticle = listeArticle;
	}


}
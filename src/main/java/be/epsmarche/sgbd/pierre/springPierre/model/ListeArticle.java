package be.epsmarche.sgbd.pierre.springPierre.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import jakarta.persistence.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.epsmarche.sgbd.pierre.springPierre.listener.ArticleListener;


@EntityListeners(ArticleListener.class)
@Entity
@Table(name="listearticle")
public class ListeArticle implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_listearticle")
	private long id;
	@Column(name = "qtycommande")
	private int qt;
	@Column(name = "prixactuel")
	private float prixactuel;

	@ManyToOne
	@JoinColumn(name = "id_commande")
	private Commande commande;

	@ManyToOne
	@JoinColumn(name = "id_article")
	private Article article;
	
	
	public ListeArticle() {
	}
	
	public ListeArticle(int qt, float prixactuel, Commande commande, Article article) {
		this.qt = qt;
		this.prixactuel = prixactuel;
		this.commande = commande;
		this.article = article;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getQt() {
		return qt;
	}
	
	public void setQt(int qt) {
		this.qt = qt;
	}
	
	public float getPrixactuel() {
		return prixactuel;
	}
	
	public void setPrixactuel(float prixactuel) {
		this.prixactuel = prixactuel;
	}
	
	public Commande getCommande() {
		return commande;
	}
	
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	public Article getArticle() {
		return article;
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}

}
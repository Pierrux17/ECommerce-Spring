package be.epsmarche.sgbd.pierre.springPierre.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import jakarta.persistence.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
@Table(name="commande")
public class Commande implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_commande")
	private long id;
	@Column(name = "nom_commande")
	@NotNull
	private String nom;
	@Column(name = "prenom_commande")
	@NotNull
	private String prenom;
	@Column(name = "date_commande")
	@NotNull
	private Date date;
	@Column(name = "cloture_commande")
	@NotNull
	private boolean cloture;
	
	@OneToMany(mappedBy = "commande", cascade = CascadeType.REMOVE)
	private Set<ListeArticle> listeArticle;
	
	public Commande() {
		
	}
	
	public Commande(String nom, String prenom, Date date, boolean cloture) {
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.cloture = cloture;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isCloture() {
		return cloture;
	}
	
	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}
}

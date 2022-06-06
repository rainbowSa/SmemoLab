package com.smemoLab.webapp.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Postit {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String titolo;
	
	private String descrizione;
	private Date dataScadenza;
	@Column(nullable = false)
	private Date dataCreazione;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "postit_categoria",
			joinColumns = @JoinColumn(name ="fk_postit"),
			inverseJoinColumns = @JoinColumn(name = "fk_categoria")
			)
	private List<Categoria> categorie;
	
	@ManyToOne
	@JoinColumn(name ="fk_utente")
	private Utente utente;
	
	//Costruttore vuoto
	public Postit() {
		
	}


	public int getId() {
		return id;
	}


	public String getTitolo() {
		return titolo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public Date getDataScadenza() {
		return dataScadenza;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}


	public List<Categoria> getCategorie() {
		return categorie;
	}


	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}


	public Date getDataCreazione() {
		return dataCreazione;
	}


	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	
	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}


		//aggiunge una  nuova categoria alla lista delle categorie del postIT
		public void addCategoria(Categoria newCategoria) {
			
			this.categorie.add(newCategoria);
		}


		@Override
		public String toString() {
			return "Postit [id=" + id + ", titolo=" + titolo + ", descrizione=" + descrizione + ", dataScadenza="
					+ dataScadenza + ", dataCreazione=" + dataCreazione + ", categorie=" + categorie + ", utente="
					+ utente + "]";
		}
	
		
}

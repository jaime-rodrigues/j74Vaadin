package com.j74.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the uf database table.
 * 
 */
@Entity
@Table(name="uf")
@NamedQuery(name="Uf.findAll", query="SELECT u FROM Uf u")
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, length=2)
	private String ufsigla;

	private int id;

	@Column(name="UF_IESTADUAL_SUBSTITUTOTRIB", length=20)
	private String ufIestadualSubstitutotrib;

	@Column(length=50)
	private String ufdescr;

	//bi-directional many-to-one association to Cidade
	@OneToMany(mappedBy="uf")
	private List<Cidade> cidades;

	public Uf() {
	}

	public String getUfsigla() {
		return this.ufsigla;
	}

	public void setUfsigla(String ufsigla) {
		this.ufsigla = ufsigla;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUfIestadualSubstitutotrib() {
		return this.ufIestadualSubstitutotrib;
	}

	public void setUfIestadualSubstitutotrib(String ufIestadualSubstitutotrib) {
		this.ufIestadualSubstitutotrib = ufIestadualSubstitutotrib;
	}

	public String getUfdescr() {
		return this.ufdescr;
	}

	public void setUfdescr(String ufdescr) {
		this.ufdescr = ufdescr;
	}

	public List<Cidade> getCidades() {
		return this.cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade addCidade(Cidade cidade) {
		getCidades().add(cidade);
		cidade.setUf(this);

		return cidade;
	}

	public Cidade removeCidade(Cidade cidade) {
		getCidades().remove(cidade);
		cidade.setUf(null);

		return cidade;
	}

}
package com.j74.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aux_pais database table.
 * 
 */
@Entity
@Table(name="aux_pais")
@NamedQuery(name="AuxPais.findAll", query="SELECT a FROM AuxPais a")
public class AuxPais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=50)
	private String descricao;

	@Column(length=50)
	private String gerundio;

	public AuxPais() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGerundio() {
		return this.gerundio;
	}

	public void setGerundio(String gerundio) {
		this.gerundio = gerundio;
	}

}
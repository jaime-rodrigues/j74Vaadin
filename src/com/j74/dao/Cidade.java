package com.j74.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@Table(name="cidade")
@NamedQuery(name="Cidade.findAll", query="SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="CID_MODIFICACAO_DATAHORA", nullable=false)
	private Timestamp cidModificacaoDatahora;

	@Column(name="COD_SIAFI")
	private int codSiafi;

	@Column(nullable=false)
	private int codpais;

	@Column(name="NFSE_PROVEDOR_ID")
	private int nfseProvedorId;

	@Column(nullable=false, length=50)
	private String nomecidade;

	//bi-directional many-to-one association to Uf
	@ManyToOne
	@JoinColumn(name="ESTADO")
	private Uf uf;

	public Cidade() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCidModificacaoDatahora() {
		return this.cidModificacaoDatahora;
	}

	public void setCidModificacaoDatahora(Timestamp cidModificacaoDatahora) {
		this.cidModificacaoDatahora = cidModificacaoDatahora;
	}

	public int getCodSiafi() {
		return this.codSiafi;
	}

	public void setCodSiafi(int codSiafi) {
		this.codSiafi = codSiafi;
	}

	public int getCodpais() {
		return this.codpais;
	}

	public void setCodpais(int codpais) {
		this.codpais = codpais;
	}

	public int getNfseProvedorId() {
		return this.nfseProvedorId;
	}

	public void setNfseProvedorId(int nfseProvedorId) {
		this.nfseProvedorId = nfseProvedorId;
	}

	public String getNomecidade() {
		return this.nomecidade;
	}

	public void setNomecidade(String nomecidade) {
		this.nomecidade = nomecidade;
	}

	public Uf getUf() {
		return this.uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}
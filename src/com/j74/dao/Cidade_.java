package com.j74.dao;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-12T11:08:54.363-0300")
@StaticMetamodel(Cidade.class)
public class Cidade_ {
	public static volatile SingularAttribute<Cidade, Integer> id;
	public static volatile SingularAttribute<Cidade, Timestamp> cidModificacaoDatahora;
	public static volatile SingularAttribute<Cidade, Integer> codSiafi;
	public static volatile SingularAttribute<Cidade, Integer> codpais;
	public static volatile SingularAttribute<Cidade, Integer> nfseProvedorId;
	public static volatile SingularAttribute<Cidade, String> nomecidade;
	public static volatile SingularAttribute<Cidade, Uf> uf;
}

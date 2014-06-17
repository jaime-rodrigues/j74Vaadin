package com.j74.dao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-12T11:08:54.512-0300")
@StaticMetamodel(Uf.class)
public class Uf_ {
	public static volatile SingularAttribute<Uf, String> ufsigla;
	public static volatile SingularAttribute<Uf, Integer> id;
	public static volatile SingularAttribute<Uf, String> ufIestadualSubstitutotrib;
	public static volatile SingularAttribute<Uf, String> ufdescr;
	public static volatile ListAttribute<Uf, Cidade> cidades;
}

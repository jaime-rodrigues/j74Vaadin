package com.j74.listas;

import com.j74.base.BaseListView;
import com.j74.dao.Cidade;
import com.j74.utils.Utils;
import com.lexaden.grid.PagedTable;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class ProdutosListView extends BaseListView {
	
	public ProdutosListView() {
		super();

		JPAContainer<Cidade> cidades = JPAContainerFactory.make(Cidade.class, "j74Vaadin");
		cidades.addNestedContainerProperty("uf.ufsigla");
		        
		PagedTable t = new PagedTable();
        t.addStyleName(Reindeer.TABLE_BORDERLESS);
//        t.addStyleName("borderless");
        t.setContainerDataSource(cidades);
        t.setWidth("100%");
        t.setColumnCollapsingAllowed(true);
        t.setColumnReorderingAllowed(true);

		t.setVisibleColumns(new Object[]{"id","nomecidade","uf.ufsigla"});
        t.setColumnHeaders(new String[]{"#Id", "Cidade", "UF"});
        t.setSelectable(true);              
                
        CssLayout panel = Utils.CreatePanel(t);
        getRow().addComponent(panel);

        addComponent(t.createControls());
	}

	@Override
	protected String getTitulo() {
		return "Produtos";
	}

}
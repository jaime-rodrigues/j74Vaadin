package com.j74.listas;

import com.j74.base.BaseListView;
import com.j74.dao.Cidade;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ClientesListView extends BaseListView {
	
	public ClientesListView() {
		super();
		
		JPAContainer<Cidade> cidades = JPAContainerFactory.make(Cidade.class, "j74Vaadin");
		cidades.addNestedContainerProperty("uf.ufsigla");
		        
        getTable().setContainerDataSource(cidades);
        getTable().setVisibleColumns(new Object[]{"id","nomecidade","uf.ufsigla"});
        getTable().setColumnHeaders(new String[]{"#Id", "Cidade", "UF"});

		getControls().addComponent(new Button("Adicionar") {
			{
				addStyleName("icon-transactions");
				setDescription("Adicionar");
				addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						Notification
								.show("Adicionar - Não implementado nesta versão.");
					}
				});
			}
		});

		getControls().addComponent(new Button("Remover") {
			{
				addStyleName("icon-cancel");
				setDescription("Remover");
				addClickListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
		    			Notification.show("Remover - Não implementado nesta versão.");
					}
				});
			}
		});
	}

	@Override
	protected String getTitulo() {
		return "Clientes";
	}

}

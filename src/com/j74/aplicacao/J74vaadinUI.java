package com.j74.aplicacao;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.j74.controles.PageControl;
import com.j74.listas.AuxiliaresListView;
import com.j74.listas.ClientesListView;
import com.j74.listas.ProdutosListView;
import com.j74.listas.VendasListView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("dashboard")
public class J74vaadinUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = J74vaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private CssLayout root = new CssLayout();
	private PageControl pageControl;

	public PageControl getPageControl() {
		return pageControl;
	}

	@Override
	protected void init(VaadinRequest request) {
		setLocale(Locale.US);

		setContent(root);
		root.addStyleName("root");
		root.setSizeFull();

		buildMainView();
	}

	private void buildMainView() {
		pageControl = new PageControl(this,
				new HashMap<String, Class<? extends View>>() {
					{
						put("clientes", ClientesListView.class);
						put("produtos", ProdutosListView.class);
						put("vendas", VendasListView.class);
						put("auxiliares", AuxiliaresListView.class);
					}
				});
		root.addComponent(pageControl);
	}

}
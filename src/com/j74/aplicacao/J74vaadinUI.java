package com.j74.aplicacao;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.j74.controles.PageControl;
import com.j74.controles.PageLogin;
import com.j74.helpers.HelpManager;
import com.j74.helpers.HelpOverlay;
import com.j74.listas.AuxiliaresListView;
import com.j74.listas.ClientesListView;
import com.j74.listas.ProdutosListView;
import com.j74.listas.VendasListView;
import com.j74.listners.LoginEvent;
import com.j74.listners.LoginListener;
import com.j74.views.DashboardView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.client.ui.VNotification.HideEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("dashboard")
public class J74vaadinUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = J74vaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private PageLogin loginLayout;
    private CssLayout root = new CssLayout();
    private PageControl pageControl;

    public PageControl getPageControl(){
    	return pageControl;	
    }
    
    @Override
    protected void init(VaadinRequest request) {   
        setLocale(Locale.US);

        setContent(root);
        root.addStyleName("root");
        root.setSizeFull();

        loginLayout = new PageLogin(root);
        loginLayout.addLoginListener(new LoginListener() {		
			@Override
			public void onLogin(LoginEvent e) {
		        removeStyleName("login");
		        root.removeComponent(loginLayout);
		    	HelpManager.getInstance().closeAll();
				buildMainView();				
			}
		});       
    }

    
    private void buildMainView() {      
        pageControl = new PageControl(this, new HashMap<String, Class<? extends View>>() {
        	{	put("clientes", ClientesListView.class);
         		put("produtos", ProdutosListView.class);
         		put("vendas", VendasListView.class);
         		put("auxiliares", AuxiliaresListView.class);
        	}
        });
        root.addComponent(pageControl);
    }
 
}
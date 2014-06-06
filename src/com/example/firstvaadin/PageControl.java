package com.example.firstvaadin;

import java.util.HashMap;
import java.util.Iterator;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PageControl extends HorizontalLayout {

	private Navigator navegador;
    private HelpManager helpManager;
    private UI parentUI;
    private CssLayout content = new CssLayout();
    private CssLayout menu = new CssLayout();    
    private VerticalLayout sidebar = new VerticalLayout();
	private CssLayout branding = new CssLayout();
	private VerticalLayout user = new VerticalLayout();
	private MenuBar settings = new MenuBar();
 	
    private HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>();
    private HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();

    public HashMap<String, Class<? extends View>> getRoutes(){
    	return routes;
    }
    
	public PageControl(UI parentUI, HashMap<String, Class<? extends View>> routes) {
		this.parentUI = parentUI;
		this.routes = routes;
		helpManager = new HelpManager(parentUI);

		CreateSideBar();
		CreateContainerview();
		CreateMenu();
		CreateNavigator();
		CreateUser();
		CreateSettings();
		CreateBranding();
	}
	
    private void CreateContainerview(){
    	setSizeFull();
    	addStyleName("main-view");

		addComponent(sidebar);
		addComponent(content);
    	content.setSizeFull();
    	content.addStyleName("view-content");
    	setExpandRatio(content, 1);    	
    }

    private void CreateMenu() {
    	menu.removeAllComponents();

    	for (final String view : routes.keySet()) {
    		Button b = new NativeButton(view.substring(0, 1).toUpperCase() + view.substring(1).replace('-', ' '));
    		b.addStyleName("icon-" + view);
    		b.addClickListener(new ClickListener() {
    			@Override
    			public void buttonClick(ClickEvent event) {
    				clearMenuSelection(menu);
    				event.getButton().addStyleName("selected");
    				if (!navegador.getState().equals("/" + view))
    					navegador.navigateTo("/" + view);
    			}
    		});

    		menu.addComponent(b);
    		viewNameToMenuButton.put("/" + view, b);
    	}
    	menu.addStyleName("menu");
    	menu.setHeight("100%");

    }
    
    private void CreateNavigator(){
    	viewNameToMenuButton.get("/dashboard").setHtmlContentAllowed(true);
    	viewNameToMenuButton.get("/dashboard").setCaption("Dashboard<span class=\"badge\">2</span>");

        navegador = new Navigator(parentUI, content);

    	for (String route : routes.keySet()) {
    		navegador.addView("/" + route, routes.get(route));
    	}
    	String f = Page.getCurrent().getUriFragment();
    	if (f != null && f.startsWith("!")) {
    		f = f.substring(1);
    	}
    	if (f == null || f.equals("") || f.equals("/")) {
    		navegador.navigateTo("/dashboard");
    		menu.getComponent(0).addStyleName("selected");
    		helpManager.showHelpFor(DashboardView.class);
    	} else {
    		navegador.navigateTo(f);
    		helpManager.showHelpFor(routes.get(f));
    		viewNameToMenuButton.get(f).addStyleName("selected");
    	}

    	navegador.addViewChangeListener(new ViewChangeListener() {
    		@Override
    		public boolean beforeViewChange(ViewChangeEvent event) {
    			helpManager.closeAll();
    			return true;
    		}

    		@Override
    		public void afterViewChange(ViewChangeEvent event) {
    			View newView = event.getNewView();
    			helpManager.showHelpFor(newView);
    		}
    	});
    	
    }
    
    private void CreateSideBar(){
        sidebar.addStyleName("sidebar");
    	sidebar.setWidth(null);
    	sidebar.setHeight("100%");
    	sidebar.addComponent(branding);
    	
    	sidebar.addComponent(menu);
    	sidebar.setExpandRatio(menu, 1);

    	sidebar.addComponent(user);
    	
    }

    private void CreateBranding(){
    	branding.addStyleName("branding");

    	Label logo = new Label("Zombre <span>Underwear</span>", ContentMode.HTML);
    	logo.setSizeUndefined();
    	branding.addComponent(logo);
    	
    }
    
    private void CreateUser(){
    	user.setSizeUndefined();
    	user.addStyleName("user");

    	Image profilePic = new Image(null, new ThemeResource("img/profile-pic.png"));
    	profilePic.setWidth("34px");
    	user.addComponent(profilePic);

    	Label userName = new Label("J. Rodrigues");
    	userName.setSizeUndefined();
    	user.addComponent(userName);

    	user.addComponent(settings);

    	Button exit = new NativeButton("Sair");
    	exit.addStyleName("icon-cancel");
    	exit.setDescription("Logoff");
    	user.addComponent(exit);
    	exit.addClickListener(new ClickListener() {
    		public void buttonClick(ClickEvent event) {
//    			buildLoginView(true);
    		}
    	});
    	
    }
    
    private void CreateSettings(){
    	Command cmd = new Command() {
    		@Override
    		public void menuSelected(MenuItem selectedItem) {
    			Notification.show("Não implementado nesta versão.");
    		}
    	};
    	
    	MenuItem settingsMenu = settings.addItem("", null);
    	settingsMenu.setStyleName("icon-cog");
    	settingsMenu.addItem("Configurações", cmd);
    	settingsMenu.addItem("Preferências", cmd);
    	settingsMenu.addSeparator();
    	settingsMenu.addItem("Minha conta", cmd);    	
    }
    
    public void clearDashboardButtonBadge() {
        viewNameToMenuButton.get("/dashboard").setCaption("Dashboard");
    }
 
	public void clearMenuSelection(CssLayout menu) {
        for (Iterator<Component> it = menu.getComponentIterator(); it.hasNext();) {
            Component next = it.next();
            if (next instanceof NativeButton) {
                next.removeStyleName("selected");
            } else if (next instanceof DragAndDropWrapper) {
                // Wow, this is ugly (even uglier than the rest of the code)
                ((DragAndDropWrapper) next).iterator().next().removeStyleName("selected");
            }
        }
    }
 
}

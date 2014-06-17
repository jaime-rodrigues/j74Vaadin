package com.j74.controles;

import java.util.HashMap;
import java.util.Iterator;

import com.j74.helpers.HelpManager;
import com.j74.views.DashboardView;
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
public class PageControl extends VerticalLayout {

	private Navigator navegador;
    private UI parentUI;
    private CssLayout content = new CssLayout();
    private HorizontalLayout menu = new HorizontalLayout();    
    private HorizontalLayout sidebar = new HorizontalLayout();
	private HorizontalLayout branding = new HorizontalLayout();
	private HorizontalLayout user = new HorizontalLayout();
	private MenuBar settings = new MenuBar();
 	
    private HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>();
    private HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();

    public HashMap<String, Class<? extends View>> getRoutes(){
    	return routes;
    }
    
	public PageControl(UI parentUI, HashMap<String, Class<? extends View>> routes) {
		this.parentUI = parentUI;
		this.routes = routes;
     	
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
    	content.setSizeUndefined();
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
    	menu.setSizeUndefined();

    }
    
    private void CreateNavigator(){
        navegador = new Navigator(parentUI, content);
		navegador.addView("/dashboard", DashboardView.class);

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
    		HelpManager.getInstance().showHelpFor(DashboardView.class);
    	} else {
    		navegador.navigateTo(f);
    		HelpManager.getInstance().showHelpFor(routes.get(f));
    		viewNameToMenuButton.get(f).addStyleName("selected");
    	}

    	navegador.addViewChangeListener(new ViewChangeListener() {
    		@Override
    		public boolean beforeViewChange(ViewChangeEvent event) {
    			HelpManager.getInstance().closeAll();
    			return true;
    		}

    		@Override
    		public void afterViewChange(ViewChangeEvent event) {
    			View newView = event.getNewView();
    			HelpManager.getInstance().showHelpFor(newView);
    		}
    	});
    	
    }
    
    private void CreateSideBar(){
        sidebar.addStyleName("sidebar");
    	sidebar.setWidth("100%");
    	sidebar.setHeight(null);
    	sidebar.addComponent(branding);
    	
    	sidebar.addComponent(menu);
    	sidebar.setExpandRatio(menu, 1);

    	sidebar.addComponent(user);
    	
    }

    private void CreateBranding(){
    	branding.setSizeUndefined();
    	branding.addStyleName("branding");
    	branding.addStyleName("menu");

		Button b = new NativeButton();
		b.addStyleName("icon-dashboard");
		b.setSizeFull();
		b.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				clearMenuSelection(menu);
				event.getButton().addStyleName("selected");
				if (!navegador.getState().equals("/dashboard"))
					navegador.navigateTo("/dashboard");
			}
		});

		viewNameToMenuButton.put("/dashboard", b);
    	viewNameToMenuButton.get("/dashboard").setHtmlContentAllowed(true);
    	viewNameToMenuButton.get("/dashboard").setCaption("<span class=\"badge\">2</span>");

    	Label logo = new Label("Zombre <span>Underwear</span>", ContentMode.HTML);
    	logo.setSizeFull();
    	branding.addComponent(logo);
    	branding.addComponent(b);
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
    			Notification.show("N�o implementado nesta vers�o.");
    		}
    	};
    	
    	MenuItem settingsMenu = settings.addItem("", null);
    	settingsMenu.setStyleName("icon-cog");
    	settingsMenu.addItem("Configura��es", cmd);
    	settingsMenu.addItem("Prefer�ncias", cmd);
    	settingsMenu.addSeparator();
    	settingsMenu.addItem("Minha conta", cmd);    	
    	settingsMenu.addItem("Sair", cmd);    	
    }
    
    public void clearDashboardButtonBadge() {
        viewNameToMenuButton.get("/dashboard").setCaption("Dashboard");
    }
 
	public void clearMenuSelection(HorizontalLayout menu) {
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

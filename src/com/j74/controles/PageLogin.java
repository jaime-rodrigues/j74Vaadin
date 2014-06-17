package com.j74.controles;

import javax.swing.event.EventListenerList;

import com.j74.helpers.HelpManager;
import com.j74.helpers.HelpOverlay;
import com.j74.listners.LoginEvent;
import com.j74.listners.LoginListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
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
import com.vaadin.ui.VerticalLayout;

public class PageLogin extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private CssLayout root;
	private EventListenerList loginListeners = new EventListenerList();

	public PageLogin(CssLayout root) {
		super();

		this.root = root;
		this.root.removeAllComponents();

		buildLoginView();
	}

	private void buildLoginView() {
        Label bg = new Label();
        bg.setSizeUndefined();
        bg.addStyleName("login-bg");
        root.addComponent(bg);
        root.addComponent(this);
		
//		HelpManager.getInstance().closeAll();
//		HelpOverlay w = HelpManager
//				.getInstance()
//				.addOverlay(
//						"Bem-vindo à aplicação demo j74.GWeb",
//						"<p>Esta aplicação ainda não é real, somente demonstra uma aplicação construida com o <a href=\"http://vaadin.com\">Vaadin framework</a>."
//						+ "</p><p>Usuário e senha não são obrigatórios, clique no botão \"Entrar\" para continuar. Você pode tentar um usuário e senha aleatórios.</p>",
//						"Entrar");
//		w.center();
//		root.getUI().addWindow(w);
		root.getUI().addStyleName("login");

		setSizeFull();
		addStyleName("login-layout");

		final CssLayout loginPanel = new CssLayout();
		loginPanel.addStyleName("login-panel");

		VerticalLayout labels = new VerticalLayout();
		labels.setWidth("100%");
		labels.setMargin(true);
		labels.addStyleName("labels");
		loginPanel.addComponent(labels);

		Label welcome = new Label("Bem-vindo à aplicação j74.GWeb demo");
		welcome.setSizeUndefined();
		welcome.addStyleName("h4");
		labels.addComponent(welcome);
		labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);

		Label title = new Label("<p>Esta aplicação ainda não é real.<br>"
								+ "É somente uma demonstração construida com o <a href=\"http://vaadin.com\">Vaadin framework</a>.<br>"
	  							+ "Usuário e senha não são obrigatórios, clique no botão \"Entrar\" para continuar.<br>"
								+ "Você pode tentar um usuário e senha aleatórios.</p>",
	  							ContentMode.HTML);

		title.setSizeUndefined();
		title.addStyleName("Entrar");
		labels.addComponent(title);
		labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);

		HorizontalLayout fields = new HorizontalLayout();
		fields.setSpacing(true);
		fields.setMargin(true);
		fields.addStyleName("fields");

		final TextField username = new TextField("Usuário");
		username.focus();
		fields.addComponent(username);

		final PasswordField password = new PasswordField("Senha");
		fields.addComponent(password);

		final Button signin = new Button("Entrar");
		signin.addStyleName("default");
		fields.addComponent(signin);
		fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

		final ShortcutListener enter = new ShortcutListener("Entrar", KeyCode.ENTER, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				signin.click();
			}
		};

		signin.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (username.getValue() != null
						&& username.getValue().equals("")
						&& password.getValue() != null
						&& password.getValue().equals("")) {
					signin.removeShortcutListener(enter);
					// dispara o evento de login
					fireOnLogin(new LoginEvent(this), true);
				} else {
					if (loginPanel.getComponentCount() > 2) {
						// Remove the previous error message
						loginPanel.removeComponent(loginPanel.getComponent(2));
					}
					// Add new error message
					Label error = new Label(
							"Usuário ou senha inválida. <span>Hint: tente valores vazios</span>",
							ContentMode.HTML);
					error.addStyleName("error");
					error.setSizeUndefined();
					error.addStyleName("light");
					// Add animation
					error.addStyleName("v-animate-reveal");
					loginPanel.addComponent(error);
					username.focus();
				}
			}
		});

		signin.addShortcutListener(enter);
		loginPanel.addComponent(fields);

		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
	}

	public void addLoginListener(LoginListener listener) {
		loginListeners.add(LoginListener.class, listener);
	}

	public void removeLoginListener(LoginListener listener) {
		loginListeners.remove(LoginListener.class, listener);
	}

	protected void fireOnLogin(LoginEvent loginEvent, boolean isLogin) {
		Object[] listeners = loginListeners.getListenerList();
		int numListeners = listeners.length;
		for (int i = 0; i < numListeners; i += 2) {
			if (listeners[i] == LoginListener.class) {
					((LoginListener) listeners[i + 1]).onLogin(loginEvent);
			}
		}
	}

}

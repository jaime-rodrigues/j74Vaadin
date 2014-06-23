package com.j74.controles;

import javax.swing.event.EventListenerList;

import com.j74.listners.LoginEvent;
import com.j74.listners.LoginListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PageLogin extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private EventListenerList loginListeners = new EventListenerList();
	private String style;
	
	public PageLogin(String style) {
		super();
		this.style = style;
		buildLoginView();
	}

	private void buildLoginView() {
		setSizeFull();
		addStyleName(style);

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
					if (getComponentCount() > 2) {
						// Remove the previous error message
						removeComponent(getComponent(2));
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
					addComponent(error);
					username.focus();
				}
			}
		});

		signin.addShortcutListener(enter);
		addComponent(fields);
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

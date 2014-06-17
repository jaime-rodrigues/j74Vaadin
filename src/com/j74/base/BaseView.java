package com.j74.base;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class BaseView extends VerticalLayout implements View {

	private HorizontalLayout header = new HorizontalLayout();
	private final Label title = new Label(getTitulo());

	protected abstract String getTitulo();

	protected BaseView() {
		setSizeFull();
		addStyleName("dashboard-view");

		header.setWidth("100%");
		header.setSpacing(true);
		header.addStyleName("toolbar");
		addComponent(header);
		title.setSizeUndefined();
		title.addStyleName("h1");
		header.addComponent(title);
		header.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		header.setExpandRatio(title, 1);
	}

	public HorizontalLayout getHeader() {
		return header;
	}

	public Label getTitle() {
		return title;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

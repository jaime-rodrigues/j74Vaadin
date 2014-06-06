package com.example.firstvaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class BaseView extends VerticalLayout implements View {

	protected HorizontalLayout top = new HorizontalLayout();
	protected final Label title = new Label(getTitulo());
	
	protected abstract String getTitulo();
	
	protected BaseView() {
		setSizeFull();
		addStyleName("dashboard-view");

		top.setWidth("100%");
		top.setSpacing(true);
		top.addStyleName("toolbar");
		addComponent(top);
		title.setSizeUndefined();
		title.addStyleName("h1");
		top.addComponent(title);
		top.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		top.setExpandRatio(title, 1);
}

		@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}


}

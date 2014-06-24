package com.j74.base;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class BaseView extends VerticalLayout implements View {

	protected abstract String getTitulo();
	private HorizontalLayout controls = new HorizontalLayout();

	protected BaseView() {
		setSizeFull();
		addStyleName("dashboard-view");
		
		controls.setSizeUndefined();
		addComponent(controls);
	}	
		
	public void setControls(HorizontalLayout controls) {
        replaceComponent(this.controls, controls);
		this.controls = controls;
	}

	protected HorizontalLayout getControls() {
		return controls;
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

package com.example.firstvaadin;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class BaseListView extends BaseView {

	protected CssLayout panel;
	
	public BaseListView() {
		super();
		
        HorizontalLayout row = new HorizontalLayout();
        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1.5f);

        panel = Utils.CreatePanel(new VerticalLayout());
        panel.addStyleName("notes");
        row.addComponent(panel);
	}

	@Override
	protected String getTitulo() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

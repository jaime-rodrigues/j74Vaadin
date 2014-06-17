package com.j74.base;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class BaseListView extends BaseView {

	private HorizontalLayout row;
	
	public HorizontalLayout getRow() {
		return row;
	}

	public BaseListView() {
		super();
		
        row = new HorizontalLayout();
        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1.5f);

	}

	@Override
	protected String getTitulo() {
		return null;
	}
	
}

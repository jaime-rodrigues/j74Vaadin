package com.j74.base;

import com.j74.utils.Utils;
import com.lexaden.grid.PagedTable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class BaseListView extends BaseView {

	private PagedTable table = new PagedTable();
	private HorizontalLayout row = new HorizontalLayout();
	public BaseListView() {
		super();

        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1.5f);

		table.addStyleName(Reindeer.TABLE_BORDERLESS);
		table.setColumnCollapsingAllowed(true);
		table.setColumnReorderingAllowed(true);
		table.setSelectable(true);
        
        CssLayout panel = Utils.CreateSimplePanel(table);
        row.addComponent(panel);
        setControls(table.createControls());
	}

	@Override
	protected String getTitulo() {
		return null;
	}

	public PagedTable getTable() {
		return table;
	}
	protected void addControll(Component c) {
		getControls().addComponent(c);
		getControls().setComponentAlignment(c, Alignment.MIDDLE_RIGHT);
	}
}

package org.quickstart.TFMJ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private final View m_view;
	private final Model m_model;
	
	public Controller() {

        this.m_model = new Model(1);
        this.m_view = new View(m_model.getDirectives(), m_model.getPDFs());
        
        this.m_view.addListenerDirectivesDropdown(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		m_model.loadXML("Directives/" + m_view.getDirectiveSelected());
        		m_view.updateTestsAvailable(m_model.getTestNames());
        	}
        });
        
        this.m_view.addListenerStartTest(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.out.println(m_model.startTest(m_view.getPDFSelected(), m_view.getTestSelected()));
        	}
        });
        
        m_view.setVisible(true);
	}
}

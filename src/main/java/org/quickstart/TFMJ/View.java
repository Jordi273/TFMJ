package org.quickstart.TFMJ;

//import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class View extends JFrame{
    
	private JMenuItem m_about;
	private JMenuItem m_github;
	
    private JButton m_folderButton;
    private JList<String> m_pdfList;
    private JComboBox<String> m_directivesDropdown;
    private JList<String> m_testList;
    private JButton m_startTestButton;
    
    public View(String[] baseDir, String[] basePDF) {
        setTitle("Document Policy Validator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initializateBar();
        
        setLayout(new GridBagLayout());
        
        initializeComponents(baseDir, basePDF);
        
        setVisible(true);
    }
    
    private void initializateBar() {
    	JMenuBar menuBar = new JMenuBar();
    	
    	//Help
    	JMenu helpMenu = new JMenu("Help");
    	m_about = new JMenuItem("About");
    	m_github = new JMenuItem("GitHub repository");
        helpMenu.add(m_about);
        helpMenu.add(m_github);
        
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void initializeComponents(String [] baseDir, String[] basePDF) {
    	
    	//Left side
    	
    	JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
    	GridBagConstraints leftGBC = new GridBagConstraints();
    	
    	//Folder button
        m_folderButton = new JButton("Choose folder");
        
        //Pdf list
        
        m_pdfList = new JList<String>(basePDF);
        
        //Left side positioning
                
        leftGBC.gridx = 0;
        leftGBC.gridy = 0;
        leftGBC.insets = new Insets(10, 10, 10, 10);
        leftGBC.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(m_folderButton, BorderLayout.NORTH);

        leftGBC.gridy = 1;
        leftPanel.add(m_pdfList, BorderLayout.CENTER);

        leftGBC.gridx = 0;
        leftGBC.gridy = 1;
        leftGBC.weightx = 0.3;
        leftGBC.fill = GridBagConstraints.BOTH;
        add(leftPanel, leftGBC);
        
    	//Right side
    	
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rightGBC = new GridBagConstraints();
        
        //Dropdown loaded directives
        
        String[] directives = {};
        m_directivesDropdown = new JComboBox<>(directives);
        m_directivesDropdown.setPreferredSize(new Dimension(200, 30));
        
        loadDirectives(baseDir);
        
        //Test list
        
        m_testList = new JList<String>(basePDF);
        
        //Start test
        
        m_startTestButton = new JButton("Start test");

        //Right side positioning
        
        rightGBC.gridx = 1;
        rightGBC.gridy = 0;
        rightGBC.insets = new Insets(10, 10, 10, 10);
        rightGBC.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(m_directivesDropdown, rightGBC);

        rightGBC.gridy = 1;
        rightPanel.add(m_testList, rightGBC);
        
        rightGBC.gridy = 2;
        rightPanel.add(m_startTestButton, rightGBC);

        rightGBC.gridx = 1;
        rightGBC.gridy = 1;
        rightGBC.weightx = 0.3;
        rightGBC.fill = GridBagConstraints.BOTH;
        add(rightPanel, rightGBC);
        

    }
    
    public void loadDirectives(String[] directives) {
    	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    	for (String dir : directives) {
    		model.addElement(dir);
    	}
    		
    	m_directivesDropdown.setModel(model);
    }
    
    public void addListenerDirectivesDropdown(ActionListener listener) {
    	m_directivesDropdown.addActionListener(listener);
    }
    
    public void addListenerStartTest(ActionListener listener) {
    	m_startTestButton.addActionListener(listener);
    }
    
    public String getDirectiveSelected() {
    	return m_directivesDropdown.getSelectedItem().toString();
    }
    
    public String getPDFSelected() {
    	return m_pdfList.getSelectedValue();
    }
    
    public String getTestSelected() {
    	return m_testList.getSelectedValue();
    }
    
    public void updateTestsAvailable(String[] newTests) {
    	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    	for (String test : newTests) {
    		model.addElement(test);
    	}
    		
    	m_testList.setModel(model);
    }
}

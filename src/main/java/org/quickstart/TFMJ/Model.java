package org.quickstart.TFMJ;

import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class Model {
	private final int m_a;
	private String m_pathToDirectiveFiles = "Directives";
	private String m_pathToPDFs = "PDFs";
	
	private Document m_xmlDirective;
	
	public Model(int a) {
		this.m_a=a;
	}
	
	public int getA() {
		return m_a;
	}
	
	public String[] getDirectives() {
		return getFileNames(m_pathToDirectiveFiles, ".xml");
	}
	public String[] getPDFs() {
		return getFileNames(m_pathToPDFs, ".pdf");
	}
	
	private String[] getFileNames(String path, String extension) {
		
		File folder = new File (path);
		
		if (!folder.exists() || !folder.isDirectory()) {
			return null;
		}
		
		File[] filesFound = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(extension));
		
		if (filesFound == null || filesFound.length == 0) {
			return null;
		}
		
		ArrayList<String> fileNames = new ArrayList<>();
		
		for (File f : filesFound) {
			fileNames.add(f.getName());
		}
		
		return fileNames.toArray(new String [0]);
	}
	
	public void loadXML(String xmlFile) {
		try {
			File xml = new File(xmlFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			m_xmlDirective = builder.parse(xml);
			m_xmlDirective.getDocumentElement().normalize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] getTestNames() {
		ArrayList<String> testNames = new ArrayList<>();
		
		NodeList partsList = m_xmlDirective.getElementsByTagName("part");
		
		for (int i = 0; i < partsList.getLength(); i++) {
			Node partNode = partsList.item(i);
			
			if (partNode.getNodeType() == Node.ELEMENT_NODE) {
				Element part = (Element) partNode;
				String partName = part.getAttribute("name");
				
				NodeList subpartsList = part.getElementsByTagName("test");
				
				for(int j = 0; j < subpartsList.getLength(); j++) {
					Node subpartNode = subpartsList.item(j);
					
					if (subpartNode.getNodeType() == Node.ELEMENT_NODE) {
						Element subpart = (Element) subpartNode;
						String subpartName = subpart.getAttribute("name");
						
						if(!subpartName.isEmpty()) {
							testNames.add(partName + " : " + subpartName);
						}
					}
				}
			}
		}
		
		return testNames.toArray(new String[0]);
	}
	
	public float startTest (String pdfToTest, String testName) {
		float testRes = -1;
		if (pdfToTest != null && testName != null) {
			testRes = 1f;
		}
		return testRes;
	}
}

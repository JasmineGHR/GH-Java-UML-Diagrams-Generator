package org.mql.java.reflections;



import org.mql.java.models.Project;
import org.mql.java.relations.Relation;
import org.mql.java.ui.DiagramGenerator;
import org.mql.java.xml.dom.ProjetParser;
import org.mql.java.xml.dom.XMIExporter;
import org.mql.java.xml.dom.XMLSerializer;

public class Test {

	
	public Test() {
		exp01() ;
		exp02() ;
		exp03() ;
		exp04();
		}
  private void exp04() {
		DiagramGenerator diagram= new DiagramGenerator();
		diagram.setVisible(true);
		
	}
  
  private void exp03() {
	  ProjetParser parser=new ProjetParser() ;
      Project proj=parser.parse("output/projet.xml") ;
	    for (org.mql.java.models.Package pak : proj.getPackages()) {
	    	System.out.println(pak.getName());
	    	for (Class<?> clas : pak.getListClass()) {
				System.out.println("class "+clas.getName());					
			}	
	    	for (Class<?> clas : pak.getListInterface()) {
				System.out.println("interface "+clas.getName());					
			}	
	    	for (Class<?> clas : pak.getListAnnot()) {
				System.out.println("annotation "+clas.getName());					
			}
	    	for (Class<?> clas : pak.getListEnum()) {
				System.out.println("enumeration "+clas.getName());					
			}
		}
	    for (Relation rela : proj.getRelations()) {
	    	System.out.println(rela.toString());
	    	System.out.println(rela.getName());
			
		}
	}
private void exp02() {
	
	    Project projet=new Project("C:\\Users\\af\\eclipse-workspace\\ma-workspace\\P05-MuliThreading\\bin");
		projet.ProjetInfo();
		XMLSerializer serializer= new XMLSerializer() ;
	    serializer.serializeToXML(projet, "output/projet.xml");		
	    XMIExporter xmi=new XMIExporter();
	    xmi.exportToXMI(projet, "output/proj.xmi") ;
	}
private void exp01() {
	 Project projet=new Project("C:\\Users\\af\\eclipse-workspace\\ma-workspace\\P05-MuliThreading\\bin");
	  //C:\\Users\\af\\eclipse-workspace\\ma-workspace\\p04-XML Parser\\bin"
	  //C:\\Users\\af\\eclipse-workspace\\ma-workspace\\p03-Annotations and Reflections\\bin
	  projet.ProjetInfo();
		
	}
public static void main(String[] args) {
	new Test() ;
	 
	  

}

}

package org.mql.java.reflections;


import org.mql.java.models.Project;
import org.mql.java.xml.dom.ProjetParser;
import org.mql.java.xml.dom.XMLSerializer;

public class Test {

	
	public Test() {
		Project p=new Project("C:\\\\Users\\\\af\\\\eclipse-workspace\\\\ma-workspace\\\\p01-revision\\\\bin");
		
		for (org.mql.java.models.Package data : p.getPackages()) {
				//System.out.println(data.getName());
				for (Class<?> clas : data.getListClass()) {
					System.out.println(clas.getName());					
				}
				for (Class<?> clas : data.getListInterface()) {
					System.out.println(clas.getName());					
				}	
				for (Class<?> clas : data.getListAnnot()) {
					System.out.println(clas.getName());					
				}	
//				for (Relation rela : data.getRelations()) {
//					System.out.println(rela.toString());
//					
//				}
		}
		}
  public static void main(String[] args) {
	  Project projet=new Project("C:\\Users\\af\\eclipse-workspace\\ma-workspace\\p04-XML Parser\\bin");
	  //C:\\Users\\af\\eclipse-workspace\\ma-workspace\\p03-Annotations and Reflections\\bin
	  projet.ProjetInfo();
	  XMLSerializer serializer= new XMLSerializer() ;
      serializer.serializeToXML(projet, "output/projet.xml");
	  //serializer.serializeToXML(projet);
      
      ProjetParser parser=new ProjetParser() ;
      Project proj=parser.parse("output/projet.xml") ;
	    for (org.mql.java.models.Package pak : proj.getPackages()) {
	    	System.out.println(pak.getName());
	    	for (Class<?> clas : pak.getListClass()) {
				System.out.println(clas.getName());					
			}
			
		}
      
//      int[] tableauEntiers = new int[5];
//      Class<?> typeComposant = tableauEntiers.getClass().getComponentType();
//      System.out.println("Type des composants du tableau : " + typeComposant.getName());
//      
//	  new test();
}

}

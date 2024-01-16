package org.mql.java.reflections;

import org.mql.java.elements.Relation;
import org.mql.java.xml.dom.XMLSerializer;

public class test {

	
	public test() {
		ProjectExtractor p=new ProjectExtractor("C:\\\\Users\\\\af\\\\eclipse-workspace\\\\ma-workspace\\\\p01-revision\\\\bin");
		
		for (org.mql.java.elements.Package data : p.getPackages()) {
				System.out.println(data.getName());
				for (Class<?> clas : data.getListClass()) {
					System.out.println(clas.getName());					
				}
				for (Class<?> clas : data.getListInterface()) {
					System.out.println(clas.getName());					
				}	
				for (Class<?> clas : data.getListAnnot()) {
					System.out.println(clas.getName());					
				}	
				for (Relation rela : data.getRelations()) {
					System.out.println(rela.toString());
					
				}
		}
		}
  public static void main(String[] args) {
	  ProjectExtractor projet=new ProjectExtractor("C:\\\\Users\\\\af\\\\eclipse-workspace\\\\ma-workspace\\\\p01-revision\\\\bin");

	  XMLSerializer serializer= new XMLSerializer() ;
      serializer.serializeToXML(projet, "output/output.xml");
	  //new test();
}

}

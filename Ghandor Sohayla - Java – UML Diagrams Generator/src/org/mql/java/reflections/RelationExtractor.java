package org.mql.java.reflections;

import java.util.List;

import org.mql.java.elements.Package;
import org.mql.java.elements.Relation;
import org.mql.java.relations.Agregation;
import org.mql.java.relations.Extention;
import org.mql.java.relations.Use;

public class RelationExtractor {

	public RelationExtractor() {
		// TODO Auto-generated constructor stub
	}
	
	public static void extractRelations(Class<?> clas,Package pack) {
	
		for (Class<?> claz : clas.getClasses()) {
			Relation use=new Use("use",clas,claz);
			pack.getRelations().add(use);
		}
			if (clas.getComponentType()!= null) {
				Relation agre=new Agregation("agregation",clas, clas.getComponentType().getClass()) ;
				pack.getRelations().add(agre);
				//struct.add("agregation :"+clas.getComponentType().getName());
			}
			if (clas.getSuperclass()!=null) {
				Relation ext=new Extention("extention", clas, clas.getSuperclass()) ;
				pack.getRelations().add(ext) ;
			}
		
		
		
		
		
}

}

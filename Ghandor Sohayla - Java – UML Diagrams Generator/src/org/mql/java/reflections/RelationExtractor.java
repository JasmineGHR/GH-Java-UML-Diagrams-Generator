package org.mql.java.reflections;



import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Vector;

import org.mql.java.elements.Package;


import org.mql.java.relations.*;


public class RelationExtractor {

	public RelationExtractor() {
		// TODO Auto-generated constructor stub
	}
	
	public static void extractRelations(Class<?> clas,Package pack) {
	
//		for (Class<?> claz : clas.getClasses()) {
//			Relation use=new Use("use",clas,claz);
//			pack.getRelations().add(use);
//		}
//			if (clas.getComponentType()!= null) {
//				Relation agre=new Agregation("agregation",clas, clas.getComponentType().getClass()) ;
//				pack.getRelations().add(agre);
//				//struct.add("agregation :"+clas.getComponentType().getName());
//			}
//			if (clas.getSuperclass()!=null) {
//				Relation ext=new Extention("extention", clas, clas.getSuperclass()) ;
//				pack.getRelations().add(ext) ;
//			}
		for (Field field : clas.getDeclaredFields()) {
			//System.out.println(field.getType().getName()+" "+Vector.class.isAssignableFrom(field.getType()));
			if (field.getType().isArray() || Vector.class.isAssignableFrom(field.getType()) 
					|| LinkedList.class.isAssignableFrom(field.getType())) {
				
				Relation agre=new Agregation("agregation",clas, field.getType()) ;
				pack.getRelations().add(agre);
				
			}
			else {
				Relation use=new Use("use",clas,field.getType());
				pack.getRelations().add(use);
			}
			
		}
			if (clas.getSuperclass()!=null) {
				Relation ext=new Extention("extention", clas, clas.getSuperclass()) ;
				pack.getRelations().add(ext) ;
			}
			
}

}

package org.mql.java.reflections;



import java.lang.reflect.Field;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


import org.mql.java.elements.ProjectExtractor;
import org.mql.java.relations.*;


public class RelationExtractor {

	public RelationExtractor() {
		// TODO Auto-generated constructor stub
	}
	
//	public static void extractRelations(Class<?> clas,Package pack) {
//	

//		for (Field field : clas.getDeclaredFields()) {
//			//System.out.println(field.getType().getName()+" "+Vector.class.isAssignableFrom(field.getType()));
//			if (field.getType().isArray() || Vector.class.isAssignableFrom(field.getType()) 
//					|| LinkedList.class.isAssignableFrom(field.getType())) {
//				
//				Relation agre=new Agregation("agregation",clas, field.getType()) ;
//				pack.getRelations().add(agre);
//				
//			}
//			else {
//				Relation use=new Use("use",clas,field.getType());
//				pack.getRelations().add(use);
//			}
//			
//		}
//			if (clas.getSuperclass()!=null) {
//				Relation ext=new Extention("extention", clas, clas.getSuperclass()) ;
//				pack.getRelations().add(ext) ;
//			}
//			
//}
	
	public static void extractRelations(Class<?> clas, ProjectExtractor projet) {

		for (Field field : clas.getDeclaredFields()) {
			//System.out.println(field.getType().getName()+" "+Vector.class.isAssignableFrom(field.getType()));
			if (!field.getType().isPrimitive()) {
				if (field.getType().isArray() || Vector.class.isAssignableFrom(field.getType()) 
						|| LinkedList.class.isAssignableFrom(field.getType()) ) {
					//System.out.println(field.getType().getComponentType().getName());
					Relation agre=new Agregation("agregation",clas, getTypeParameter(field)) ;
					projet.getRelations().add(agre);
					
				}
				else {
					Relation use=new Use("use",clas,field.getType());
					projet.getRelations().add(use);
				}
			}
			
			
		}
			if (clas.getSuperclass()!=null) {
				Relation ext=new Extention("extention", clas, clas.getSuperclass()) ;
				projet.getRelations().add(ext) ;
			}
			
}
	private static Class<?> getTypeParameter(Field field) {
        Class<?> fieldType = field.getType();

        if (List.class.isAssignableFrom(fieldType)) {
            // Si le champ est une sous-classe de List, récupérez le type paramétré
            return (Class<?>) ((java.lang.reflect.ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        }

        return null;
    }

}

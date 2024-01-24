package org.mql.java.reflections;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;

import org.mql.java.models.Project;
import org.mql.java.relations.*;


public class RelationExtractor {

	public RelationExtractor() {
		
	}

	
	public static void extractRelations(Class<?> clas, Project projet) {

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
	/*
	 * methode pour recuperer le type d'un attribut s'il presente un array dans une classe
	 */
	private static Class<?> getTypeParameter(Field field) {
	    java.lang.reflect.Type fieldType = field.getGenericType();

	    if (fieldType instanceof ParameterizedType) {
	        ParameterizedType parameterizedType = (ParameterizedType) fieldType;
	        java.lang.reflect.Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

	        if (actualTypeArguments.length == 1) {
	            // Si le champ est une sous-classe de List, récupérez le type paramétré
	            return (Class<?>) actualTypeArguments[0];
	        } else if (actualTypeArguments.length == 2 && Map.class.isAssignableFrom(field.getType())) {
	            // Si le champ est une sous-classe de Map, récupérez le type paramétré de la valeur
	            return (Class<?>) actualTypeArguments[1];
	        }
	    } else if (fieldType instanceof Class && ((Class<?>) fieldType).isArray()) {
	        // Si le champ est un tableau, récupérez le type du composant du tableau
	        return ((Class<?>) fieldType).getComponentType();
	    } else if (field.getType().isArray()) {
	        // Si le champ est un tableau (type non paramétré), récupérez le type du composant du tableau
	        return field.getType().getComponentType();
	    }

	    return null;
	}

}

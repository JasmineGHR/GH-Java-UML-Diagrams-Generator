package org.mql.java.reflections;

import java.util.List;

public class ExtractRelations {

	public ExtractRelations() {
		// TODO Auto-generated constructor stub
	}
	
	public static void extractRelations(Class<?> clas,List<String> struct) {
	
		for (Class<?> claz : clas.getClasses()) {
			struct.add("Utilisation ;"+claz.getName());
			if (claz.getComponentType() != null) {
				struct.add("agregation :"+clas.getComponentType().getName());
			}
		}
		struct.add("extention ;"+clas.getSuperclass().getName());
		
		
		
}

}

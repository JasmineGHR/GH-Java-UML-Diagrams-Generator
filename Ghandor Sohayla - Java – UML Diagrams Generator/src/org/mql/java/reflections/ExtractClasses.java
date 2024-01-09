package org.mql.java.reflections;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import static org.mql.java.reflections.ExtractRelations.*;
public class ExtractClasses {

	public ExtractClasses() {
		// TODO Auto-generated constructor stub
	}
	public static void extractClasses(String packageName,List<String> struct) {
		String classPath=System.getProperty("java.class.path") ;
		String packageFolder= packageName.replace('.', '\\');
		File dir = new File(classPath+'\\'+packageFolder);
		File classes[]=dir.listFiles() ;
		List<Class<?>> clasList=new LinkedList<Class<?>>();
		for(File f: classes) {
			
			//System.out.println(packageName+"."+f.getName().replace(".class",""));
			
			try {
				
				Class<?> clas = Class.forName(packageName+"."+f.getName().replace(".class",""));
				clasList.add(clas);
				
				            if (!clas.isInterface() && !clas.isAnnotation() && !clas.isEnum()) {
				            	struct.add("classe :"+clas.getName());
				            	extractRelations(clas,struct);
							}
							if (clas.isInterface() && !clas.isAnnotation()) {
								struct.add("Interface :"+clas.getName());
								//System.out.println("inter");
							}
							if (clas.isAnnotation()) {
								struct.add("Annotation :"+clas.getName());
								//System.out.println("ann");
							}
							if (clas.isEnum()) {
								struct.add("Enumeration"+clas.getName());
								//System.out.println("enu");
							}
									      
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}

		}

		
	}

}

package org.mql.java.reflections;

import java.io.File;


import java.util.LinkedList;
import java.util.List;
import static org.mql.java.reflections.RelationExtractor.*;
public class ClassExtractor {

	public ClassExtractor() {
		// TODO Auto-generated constructor stub
	}
	public static void extractClasses(org.mql.java.elements.Package pkg, String chemin) {
		MyClassLoader classLoader = MyClassLoader.createCustomClassLoader(chemin) ;

		//String classPath=System.getProperty("java.class.path") ;
		String packageFolder= pkg.getName().replace('.', '\\');
		File dir = new File(chemin+'\\'+packageFolder);
		File classes[]=dir.listFiles() ;
		List<Class<?>> clasList=new LinkedList<Class<?>>();
		for(File f: classes) {
			
			//System.out.println(packageName+"."+f.getName().replace(".class",""));
			
			try {
				
				Class<?> clas = Class.forName(pkg.getName()+"."+f.getName().replace(".class",""), true, classLoader);
				clasList.add(clas);
				
				            if (!clas.isInterface() && !clas.isAnnotation() && !clas.isEnum()) {
				            	pkg.getListClass().add(clas) ;
//				            	struct.add("classe :"+clas.getName());
				            	extractRelations(clas,pkg);
							}
							if (clas.isInterface() && !clas.isAnnotation()) {
								//struct.add("Interface :"+clas.getName());
								pkg.getListInterface().add(clas) ;
								//System.out.println("inter");
								extractRelations(clas,pkg);
							}
							if (clas.isAnnotation()) {
								//struct.add("Annotation :"+clas.getName());
								pkg.getListAnnot().add(clas) ;
								//System.out.println("ann");
								extractRelations(clas,pkg);
							}
							if (clas.isEnum()) {
								//struct.add("Enumeration"+clas.getName());
								pkg.getListEnum().add(clas) ;
								extractRelations(clas,pkg);
							}
									      
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}

		}

		
	}

}

package org.mql.java.reflections;

import java.io.File;



import java.util.LinkedList;
import java.util.List;

import org.mql.java.models.Project;

import static org.mql.java.reflections.RelationExtractor.*;
public class ClassExtractor {

	public ClassExtractor() {
		// TODO Auto-generated constructor stub
	}
	public static void extractClasses(org.mql.java.models.Package pkg, Project projet) {
		MyClassLoader classLoader = MyClassLoader.createCustomClassLoader(projet.getChemin()) ;
		//System.out.println(projet.getChemin());

		//String classPath=System.getProperty("java.class.path") ;
		String packageFolder= pkg.getName().replace('.', '\\');
		File dir = new File(projet.getChemin()+'\\'+packageFolder);
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
				            	extractRelations(clas,projet);
							}
							if (clas.isInterface() && !clas.isAnnotation()) {
								//struct.add("Interface :"+clas.getName());
								pkg.getListInterface().add(clas) ;
								//System.out.println("inter");
								extractRelations(clas,projet);
							}
							if (clas.isAnnotation()) {
								//struct.add("Annotation :"+clas.getName());
								pkg.getListAnnot().add(clas) ;
								//System.out.println("ann");
								extractRelations(clas,projet);
							}
							if (clas.isEnum()) {
								//struct.add("Enumeration"+clas.getName());
								pkg.getListEnum().add(clas) ;
								extractRelations(clas,projet);
							}
									      
			} catch (ClassNotFoundException e) {
				System.out.println("ERROR");
				System.out.println(e.getMessage());
				
			}

		}

		
	}

}

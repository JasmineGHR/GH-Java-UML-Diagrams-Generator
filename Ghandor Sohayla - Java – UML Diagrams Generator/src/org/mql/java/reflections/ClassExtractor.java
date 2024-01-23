package org.mql.java.reflections;

import java.io.File;
import java.lang.annotation.Annotation;
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
		Package[] packages=classLoader.getDefinedPackages();
		
		if (classLoader.getDefinedPackages()!=null) {
			for (Package pack : classLoader.getDefinedPackages()) {
				for (Annotation cls : pack.getAnnotations()) {
					
				}
				
			}
		}else System.out.println("********************************NULL");
		
		System.out.println("**************");
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
				//clas.getPackage();
				//System.out.println(clas.getPackageName());
				//System.out.println("hiiiiiiii"+clas.getName());
				//clasList.add(clas);
				
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
				System.out.println("I AM HERE : "+e.getMessage());
				
			}

		}

		
	}

}

package org.mql.java.reflections;

import java.io.File;

import org.mql.java.models.Project;

import static org.mql.java.reflections.RelationExtractor.*;
public class ClassExtractor {

	public ClassExtractor() {
		
	}
	public static void extractClasses(org.mql.java.models.Package pkg, Project projet) {
		MyClassLoader classLoader = MyClassLoader.createCustomClassLoader(projet.getChemin()) ;

		String packageFolder= pkg.getName().replace('.', '\\');
		File dir = new File(projet.getChemin()+'\\'+packageFolder);
		File classes[]=dir.listFiles() ;
		for(File f: classes) {
			
					
			try {
				Class<?> clas = Class.forName(pkg.getName()+"."+f.getName().replace(".class",""), true, classLoader);
				if (!projet.getPackages2().contains(clas.getPackage())) {
					//System.out.println(clas.getPackage());
					projet.getPackages2().add(clas.getPackage()) ;
				}
				
				            if (!clas.isInterface() && !clas.isAnnotation() && !clas.isEnum()) {
				            	pkg.getListClass().add(clas) ;
				            	extractRelations(clas,projet);
							}
							if (clas.isInterface() && !clas.isAnnotation()) {
								pkg.getListInterface().add(clas) ;
								extractRelations(clas,projet);
							}
							if (clas.isAnnotation()) {
								pkg.getListAnnot().add(clas) ;
								extractRelations(clas,projet);
							}
							if (clas.isEnum()) {
								pkg.getListEnum().add(clas) ;
								extractRelations(clas,projet);
							}
									      
			} catch (ClassNotFoundException e) {
				//System.out.println(e.getMessage());
				//e.getStackTrace();
				
			}

		}

		
	}

}

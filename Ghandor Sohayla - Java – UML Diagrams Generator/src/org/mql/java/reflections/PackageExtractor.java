package org.mql.java.reflections;

import java.io.File;


import java.util.List;

import org.mql.java.models.Package;
import org.mql.java.models.Project;

import static org.mql.java.reflections.ClassExtractor.*;
public class PackageExtractor {

	
	public PackageExtractor() {
		
	}
	
	public static void  extractPackages(Project projet,List<Package> packages) {
	       
        File projectDir = new File(projet.getChemin());

        if (projectDir.exists() && projectDir.isDirectory()) {
            extractPackagesFromDirectory(projectDir, "",packages,projet);
        } else {
            System.err.println("Le répertoire du projet n'existe pas.");
        }
    }

	private static void extractPackagesFromDirectory(File directory, String parentPackage,List<Package> packages,Project projet) {
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                	String packageName = parentPackage + file.getName();
                    if (containsJavaFiles(file)) {       
                    	org.mql.java.models.Package pkg=new org.mql.java.models.Package(packageName);
                    	packages.add(pkg) ;
                    	//struct.add("package :"+packageName);
                    	extractClasses(pkg,projet);
                        File souspackage =caintainsSousPackage(file);
                        if(souspackage!=null) extractPackagesFromDirectory(file, packageName + ".",packages,projet);
                    }else  extractPackagesFromDirectory(file, packageName + ".",packages,projet);
                }
            }
        }
 
    }

    private static File caintainsSousPackage(File diro) {
    	File[] files = diro.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    return file;
                }
            }
        }
        return null;
	}

	private static boolean containsJavaFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if(file.isFile() && file.getName().endsWith(".class")) {
                    return true;
                }
            }
        }
        return false;
    }

}

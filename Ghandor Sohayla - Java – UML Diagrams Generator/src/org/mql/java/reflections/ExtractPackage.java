package org.mql.java.reflections;

import java.io.File;
import java.util.List;

import static org.mql.java.reflections.ExtractClasses.*;
public class ExtractPackage {

	public ExtractPackage() {
		// TODO Auto-generated constructor stub
	}
	
	public static void  extractPackages(String projectDirectory,List<String> struct) {
	       
        File projectDir = new File(projectDirectory);

        if (projectDir.exists() && projectDir.isDirectory()) {
            extractPackagesFromDirectory(projectDir, "",struct);
        } else {
            System.err.println("Le répertoire du projet n'existe pas.");
        }
    }

	private static void extractPackagesFromDirectory(File directory, String parentPackage,List<String> struct) {
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                	String packageName = parentPackage + file.getName();
                    if (containsJavaFiles(file)) {                        
                    	struct.add("package :"+packageName);
                    	extractClasses(packageName,struct);
                        File souspackage =caintainsSousPackage(file);
                        if(souspackage!=null) extractPackagesFromDirectory(file, packageName + ".",struct);
                    }else  extractPackagesFromDirectory(file, packageName + ".",struct);
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

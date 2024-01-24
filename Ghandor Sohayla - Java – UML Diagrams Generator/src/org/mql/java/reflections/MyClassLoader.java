package org.mql.java.reflections;


import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Manifest;
import java.io.File;

public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public static MyClassLoader createCustomClassLoader(String projectPath) {
        try {
            URL projectUrl = new File(projectPath).toURI().toURL();
            return new MyClassLoader(new URL[]{projectUrl}, MyClassLoader.class.getClassLoader());
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du CustomClassLoader : " + e.getMessage());
            return null;
        }
    }
    
    @Override
    protected Package definePackage(String name, Manifest man, URL url) {
    	// TODO Auto-generated method stub
    	return super.definePackage(name, man, url);
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
    	// TODO Auto-generated method stub
    	return super.findClass(name);
    }
    
    
}


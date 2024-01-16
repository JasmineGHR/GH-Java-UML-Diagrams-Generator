package org.mql.java.reflections;


import java.net.URL;
import java.net.URLClassLoader;
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
}


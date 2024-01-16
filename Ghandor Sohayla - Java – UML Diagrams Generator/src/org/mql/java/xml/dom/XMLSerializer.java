package org.mql.java.xml.dom;
import org.mql.java.reflections.ProjectExtractor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.List;

public class XMLSerializer {
   
    public void serializeToXML(ProjectExtractor projet, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Créer le document XML
            Document doc = docBuilder.newDocument();

            // Élément racine
            Element rootElement = doc.createElement("Project");
            doc.appendChild(rootElement);
            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(projet.getChemin()));
            rootElement.appendChild(name);
            Element packages = doc.createElement("Packages");
            rootElement.appendChild(packages);

            // Ajouter des éléments pour chaque package et ses classes
            for (org.mql.java.elements.Package pkg : projet.getPackages()) {
                Element packageElement = doc.createElement("Package");
                packages.appendChild(packageElement);

                Element packageNameElement = doc.createElement("PackageName");
                packageNameElement.appendChild(doc.createTextNode(pkg.getName()));
                packageElement.appendChild(packageNameElement);

                Element classesElement = doc.createElement("Classes");
                packageElement.appendChild(classesElement);

                System.out.println("jjjjjjjjjjj");
                // Ajouter des éléments pour chaque classe
                for (Class<?> className : pkg.getListClass()) {
                    Element classElement = doc.createElement("Class");
                    classElement.appendChild(doc.createTextNode(className.getName()));
                    classesElement.appendChild(classElement);
                    System.out.println("fffffffff");
                }
                for (Class<?> interfaceName : pkg.getListInterface()) {
                    Element InterfaceElement = doc.createElement("Interface");
                    InterfaceElement.appendChild(doc.createTextNode(interfaceName.getName()));
                    classesElement.appendChild(InterfaceElement);
                    System.out.println("fffffffff");
                }
                for (Class<?> annotationName : pkg.getListAnnot()) {
                    Element annotationElement = doc.createElement("Annotation");
                    annotationElement.appendChild(doc.createTextNode(annotationName.getName()));
                    classesElement.appendChild(annotationElement);
                    System.out.println("fffffffff");
                }
                for (Class<?> enumName : pkg.getListEnum()) {
                    Element enumElement = doc.createElement("Enumeration");
                    enumElement.appendChild(doc.createTextNode(enumName.getName()));
                    classesElement.appendChild(enumElement);
                    System.out.println("fffffffff");
                }
            }

            // Sauvegarder le contenu dans un fichier XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

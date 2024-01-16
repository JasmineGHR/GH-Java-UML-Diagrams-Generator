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
            Element rootElement = doc.createElement("Packages");
            doc.appendChild(rootElement);

            // Ajouter des éléments pour chaque package et ses classes
            for (org.mql.java.elements.Package pkg : projet.getPackages()) {
                Element packageElement = doc.createElement("Package");
                rootElement.appendChild(packageElement);

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

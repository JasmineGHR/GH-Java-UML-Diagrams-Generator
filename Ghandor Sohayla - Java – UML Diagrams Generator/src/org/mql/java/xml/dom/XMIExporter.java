package org.mql.java.xml.dom;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.mql.java.models.Project;
import org.mql.java.relations.Agregation;
import org.mql.java.relations.Extention;
import org.mql.java.relations.Relation;
import org.mql.java.relations.Use;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMIExporter {

    public void exportToXMI(Project project, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Créer le document XML
            Document doc = docBuilder.newDocument();

            // Élément racine (XMI)
            Element xmiRoot = doc.createElement("xmi:XMI");
            xmiRoot.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
            xmiRoot.setAttribute("xmlns:uml", "http://www.omg.org/spec/UML/20090901");
            xmiRoot.setAttribute("xmi:version", "2.1");
            doc.appendChild(xmiRoot);

            // Élément modèle UML
            Element umlModel = doc.createElement("uml:Class"); 
            // Utilisez un type de classe existant dans votre modèle            xmiRoot.appendChild(umlModel);

            // Élément pour chaque package
            for (org.mql.java.models.Package pkg : project.getPackages()) {
                Element umlPackage = doc.createElement("packagedElement");
                umlPackage.setAttribute("xmi:type", "uml:Package");
                umlPackage.setAttribute("name", pkg.getName());
                umlModel.appendChild(umlPackage);

                // Élément pour chaque classe
                for (Class<?> className : pkg.getListClass()) {
                    Element umlClass = doc.createElement("ownedMember");
                    umlClass.setAttribute("xmi:type", "uml:Class");
                    umlClass.setAttribute("name", className.getName());
                    umlPackage.appendChild(umlClass);
                }

                // Élément pour chaque interface
                for (Class<?> interfaceName : pkg.getListInterface()) {
                    Element umlInterface = doc.createElement("ownedMember");
                    umlInterface.setAttribute("xmi:type", "uml:Interface");
                    umlInterface.setAttribute("name", interfaceName.getName());
                    umlPackage.appendChild(umlInterface);
                }

                // Élément pour chaque annotation
                for (Class<?> annotationName : pkg.getListAnnot()) {
                    Element umlAnnotation = doc.createElement("ownedMember");
                    umlAnnotation.setAttribute("xmi:type", "uml:Class"); // Supposez que les annotations sont modélisées comme des classes
                    umlAnnotation.setAttribute("name", annotationName.getName());
                    umlPackage.appendChild(umlAnnotation);
                }

                // Élément pour chaque énumération
                for (Class<?> enumName : pkg.getListEnum()) {
                    Element umlEnumeration = doc.createElement("ownedMember");
                    umlEnumeration.setAttribute("xmi:type", "uml:Enumeration");
                    umlEnumeration.setAttribute("name", enumName.getName());
                    umlPackage.appendChild(umlEnumeration);
                }
            }
            
            // Élément pour chaque relation
            Element umlRelations = doc.createElement("packagedElement");
            umlRelations.setAttribute("xmi:type", "uml:Package");
            umlRelations.setAttribute("name", "Relations");
            umlModel.appendChild(umlRelations);

            for (Relation relation : project.getRelations()) {
                Element umlRelation = doc.createElement("ownedMember");

                if (relation instanceof Agregation) {
                    umlRelation.setAttribute("xmi:type", "uml:Association");
                    umlRelation.setAttribute("name", "Agregation_" + relation.hashCode());
                    // Ajoutez d'autres détails spécifiques à l'agrégation si nécessaire
                } else if (relation instanceof Use) {
                    umlRelation.setAttribute("xmi:type", "uml:Usage");
                    umlRelation.setAttribute("name", "Utilisation_" + relation.hashCode());
                    // Ajoutez d'autres détails spécifiques à l'utilisation si nécessaire
                } else if (relation instanceof Extention) {
                    umlRelation.setAttribute("xmi:type", "uml:Generalization");
                    umlRelation.setAttribute("name", "Extention_" + relation.hashCode());
                    // Ajoutez d'autres détails spécifiques à l'extension si nécessaire
                }

                // Ajoutez les classes liées à la relation
                umlRelation.appendChild(createClassifier(doc, "source", relation.getClas1().getName()));
                umlRelation.appendChild(createClassifier(doc, "target", relation.getClas2().getName()));

                umlRelations.appendChild(umlRelation);
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

    private Element createClassifier(Document doc, String role, String className) {
        Element classifier = doc.createElement("classifier");
        classifier.setAttribute("xmi:type", "uml:Class");
        classifier.setAttribute("role", role);
        classifier.setAttribute("xmi:id", className + "_id");
        classifier.setAttribute("xmi:version", "1.0");
        classifier.setAttribute("name", className);

        return classifier;
    }
}

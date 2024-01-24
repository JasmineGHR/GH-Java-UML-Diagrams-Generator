package org.mql.java.xml.dom;

import org.w3c.dom.Document;


import org.w3c.dom.Element;
import org.w3c.dom.Text;
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

public class XMLSerializer {
   
	//hhhhhhhh
    public void serializeToXML(Project projet, String filePath) {
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
            for (org.mql.java.models.Package pkg : projet.getPackages()) {
                Element packageElement = doc.createElement("Package");
                packages.appendChild(packageElement);

                Element packageNameElement = doc.createElement("PackageName");
                packageNameElement.appendChild(doc.createTextNode(pkg.getName()));
                packageElement.appendChild(packageNameElement);

                Element classesElement = doc.createElement("Classes");
                packageElement.appendChild(classesElement);

                // Ajouter des éléments pour chaque classe
                for (Class<?> className : pkg.getListClass()) {
                    Element classElement = doc.createElement("Class");
                    classElement.appendChild(doc.createTextNode(className.getName()));
                    classesElement.appendChild(classElement);
                }
                for (Class<?> interfaceName : pkg.getListInterface()) {
                    Element InterfaceElement = doc.createElement("Interface");
                    InterfaceElement.appendChild(doc.createTextNode(interfaceName.getName()));
                    classesElement.appendChild(InterfaceElement);
                }
                for (Class<?> annotationName : pkg.getListAnnot()) {
                    Element annotationElement = doc.createElement("Annotation");
                    annotationElement.appendChild(doc.createTextNode(annotationName.getName()));
                    classesElement.appendChild(annotationElement);
                }
                for (Class<?> enumName : pkg.getListEnum()) {
                    Element enumElement = doc.createElement("Enumeration");
                    enumElement.appendChild(doc.createTextNode(enumName.getName()));
                    classesElement.appendChild(enumElement);
                }
               

            }

            Element relations = doc.createElement("Relations");
            rootElement.appendChild(relations);
            for (Relation relation : projet.getRelations()) {
            	//System.out.println("relation");
            	if (relation!=null && relation instanceof Agregation) {
            		Element agregation = doc.createElement("Agregation");
            		relations.appendChild(agregation);
            		
            		Element agregated = doc.createElement("agregated");
            		agregation.appendChild(agregated);
                	//System.out.println("1"+relation.getClas1().getName());
            		Text agregatedText = doc.createTextNode(relation.getClas1().getName());
            		agregated.appendChild(agregatedText);
            		//System.out.println("2"+relation.getClas2().getName());
            		Element agregat = doc.createElement("agregat");
            		agregation.appendChild(agregat);
                	
            		Text agregatText = doc.createTextNode(relation.getClas2().getName());
            		agregat.appendChild(agregatText);
            		
				}
            	if (relation!=null &&  relation instanceof Use) {
            		Element utilisation = doc.createElement("Utilisation");
            		relations.appendChild(utilisation);
            		
            		Element class1 = doc.createElement("class1");
            		utilisation.appendChild(class1);
            		Text class1Text = doc.createTextNode(relation.getClas1().getName());
            		class1.appendChild(class1Text);
            		Element class2 = doc.createElement("class2");
            		utilisation.appendChild(class2);
            		Text class2Text = doc.createTextNode(relation.getClas2().getName());
            		class2.appendChild(class2Text);
					
				}
            	if (relation!=null &&  relation instanceof Extention) {
            		Element Extention = doc.createElement("Extention");
            		relations.appendChild(Extention);
            		
            		Element fils = doc.createElement("fils");
            		Extention.appendChild(fils);
            		Text filsText = doc.createTextNode(relation.getClas1().getName());
            		fils.appendChild(filsText);
            		Element parent = doc.createElement("parent");
            		Extention.appendChild(parent);
            		Text parentText = doc.createTextNode(relation.getClas2().getName());
            		parent.appendChild(parentText);	
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

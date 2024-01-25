package org.mql.java.xml.dom;


import org.mql.java.models.*;
import org.mql.java.models.Package;
import org.mql.java.reflections.MyClassLoader;
import org.mql.java.relations.Agregation;
import org.mql.java.relations.Extention;
import org.mql.java.relations.Relation;
import org.mql.java.relations.Use;

//nous allons implementer un paseur Dom
public class ProjetParser {

	public ProjetParser() {

	}
	public Project parse(String source) {
		
		XMLNode root=new XMLNode(source);
		String name=root.getChild("Name").getValue() ;	
		//System.out.println(name);
		Project projet=new Project(name) ;
		
		XMLNode data[] = root.getChildren();
		for (XMLNode node : data) {
			MyClassLoader classLoader=MyClassLoader.createCustomClassLoader(name);
			String nodeName=XMLNode.getNameChild(node.getNode());
			if (nodeName.equals("Packages")) {
				XMLNode[] xn = node.getChildren() ;
				for (XMLNode xmlNode : xn) {
					
					String packName=xmlNode.getChild("PackageName").getValue() ;
					Package pkg=new Package(packName) ;
					XMLNode[] xn2= xmlNode.getChildren() ;
					for (XMLNode xmlNode2 : xn2) {
						if (XMLNode.getNameChild(xmlNode2.getNode()).equals("Classes")) {
							for (XMLNode xmlNode3 : xmlNode2.getChildren()) {
								String classname= xmlNode3.getValue();
								
								Class<?> clas;
								try {
									clas = Class.forName(classname, true, classLoader);
									pkg.getListClass().add(clas) ;
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							}							
						}						
					}
					projet.getPackages().add(pkg)	;
				}			
			}	
			
			if (nodeName.equals("Relations")) {
				XMLNode[] xn = node.getChildren() ;
				for (XMLNode xmlNode : xn) {
					String relationName=XMLNode.getNameChild(xmlNode.getNode());
                    
					if (relationName=="Utilisation") {
					 Class<?> clas1;
					 Class<?> clas2;
					try {
						clas1 = Class.forName(xmlNode.getChild("class1").getValue(), true, classLoader);
						clas2 = Class.forName(xmlNode.getChild("class2").getValue(), true, classLoader);
						Relation usage=new Use(relationName, clas1, clas2) ;
						projet.getRelations().add(usage)	;
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					}
					if (relationName=="Extention") {
						 Class<?> clas1;
						 Class<?> clas2;
						try {
							clas1 = Class.forName(xmlNode.getChild("parent").getValue(), true, classLoader);
							clas2 = Class.forName(xmlNode.getChild("fils").getValue(), true, classLoader);
							Relation extention=new Extention(relationName, clas1, clas2) ;
							projet.getRelations().add(extention)	;
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}							
						}
					if (relationName=="Agregation") {
						 Class<?> clas1;
						 Class<?> clas2;
						try {
							clas1 = Class.forName(xmlNode.getChild("agregat").getValue(), true, classLoader);
							clas2 = Class.forName(xmlNode.getChild("agregated").getValue(), true, classLoader);
							Relation agre=new Agregation(relationName, clas1, clas2) ;
							projet.getRelations().add(agre)	;
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
						}		
					}
				}			
		}
		return projet ;	
	}

}

package org.mql.java.xml.dom;


import org.mql.java.models.*;
import org.mql.java.models.Package;
import org.mql.java.reflections.MyClassLoader;

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
			//System.out.println(XMLNode.getNameChild(node.getNode()));
			MyClassLoader classLoader=MyClassLoader.createCustomClassLoader(name);
			String nodeName=XMLNode.getNameChild(node.getNode());
			if (nodeName.equals("Packages")) {
				XMLNode[] xn = node.getChildren() ;
				for (XMLNode xmlNode : xn) {
					//System.out.println(XMLNode.getNameChild(xmlNode.getNode()));

					//System.out.println(XMLNode.getNameChild(xmlNode.getNode()));
                    //System.out.println("package");
					String packName=xmlNode.getChild("PackageName").getValue() ;
					//System.out.println(packName);
					Package pkg=new Package(packName) ;
					XMLNode[] xn2= xmlNode.getChildren() ;
					//System.out.println();
					for (XMLNode xmlNode2 : xn2) {
						//System.out.println("hih"+XMLNode.getNameChild(xmlNode2.getNode()));
						if (XMLNode.getNameChild(xmlNode2.getNode()).equals("Classes")) {
							for (XMLNode xmlNode3 : xmlNode2.getChildren()) {
								String classname= xmlNode3.getValue();
								
								Class<?> clas;
								try {
									clas = Class.forName(classname, true, classLoader);
									pkg.getListClass().add(clas) ;
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//System.out.println(XMLNode.getNameChild(xmlNode2.getNode()));
							
						}
						
					}
					projet.getPackages().add(pkg)	;
				}
				
			}	
			
//			if (nodeName.equals("Relations")) {
//				XMLNode[] xn = node.getChildren() ;
//				for (XMLNode xmlNode : xn) {
//					//System.out.println(XMLNode.getNameChild(xmlNode.getNode()));
//
//					//System.out.println(XMLNode.getNameChild(xmlNode.getNode()));
//                    //System.out.println("package");
//					String packName=xmlNode.getChild("PackageName").getValue() ;
//					//System.out.println(packName);
//					Package pkg=new Package(packName) ;
//					XMLNode[] xn2= xmlNode.getChildren() ;
//					//System.out.println();
//					for (XMLNode xmlNode2 : xn2) {
//						//System.out.println("hih"+XMLNode.getNameChild(xmlNode2.getNode()));
//						if (XMLNode.getNameChild(xmlNode2.getNode()).equals("Classes")) {
//							for (XMLNode xmlNode3 : xmlNode2.getChildren()) {
//								String classname= xmlNode3.getValue();
//								
//								Class<?> clas;
//								try {
//									clas = Class.forName(classname, true, classLoader);
//									pkg.getListClass().add(clas) ;
//								} catch (ClassNotFoundException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//							//System.out.println(XMLNode.getNameChild(xmlNode2.getNode()));
//							
//						}
//						
//					}
//					projet.getPackages().add(pkg)	;
//				}
//				
//			}	
			
		}
		return projet ;
		
	}

}

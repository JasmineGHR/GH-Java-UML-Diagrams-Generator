package org.mql.java.xml.dom;

import java.util.LinkedList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * Il sera utilisé par AuthorsParser
 * Cette classe est utilisé pour Faciliter l'usage DOM il est Réutilisable (Objectif=utiliser Les Classes Réutilisables)
 * **/

//cette Classe est une classe wrapper ou une fascade en design pattern
//Acherché le concept de fascade
public class XMLNode {
	
 private Node node;
 private XMLNode children[];//les elements
	
	
	
 public XMLNode(String source) {
  DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();//Définit une API d'usine qui permet aux applications d'obtenir un analyseur qui produit des arborescences d'objets DOM à partir de documents XML.
  /*Vérifier si notre fichier xml est valide avec dtd ou xsd
  si il est valide nice sinon il le valide factory.setValidating(true);
  On crée un parseur Validant.*/
  //factory.setValidating(true);//parseur validant
  try {
   DocumentBuilder builder = factory.newDocumentBuilder();//Définit l'API pour obtenir des instances de document DOM à partir d'un document XML. En utilisant cette classe, un programmeur d'application peut obtenir un document à partir de XML.
   Document document= builder.parse(source);//Analyse le contenu de l'URI donné en tant que document XML et renvoie un nouvel objet Document DOM. Une exception IllegalArgumentException est levée si l'URI est nul.
   //document.getDocumentElement();//obtient l'element
   //ou 
   //document.getDocumentElement(); =>Element on peut utiliser celle ci mais pour connaitre l'api en details

   Node node= document.getFirstChild();
   while (node.getNodeType()!= Node.ELEMENT_NODE) {
	   //System.out.println(node.getNodeName());
       node=node.getNextSibling();//le noeud frere 
   }
   
   setNode(node);
   
   //principale info sur node
   System.out.println(
     node.getNodeName()+" , "+
     node.getNodeType()+" , "+
     node.getNodeValue());
      System.out.println("Comment node: "+Node.COMMENT_NODE);
      System.out.println("Element node: "+Node.ELEMENT_NODE);
      System.out.println("Document type node: "+Node.DOCUMENT_TYPE_NODE);
  } catch (Exception e) {
   System.out.println("Erreur: "+e.getMessage());
  }
 }
	
 public XMLNode(Node node) {
  super();
  setNode(node);
 }
	
 private void setNode(Node node) {
  this.node=node; 
  extractChildren();
 }
	
 private void extractChildren() {
  NodeList list= node.getChildNodes();//Une NodeList qui contient tous les enfants de ce nœud. S'il n'y a pas d'enfants, il s'agit d'une NodeList contenant des non-nœuds.
  /*
   * for (int i = 0; i < list.getLength(); i++) {
    System.out.println(list.item(i).getNodeName());
   }
   */
  LinkedList<XMLNode> nodes =new LinkedList<XMLNode>();
  for (int i = 0; i < list.getLength(); i++) {
   if(list.item(i).getNodeType()==Node.ELEMENT_NODE) {   
    nodes.add(new XMLNode(list.item(i)));
   }
  }
  
  children=new XMLNode[nodes.size()];
  nodes.toArray(children);
  //System.out.println(children.length);
 }
	
 public Node getNode() {
  return node;
 }
	
 public String getName() {
  return node.getNodeName();//delegate method
 }
 public boolean isNamed(String name) {
  return node.getNodeName().equals(name);
 }
	
 public XMLNode[] getChildren() {
  return children;
 }
 public XMLNode getChild(String name) {
  for (XMLNode child : children) {
   //if(child.getNode().getNodeName().equals(name))
   //if(child.getName().equals(name)) {
   if(child.isNamed(name)) {
    return child;
   }
  }
  return null;
 }	
 public String getValue() {
  //node.getAttributes().item(0);//on utilise pas ça 
  //node.getAttributes().getNamedItem("");//c mieu
  Node child = node.getFirstChild();
  if(child != null && child.getNodeType() == Node.TEXT_NODE) {
   return child.getNodeValue();
  }
  return "";
 }
 
 public String getAttribute(String name) {
	 Node att=node.getAttributes().getNamedItem(name) ;
	 if(att==null) return "" ;
	 return att.getNodeValue() ;
	 
 }
 
 public int getIntAttribute(String name) {
	 String s = getAttribute(name) ;
	 int value=0 ;
	 try {
		 value=Integer.parseInt(s) ;
		
	} catch (NumberFormatException  e) {
		System.out.println(e.getMessage());
	}
	 //System.out.println(value);
	return value;
 }
 
 public static String getNameChild(Node node) {
	return node.getNodeName();
}
	
   
	
}
package org.mql.java.models;

import java.util.LinkedList;

import java.util.List;


public class Package {
	private String name ;
	private List<Class<?>> listClass ; 
	private List<Class<?>> listInterface ; 
	private List<Class<?>> listEnum ; 
	private List<Class<?>> listAnnot ; 
	//private List<Relation> relations ;

	public Package(String name) {
		this.name = name ;
		listClass=new LinkedList<Class<?>>();
		listInterface=new LinkedList<Class<?>>();
		listEnum=new LinkedList<Class<?>>();
		listAnnot=new LinkedList<Class<?>>();
		//relations=new LinkedList<Relation>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Class<?>> getListClass() {
		return listClass;
	}

	public void setListClass(List<Class<?>> listClass) {
		this.listClass = listClass;
	}

	public List<Class<?>> getListInterface() {
		return listInterface;
	}

	public void setListInterface(List<Class<?>> listInterface) {
		this.listInterface = listInterface;
	}

	public List<Class<?>> getListEnum() {
		return listEnum;
	}

	public void setListEnum(List<Class<?>> listEnum) {
		this.listEnum = listEnum;
	}

	public List<Class<?>> getListAnnot() {
		return listAnnot;
	}

	public void setListAnnot(List<Class<?>> listAnnot) {
		this.listAnnot = listAnnot;
	}
	
//	public List<Relation> getRelations() {
//		return relations;
//	}

	
}

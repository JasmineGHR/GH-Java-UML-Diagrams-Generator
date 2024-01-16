package org.mql.java.relations;

import org.mql.java.elements.Relation;

public class Extention extends Relation{

	private Class<?> fils ;
	private Class<?> parent ;
	public Extention() {
		// TODO Auto-generated constructor stub
	}
	public Extention(String name , Class<?> fils, Class<?> parent) {
		super(name, fils, parent);
		this.fils = fils;
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "Extention [fils=" + fils.getName() + ", parent=" + parent.getName() + "]";
	}
	
	

}

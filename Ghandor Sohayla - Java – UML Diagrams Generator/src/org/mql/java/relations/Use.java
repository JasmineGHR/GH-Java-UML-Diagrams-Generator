package org.mql.java.relations;

import org.mql.java.elements.Relation;

public class Use extends Relation{

	private Class<?> clas1 ;
	private Class<?> clas2 ;
	public Use() {
		// TODO Auto-generated constructor stub
	}
	public Use(String name, Class<?> clas1, Class<?> clas2) {
		super(name, clas1, clas2);
		this.clas1 = clas1;
		this.clas2 = clas2;
	}
	@Override
	public String toString() {
		return "Use [clas1=" + clas1.getName() + ", clas2=" + clas2.getName() + "]";
	}
	
	

}

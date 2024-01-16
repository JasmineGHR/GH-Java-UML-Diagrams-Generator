package org.mql.java.relations;


public class Agregation extends Relation{

	private Class<?> agregated ;
	private Class<?> agregate ;
	public Agregation() {
		// TODO Auto-generated constructor stub
	}
	public Agregation(String name ,  Class<?> agregated, Class<?> agregate) {
		super(name, agregated, agregate) ;
		this.agregated = agregated;
		this.agregate = agregate;
	}
	@Override
	public String toString() {
		return "Agregation [agregated=" + agregated.getName() + ", agregate=" + agregate.getName() + "]";
	}
	
	

}

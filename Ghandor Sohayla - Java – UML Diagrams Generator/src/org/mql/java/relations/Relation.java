package org.mql.java.relations;

public class Relation {
	
	private String name ;
	private Class<?> clas1 ;
	private Class<?> clas2 ;

	public Relation() {
		
	}

	public Relation(String name, Class<?> clas1, Class<?> clas2) {
		super();
		this.name = name;
		this.clas1 = clas1;
		this.clas2 = clas2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getClas1() {
		return clas1;
	}

	public void setClas1(Class<?> clas1) {
		this.clas1 = clas1;
	}

	public Class<?> getClas2() {
		return clas2;
	}

	public void setClas2(Class<?> clas2) {
		this.clas2 = clas2;
	}

	
	
	
	

}

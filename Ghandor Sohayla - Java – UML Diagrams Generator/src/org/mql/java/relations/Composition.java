package org.mql.java.relations;


public class Composition extends Relation{

	private Class<?> container ;
	private Class<?> component ;
	public Composition() {
		// TODO Auto-generated constructor stub
	}
	public Composition(String name, Class<?> container, Class<?> component) {
		super(name, container, component);
		this.container = container;
		this.component = component;
	}
	@Override
	public String toString() {
		return "Composition [component=" + container.getName() + ", component=" + component.getName() + "]";
	}

	
	
}

package org.mql.java.relations;

import org.mql.java.elements.Relation;

public class Composition extends Relation{

	private Class<?> composit ;
	private Class<?> compositer ;
	public Composition() {
		// TODO Auto-generated constructor stub
	}
	public Composition(String name, Class<?> composit, Class<?> compositer) {
		super(name, composit, compositer);
		this.composit = composit;
		this.compositer = compositer;
	}
	@Override
	public String toString() {
		return "Composition [composit=" + composit.getName() + ", compositer=" + compositer.getName() + "]";
	}

	
	
}

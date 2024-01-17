package org.mql.java.relations;


public class Agregation extends Relation{

	private Class<?> agregated ;
	private Class<?> agregat ;
	public Agregation() {
		// TODO Auto-generated constructor stub
	}
	public Agregation(String name ,  Class<?> agregated, Class<?> agregat) {
		super(name, agregated, agregat) ;
		this.agregated = agregated;
		this.agregat = agregat;
	}
	@Override
	public String toString() {
		return "Agregation [agregated=" + agregated.getName() + ", agregate=" + agregat.getName() + "]";
	}
	public Class<?> getAgregated() {
		return agregated;
	}
	public void setAgregated(Class<?> agregated) {
		this.agregated = agregated;
	}
	public Class<?> getAgregat() {
		return agregat;
	}
	public void setAgregat(Class<?> agregat) {
		this.agregat = agregat;
	}
	
	

}

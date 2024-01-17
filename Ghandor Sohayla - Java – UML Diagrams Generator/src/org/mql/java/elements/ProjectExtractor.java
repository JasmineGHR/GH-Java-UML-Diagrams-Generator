package org.mql.java.elements;


import static org.mql.java.reflections.PackageExtractor.*;

import java.util.LinkedList;
import java.util.List;

import org.mql.java.relations.Relation;


public class ProjectExtractor {
	
	private String chemin ;
	private List<Package> packages;
	private List<Relation> relations ;

	public ProjectExtractor(String chemin) {
		this.chemin= chemin;
		packages=new LinkedList<org.mql.java.elements.Package>() ;
		relations=new LinkedList<Relation>() ;
		extractPackages(this,packages);
		//extractRelations()
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public List<org.mql.java.elements.Package> getPackages() {
		return packages;
	}

	public void setPackages(List<org.mql.java.elements.Package> packages) {
		this.packages = packages;
	}
	public List<Relation> getRelations() {
		return relations;
	}
	
}

package org.mql.java.models;

import static org.mql.java.reflections.PackageExtractor.*;

import java.util.LinkedList;
import java.util.List;

import org.mql.java.relations.Relation;


public class Project {
	
	private String chemin ;
	private List<Package> packages;
	private List<Relation> relations ;

	public Project(String chemin) {
		this.chemin= chemin;
		packages=new LinkedList<Package>() ;
		relations=new LinkedList<Relation>() ;
		
	}
	
	public void ProjetInfo() {
		extractPackages(this,packages);
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public List<org.mql.java.models.Package> getPackages() {
		return packages;
	}

	public void setPackages(List<org.mql.java.models.Package> packages) {
		this.packages = packages;
	}
	public List<Relation> getRelations() {
		return relations;
	}
	
}

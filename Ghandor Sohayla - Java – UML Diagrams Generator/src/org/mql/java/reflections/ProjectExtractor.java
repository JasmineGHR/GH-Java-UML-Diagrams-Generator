package org.mql.java.reflections;
import org.mql.java.elements.Package;

import static org.mql.java.reflections.PackageExtractor.*;

import java.util.LinkedList;
import java.util.List;


public class ProjectExtractor {
	
	private String chemin ;
	private List<Package> packages;

	public ProjectExtractor(String chemin) {
		this.chemin= chemin;
		packages=new LinkedList<org.mql.java.elements.Package>() ;
		extractPackages(this.chemin,packages);
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
	
	
}

package org.mql.java.reflections;
import static org.mql.java.reflections.ExtractPackage.*;

import java.util.LinkedList;
import java.util.List;


public class ProjectExtractor {
	
	private String chemin ;
	private List<String> struct;

	public ProjectExtractor() {
		this.chemin= System.getProperty("java.class.path");
		struct = new LinkedList<String>();
		extractPackages(chemin,struct);
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	public List<String> getStruct() {
		return struct;
	}
	public void setStruct(List<String> struct) {
		this.struct = struct;
	}
	
}

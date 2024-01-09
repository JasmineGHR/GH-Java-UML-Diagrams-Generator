package org.mql.java.reflections;

public class test {

	
	public test() {
		ProjectExtractor p=new ProjectExtractor();
		
		for (String data : p.getStruct()) {
				System.out.println(data);
			
		}
		}
  public static void main(String[] args) {
   
	  new test();
}

}

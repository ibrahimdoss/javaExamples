package myStringHashMap;

public class Main {

	public static void main(String[] args) {
		MyStringHashMap capitals = new MyStringHashMap();
		
		capitals.put("T�rkiye", "Ankara");
		capitals.put("Almanya", "Berlin");
		capitals.put("ingiltere", "Londra");
		
		System.out.println(capitals.get("T�rkiye"));
		
		
		
	}

}

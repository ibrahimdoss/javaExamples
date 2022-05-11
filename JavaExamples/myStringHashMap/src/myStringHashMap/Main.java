package myStringHashMap;

public class Main {

	public static void main(String[] args) {
		MyStringHashMap capitals = new MyStringHashMap();
		
		capitals.put("Türkiye", "Ankara");
		capitals.put("Almanya", "Berlin");
		capitals.put("ingiltere", "Londra");
		
		System.out.println(capitals.get("Türkiye"));
		
		
		
	}

}

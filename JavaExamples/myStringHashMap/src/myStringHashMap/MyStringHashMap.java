package myStringHashMap;

public class MyStringHashMap {
	
	private String[] keys;
	private String[] myValues;
	
	public MyStringHashMap() {
		keys = new String [0];
		myValues= new String[0];
		
	}
	
	public void put(String key, String value) {
		int x= this.keys.length;
		String[] cloneKeys= new String[x+1];
		String[] cloneValues= new String[x+1];
		
		for (int i = 0; i < x; i++) {
			cloneKeys[i]=this.keys[i];
			cloneKeys[i]=this.myValues[i];

		}
		
		cloneKeys[x]= key;
		cloneValues[x]=value;
		
		this.keys=cloneKeys;
		this.myValues=cloneValues;

	}
	
	public String[] getList() {
		String[] array= new String[this.keys.length];
		for (int i = 0; i < this.keys.length; i++) {
			array[i]=this.keys[i]+ " = " + this.myValues[i];
		}
		
		return array;
	}
	
	public String get(String key) {
		for (int i = 0; i < keys.length; i++) {
			if (this.keys[1]==key) {
				return this.myValues[i];
			}
		}
		
		return null;
	}
	
	public void clear() {
		this.keys=new String[0];
		this.myValues=new String[0];
	}

	
	
	
	
	
	
	
	
}

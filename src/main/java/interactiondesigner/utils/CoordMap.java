package interactiondesigner.utils;

import java.util.HashMap;

public class CoordMap<K,V>{

	private HashMap<K,HashMap<K,V>> items;

	public CoordMap(){
		this(20);
	}

	public CoordMap(int size){
		items = new HashMap<>();
	}
	
	public HashMap<K,V> remove(K key){
		return items.remove(key);
	}

	public V remove(K x1, K x2){
		return items.get(x1).remove(x2);
	}

	public V get(K x1, K x2){
		if(!items.containsKey(x1)){
			return null;
		}
		if(!items.get(x1).containsKey(x2)){
			return null;
		}
		return items.get(x1).get(x2);
	}

	public void put(K x1, K x2, V value){
		if(!items.containsKey(x1)){
			items.put(x1, new HashMap<K,V>());			
		}
		items.get(x1).put(x2, value);
	}

}
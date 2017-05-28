package interactiondesigner.utils;

import java.util.HashMap;
import java.util.Set;

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

	public Set<K> keyset(K key){
		return items.get(key).keySet();
	}

	public Set<K> keyset(){
		return items.keySet();
	}

	public boolean containsKey(K key){
		return items.containsKey(key);
	}

	public boolean containsKey(K x1, K key){
		if(!containsKey(x1)){
			return false;
		}
		return items.get(x1).containsKey(key);
	}

	/**
	 * Return the key pairs that point to the value
	 */
	public K[] findCoords(V value){		
		for(K key1 : keyset()){
			for(K key2 : keyset(key1)){
				if(get(key1,key2) == (value)){
					Object[] keys = new Object[2];
					keys[0] = key1;
					keys[1] = key2;
					return (K[])keys;
				}	
			}
		}
		return null;
	}

	public void put(K x1, K x2, V value){
		if(!items.containsKey(x1)){
			items.put(x1, new HashMap<K,V>());			
		}
		items.get(x1).put(x2, value);
	}

}
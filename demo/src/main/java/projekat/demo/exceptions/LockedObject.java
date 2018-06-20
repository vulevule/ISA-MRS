package projekat.demo.exceptions;

import java.util.concurrent.ConcurrentHashMap;

public class LockedObject {
	private ConcurrentHashMap<String, Object> keys;
	
	public LockedObject(){
		keys = new ConcurrentHashMap<String, Object>();
	}
	
	public Object getObject(String id){
		if(keys.containsKey(id)){
			return keys.get(id);
		}
		keys.put(id, new Object());
		return keys.get(id);
	}
}

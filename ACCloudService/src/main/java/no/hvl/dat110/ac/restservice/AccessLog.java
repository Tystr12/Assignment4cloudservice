package no.hvl.dat110.ac.restservice;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry to the log for the provided message and return assigned id
	public int add(String message) {
		
		int id = generateID();
		
		this.log.put(id, new AccessEntry(id, message));
		
		return id;
	}
		
	// TODO: retrieve a specific access entry from the log
	public AccessEntry get(int id) {
		
		if(this.log.containsKey(id)) {
			return this.log.get(id);
		}
		return new AccessEntry(1, "There is no Entry with that ID");
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		this.log = new ConcurrentHashMap<Integer, AccessEntry>();
	}
	
	// TODO: return JSON representation of the access log
	public String toJson () {
    	
		String json = null;
		
		Gson gson = new Gson();
		json = gson.toJson(log);
    	
    	return json;
    }
	
	private int generateID() {
		String res = "";
		for(int i = 0; i < 8; i++) {
			Random random = new Random();
			res += String.valueOf(random.nextInt(9));
		}
		return Integer.parseInt(res);
	}
}

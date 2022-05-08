package no.hvl.dat110.ac.restservice;

import static spark.Spark.after;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;

import com.google.gson.Gson;

import spark.Route;
import spark.Spark;

/**
 * Hello world!
 *
 */
public class App {
	
	static AccessLog accesslog = null;
	static AccessCode accesscode = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		// objects for data stored in the service
		
		accesslog = new AccessLog();
		accesscode  = new AccessCode();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		// for basic testing purposes
		get("/accessdevice/hello", (req, res) -> {
			
		 	Gson gson = new Gson();
		 	
		 	return gson.toJson("IoT Access Control Device");
		});
		
		post("/accessdevice/log/", (req,res) -> {
			
			Gson gson = new Gson();
			
			String m = "message";
		 	
		 	accesslog.add(m);
		 	return gson.toJson(m);
			
		});
		
		get("/accessdevice/log/", (req, res) -> {
			
		 	Gson gson = new Gson();
		 	
		 	return gson.toJson(accesslog.toJson());
		});
		
		get("/accessdevice/log/{id}", (req,res) -> {
			
			Gson gson = new Gson();
			
			AccessEntry e = accesslog.get(req.attribute("id"));
			
			return gson.toJson(e);
		});
		
		put("/accessdevice/code", (req,res) -> {
			 int[] a = req.attribute("accesscode");
			 accesscode.setAccesscode(a);
			 
			 Gson g = new Gson();
			 return g.toJson(accesscode.getAccesscode());
			
		});
		
		get("/accessdevice/code", (req,res) -> {
			Gson g = new Gson();
			return g.toJson(accesscode.getAccesscode());
		});
		
		delete("/accessdevice/log/", (req,res) -> {
			accesslog.clear();
			
			return accesslog.toJson();
		});
		
	}
		

		// TODO: implement the routes required for the access control service
		// as per the HTTP/REST operations describined in the project description
		
	
		

	
    
}

package com.amazonaws.samples;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.amazonaws.services.dynamodbv2.document.Item;
public class ScanProduct implements HttpHandler {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	public static void main(String[] args) throws IOException {
		scanner();
	}

	private static ItemCollection<ScanOutcome> scanner() {
		Table table = dynamoDB.getTable("Product");
		ScanSpec scanSpec = new ScanSpec()
				.withProjectionExpression("Product_id,Brand,Description,Image,Price,Product_name");
		try {
			
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);
			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();

			}
			return items;
		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void handle(HttpExchange t) throws IOException {
		byte[] response = null;

		try {
			JSONObject obj=new JSONObject();
			obj=getJsonResponse();
			response = obj.toString().getBytes();
			//t.getResponseHeaders().set("Content-Type", "appication/json");
            t.sendResponseHeaders(200, response.length);
		
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			response = null;
			t.sendResponseHeaders(400, response.length);
			OutputStream os = t.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();

		}
		OutputStream os = t.getResponseBody();
		os.write(response);
		os.close();

	}
	public static JSONObject getProducts(Item items)
	{
		 JSONObject obj = new JSONObject();
		   try {
		      obj.put("Brand", items.get("Brand"));
		      obj.put("Description", items.get("Description"));
		      obj.put("Image", items.get("Image"));
		      obj.put("Price", items.get("Price"));
		      //obj.put("Product_id", items.get("Product_id"));
		      obj.put("Product_id", items.get("Product_id"));
		      obj.put("Product_name", items.get("Product_name"));
		   return obj ;
		   }
		    catch (JSONException e) 
		   {
		    	return null; 
		    }
		} 

	public JSONObject getJsonResponse(){
	    ItemCollection<ScanOutcome> res;
		JSONArray product = new JSONArray();
		JSONObject result=new JSONObject();
        JSONObject obj=new JSONObject();
            res = scanner();
        try {
	    Iterator<Item> iter = res.iterator();
			while (iter.hasNext())
		        {
				    Item items = iter.next();
		         	String temp=items.get("Product_id").toString();
				    result=getProducts(items);
	    	        product.put(result);
	    	        obj.put("Details",product);
	            }
			System.out.println();
			return obj;
	    }
	    catch (JSONException e) {return null; }
	    }
}

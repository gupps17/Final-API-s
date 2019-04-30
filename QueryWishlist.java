
/***Ritu Gupta
 To query the wishlist of a user
 */

package com.amazonaws.aws_java_sdk;

import java.io.IOException;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpHandler;
public class QueryWishlist {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	
    static DynamoDBMapper mapper = new DynamoDBMapper(client);


	static String tableName = "Wishlist";
	Table table = dynamoDB.getTable(tableName);
	public  JSONObject queryId(String id)
	{    int a = Integer.parseInt(id);			
		 
		
      	
	     
	     System.out.println(a);
	     	System.out.println("Attempting to read the item...");
	     	WishlistItem w = mapper.load(WishlistItem.class,a);
	     	
	     		JSONArray array = new JSONArray();
        	JSONObject item = new JSONObject();
        	try {
				item.put("Name", w.getName());
				item.put("Description", w.getDescription());
	        	item.put("Username",w.getUserName());
	        	
	        	return item;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("hell");
				return null;
			}
        	
       
	     		
	     		
	     	
	     	
	}
	public  JSONObject queryS(String id)
	{    int a = Integer.parseInt(id);			
		 
		
      	
	     
	     System.out.println(a);
	     	System.out.println("Attempting to read the item...");
	     	WishlistItem w = mapper.load(WishlistItem.class,a);
	     	System.out.println(w+"r");
	     		JSONArray array = new JSONArray();
        	JSONObject item = new JSONObject();
        	try {
				item.put("Name", w.getName());
				item.put("Description", w.getDescription());
	        	item.put("Username",w.getUserName());
	        	item.put("WId",w.getId());
	        	
	        	return item;
			} catch (JSONException e) {
				
				e.printStackTrace();
				System.out.println("hell");
				return null;
			}
	}
}

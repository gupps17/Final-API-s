/*
Ritu Gupta
To get the products for a wishlist
*/

package com.amazonaws.aws_java_sdk;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

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
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpHandler;
public class QueryProduct {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	
    static DynamoDBMapper mapper = new DynamoDBMapper(client);


	static String tableName = "Wishlist";
	Table table = dynamoDB.getTable(tableName);
	public  JSONObject queryPd(String id) throws JSONException
	{    int a = Integer.parseInt(id);			
		 
		
      	System.out.println(id);
	     
	     
	     	System.out.println("Attempting to read the item..."+"kkkk");
	     	WishlistItem w = mapper.load(WishlistItem.class,a);
	     	System.out.println(w.getDescription());
	     	Product p1=new Product();
	     	ArrayList<Product> PList=new ArrayList<Product>();
	     	ArrayList<Integer> PId=new ArrayList<Integer>();
	     	PList=w.getList();
	     	System.out.println(w.getList());
	     	int i=0;
	     	JSONArray array = new JSONArray();
	     	JSONObject item = new JSONObject();
	     	for(i=0;i<PList.size();i++)
	     	{System.out.println("yes");
	     		p1=PList.get(i);
	     		PId.add(p1.getPd());
	     		JSONObject s1=queryImg(p1.getPd(),p1.getQty());
	     		try {
					item.put(Integer.toString(i),s1);
				
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
	     		
	     	}
	     		
	     		
        	
        	
       
	     	return item;     		
	     		
	     	
	     	
	}
	public JSONObject queryImg(int id,int qty) throws JSONException
	{ System.out.println(id);
		JSONArray array=new JSONArray();
		JSONObject obj=new JSONObject();
		String tableName = "Product";
		Table table = dynamoDB.getTable(tableName);
		GetItemSpec spec=new GetItemSpec().withPrimaryKey("Product_id",id);
		Item outcome = table.getItem(spec);
		obj.put("Image",outcome.get("Image").toString());
		obj.put("Price",outcome.get("Price").toString());
		obj.put("Product_name",outcome.get("Product_name").toString());
		obj.put("Brand",outcome.get("Brand").toString());
		obj.put("Product_id",outcome.get("Product_id").toString());
		obj.put("Qty",Integer.toString(qty));
		
	
	
	return obj;
	}
}

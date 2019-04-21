/*
	  @author Amrutha Krishnamoorthy
	  API to query database provided wishlist ID
	  
*/

package com.amazonaws.samples;
import java.io.IOException;
import java.io.OutputStream;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class QueryWishlist {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "Wishlist";
	public static Item queryId(String id)
	{    int result = Integer.parseInt(id);			
		 Table table = dynamoDB.getTable(tableName);
		 GetItemSpec spec=new GetItemSpec().withPrimaryKey("Wishlist_id",result);
      	
	     try
	     {
	     	System.out.println("Attempting to read the item...");
	     	Item outcome = table.getItem(spec);
	     	if(outcome!=null)
	     	{
	     		System.out.println("Retrieving your wishlist: "+ outcome);
	     		return outcome;
	     	}
	     	else
	     		throw new Exception("Incorrect Wishlist ID");
	     }
	      catch(Exception e)
	      {
	     	 System.err.println("Unable to read item :");
	     	 System.err.println(e.getMessage());
	     	 return null;
	      }
	     }
	}


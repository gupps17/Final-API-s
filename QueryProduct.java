/*
	  @author Amrutha Krishnamoorthy
	  API to query database provided product ID
	  
*/




package com.amazonaws.samples;

import java.io.IOException;
import java.io.OutputStream;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class QueryProduct {
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "Product";

	Item queryPdt(String s1) {
		int res = Integer.parseInt(s1);	
		String response;
		Item result;
		Table table = dynamoDB.getTable(tableName);
        
   GetItemSpec spec=new GetItemSpec().withPrimaryKey("Product_id",res);
       	
   try
   {
   	System.out.println("Attempting to read the item...");
   	Item outcome = table.getItem(spec);
   	result=outcome;
   	if(outcome!=null)
   	{
   	System.out.println("Login success: "+ outcome);
   	 response = outcome.getString("UserName");
   	 return outcome;
   	}
   	else
   		throw new Exception("Incorrect username or password");
   }
    catch(Exception e)
    {
   	 System.err.println("Unable to read item :");
   	 System.err.println(e.getMessage());
    }
return(null);
   }     
   
}

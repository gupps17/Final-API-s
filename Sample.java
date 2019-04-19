
/**
 * @author Ritu Gupta
  Activity has methods to create the Wishlist
  and delete the Wishlist
  *
 */








package com.amazonaws.aws_java_sdk;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.impl.bootstrap.HttpServer;

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


// This client will default to US West (Oregon)


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


public class Sample  {
	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_1)
			.build();  
  
    static DynamoDB dynamoDB = new DynamoDB(client);
    static DynamoDBMapper mapper = new DynamoDBMapper(client);

    static String tableName = "user";
    static String tableName2="Wishlist";
    

    public static void main(String[] args) throws IOException {
    	//deleteItem();
    
    	//deletewishlist();
    	
    	
      
    }
    public static String deleteUser(String s1) {

        Table table = dynamoDB.getTable(tableName);

        try {

            DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("UserName", "Dhruv");

            DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

            // Check the response.
           
            return"User was deleted";
        	}
        catch (Exception e)
        {
            System.err.println("Error deleting item in " + tableName);
            System.err.println(e.getMessage());
            //throw e; 
            return "unsuccessfull";
        }
        
    }
    
    
    public static String createItems(String s1,String s2,String s3)
    {
    	
    
    	Table table2 = dynamoDB.getTable(tableName2);
    	
    	
        
        try {
        	
	        	WishlistItem w=new WishlistItem();
	        	Product p1=new Product();
	        	Product p2=new Product();
	        	Fullfiller f1=new Fullfiller();
	        	Fullfiller f2=new Fullfiller();
	        	Fullfiller f3=new Fullfiller();
	        	ArrayList<Product> plist=new ArrayList<Product>();
	        	ArrayList<Fullfiller> flist=new ArrayList<Fullfiller>();
	        	ArrayList<Fullfiller> flist2=new ArrayList<Fullfiller>();
	        	
	        	
	
	        	w.setDescription(s2);
	        	w.setId(Integer.parseInt(s3));
	        	w.setUserName(s1);
	        	
	        	
	        	
	        	mapper.save(w); 
	        	
	        	
	        	return "Wishlistcreated";

        	}
        
        catch (Exception e) {
            System.err.println("Create items failed.");
            System.err.println(e.getMessage());
            return "Create items failed";
            //e.printStackTrace();
            
           // throw e;
            

        }
        
    }
public static String deleteWishlist(String s1)
 {
	Table table = dynamoDB.getTable(tableName2);

    try {

        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("Wishlist_id", Integer.parseInt(s1));

        DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

        // Check the response.
       
       
        return "Deleted successfully";
    }
    
    catch (Exception e)
    {
        System.err.println("Error deleting item in " + tableName);
        System.err.println(e.getMessage());
        return"Error Deleting";
    }
  }


}


  
    	
    	
    	
    
    



        

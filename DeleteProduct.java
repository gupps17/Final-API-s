package com.amazonaws.aws_java_sdk;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
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

public class DeleteProduct {
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_1)
			.build();  
   // static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static DynamoDBMapper mapper = new DynamoDBMapper(client);

    static String tableName = "user";
    static String tableName2 = "Wishlist";

    public  String deleteP(String s1,String s2)
    {try 
    	{
    	
	    	int a=Integer.parseInt(s1);
	    	ArrayList<Integer> a1=new ArrayList<Integer>();
			WishlistItem item = mapper.load(WishlistItem.class,a);
			ArrayList<Product> PList=new ArrayList<Product>();
			ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
			Product p1=new Product();
			p1=item.getPrd(Integer.parseInt(s2),item);
			PList=item.getList();
			PList.remove(p1);
		
		
	
			item.setList(PList);
			mapper.save(item);
			
		    	
		    return"DeletedProduct";
    }
	catch(Exception e)
	{
		System.err.println(e.getMessage());
		return "Unable to Delete";
	}
	

  }
}

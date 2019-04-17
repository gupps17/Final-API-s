package com.amazonaws.aws_java_sdk;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Login
{
	
	 
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_1)
			.build();  
	  static DynamoDB dynamoDB = new DynamoDB(client);

	    static String tableName = "user";
    
	    public static void main(String[] args) throws IOException {
	    	 
	   
	    }
		
     public static boolean query(String s1,String s2)
     {
    	 try
    	 {
			System.out.println(s1);
			System.out.println(s2);
			Table table = dynamoDB.getTable(tableName);
			// s1=s1.substring(1,s1.length()-1);

			GetItemSpec spec = new GetItemSpec().withPrimaryKey("UserName", s1);
			Item item = table.getItem(spec);

			System.out.println(item.get("Password"));
			// String s3="\""+item.get("Password").toString()+"\"";
			String s3 = item.get("Password").toString();
    
     if(s2.contentEquals(s3))
     { 
    	 return true;
     }
     else
     {
    	 return false;
     }
     
    	 }
         	
     
      catch(Exception e)
      {
     	 System.err.println("Unable to read item :");
     	 System.err.println(e.getMessage());
     	 return false;
     	
      }
     
     }
     
  }
    
     
     
     
    
   


/**
 * @author Ritu Gupta
  Activity has methods to update the wishlist
  which contains
   updating product quantity
  updating Fullfiller Qty
  adding fullfiller
  deleting a fullfiller
  adding a product
  deleting a product
  updating fullfiller Qty
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


public class Update2  {
	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_1)
			.build();  
   // static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static DynamoDBMapper mapper = new DynamoDBMapper(client);

    static String tableName = "user";
    static String tableName2 = "Wishlist";
    

    public static void main(String[] args) throws IOException {
    	try {
	    		ArrayList<Integer> a1=new ArrayList<Integer>();
	    		WishlistItem item = mapper.load(WishlistItem.class,97);
	    		ArrayList<Product> PList=new ArrayList<Product>();
	    		ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
	    		
	    		Fullfiller f1=new Fullfiller();
	    		Product p1=new Product();
	    		
    		    		
    		
    	
    		}
    	catch(Exception e)
    	{   
    		System.err.println(e.getMessage());
    		throw e;
    	}
    }
    //adding new product
    public  String addPrd(String s1,String s2,String s3,String s4,String s5,String s6)
    {
    	try 
		    {
		    	int a=Integer.parseInt(s4);
		    	ArrayList<Integer> a1=new ArrayList<Integer>();
				WishlistItem item = mapper.load(WishlistItem.class,a);
				ArrayList<Product> PList=new ArrayList<Product>();
				ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
				Product p1=new Product();
				Fullfiller f1=new Fullfiller();
				f1.setid(Integer.parseInt(s5));
				f1.setQty(Integer.parseInt(s6));
				FList=p1.getFList();
				FList.add(f1);
				p1.setFList(FList);
				p1.setPd(Integer.parseInt(s1));
				p1.setDate(s3);
				p1.setQty(Integer.parseInt(s2));
		
				PList=item.getList();
				
				PList.add(p1);
				item.setList(PList);
		
			
				mapper.save(item);

    	
    	return"addedProduct";
		    }
    catch(Exception e)
    {System.err.println(e.getMessage());
    return "Unable to add";
    }
    	
    
    }
    
    //deleting a product
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
    
   //adding a fullfiller 
    public  String addF(String s1,String s2,String s3,String s4)
    {
    	try {
		    
		    	int a=Integer.parseInt(s1);
		    	ArrayList<Integer> a1=new ArrayList<Integer>();
				WishlistItem item = mapper.load(WishlistItem.class,a);
				ArrayList<Product> PList=new ArrayList<Product>();
				ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
				Product p1=new Product();
				Fullfiller f1=new Fullfiller();
				f1.setid(Integer.parseInt(s3));
				f1.setQty(Integer.parseInt(s4));
				p1=item.getPrd(Integer.parseInt(s2),item);
				FList=p1.getFList();
				FList.add(f1);
				p1.setFList(FList);

				mapper.save(item);
				
    	
    	return"addedfullfiller";
    	}
    catch(Exception e)
    {
    	System.err.println(e.getMessage());
    	return "Unable to add";
    }
    	
    
    }
   //deleting a fullfiller 
    public  String deleteF(String s1,String s2,String s3)
    {try 
    	{
   
	    	int a=Integer.parseInt(s1);
	    	ArrayList<Integer> a1=new ArrayList<Integer>();
			WishlistItem item = mapper.load(WishlistItem.class,a);
			ArrayList<Product> PList=new ArrayList<Product>();
			ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
			Product p1=new Product();
			Fullfiller f1=new Fullfiller();
			p1=item.getPrd(Integer.parseInt(s2),item);
			f1=p1.getF(Integer.parseInt(s2),p1);
			FList=p1.getFList();
			FList.remove(f1);
			p1.setFList(FList);
	


	
			mapper.save(item);
	
    	
    	return"fullfiller deleted";
    }
    catch(Exception e)
    {
    	System.err.println(e.getMessage());
    	return "unable to delete";
    }
    	
    
    }
    
    
   //updating product qty 
    
    public  String updatePQty(String s1,String s2,String s3)
    {
    	try 
    	{
    	
	    	int a=Integer.parseInt(s1);
	    	ArrayList<Integer> a1=new ArrayList<Integer>();
			WishlistItem item = mapper.load(WishlistItem.class,a);
			ArrayList<Product> PList=new ArrayList<Product>();
			ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
			Product p1=new Product();
			p1=item.getPrd(Integer.parseInt(s2),item);
			p1.setQty(Integer.parseInt(s3));
	
			mapper.save(item);

    	
			return"qty updated";
    	}
    catch(Exception e)
    {
    	System.err.println(e.getMessage());
    	return "not Updated";
    }
    	
    
    }
    //update fullfiller Qty
    public  String updateFQty(String s1,String s2,String s3,String s4)
    {
    	try {
   
	    	int a=Integer.parseInt(s1);
	    	ArrayList<Integer> a1=new ArrayList<Integer>();
			WishlistItem item = mapper.load(WishlistItem.class,a);
			ArrayList<Product> PList=new ArrayList<Product>();
			ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
			Product p1=new Product();
			p1=item.getPrd(Integer.parseInt(s2),item);
			Fullfiller f1=new Fullfiller();
			f1=p1.getF(Integer.parseInt(s3),p1);
			int i=p1.getFP(Integer.parseInt(s3),p1);
			
			f1.updateQty(i,Integer.parseInt(s4));

	
			mapper.save(item);
			System.out.println(item);
    	
    	return"updatedFullfiller Qty";
    }
    catch(Exception e)
    {
    	System.err.println(e.getMessage());
    	return "not added";
    }
    	
    
  }
    
}
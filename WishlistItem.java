/**
 * @author Ritu Gupta
  Activity has the wishlistItem class
  which describes the information about a Wishlist of a user
  Initially Wishlist can be empty
  It also contains Product,which in turn contains fulllfiller
  *
 */





package com.amazonaws.aws_java_sdk;
import java.util.ArrayList;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
@DynamoDBTable(tableName="Wishlist")
public class WishlistItem
{	
	
		public Integer wishlist_id;
		public String username;
		public String description;
		public ArrayList<Product> productList=new ArrayList<Product>();
       
        

    @DynamoDBHashKey(attributeName="Wishlist_id")  
    public Integer getId() 
    { return wishlist_id; }
    
    public void setId(Integer id) 
    {wishlist_id = id; }
    
   @DynamoDBAttribute(attributeName="Username")  
    public String getUserName() 
     {return username; }
   
    public void setUserName(String username) 
     {this.username = username; }
    
    @DynamoDBAttribute(attributeName="Description")  
    public String getDescription() 
     { return description; }
    
    public void setDescription(String description) 
     { this.description =description; }
     
     @DynamoDBAttribute(attributeName="List")
     @DynamoDBTypeConverted(converter = ProductTypeConverter.class)
    public ArrayList<Product> getList() 
     { return productList; }
     
    public void setList(ArrayList<Product> productList ) 
     { this.productList = productList; }
     
     
    public Product getPrd(int id,WishlistItem w1)
     {
    	 ArrayList<Product> pl=new ArrayList<Product>();
    	 pl=w1.getList();
    	 Product p1=new Product();
    	 for(int i=0;i<pl.size();i++)
    	 {
    		p1=pl.get(i);
    		if(p1.getPd()==id)
    		{
    			break;
    		}
    			
    	 }
    	 
    	 
    	 return p1;
     }
    public void delProduct(int q)
     {
    	 productList.remove(q);
     }
     
    public String toString() 
    {
    	
        return wishlist_id+" "+username+" "+description+" "+productList;
    }

}
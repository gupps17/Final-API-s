/**
 * @author Ritu Gupta
  Activity has the Fullfiller class
  which describes the information about fullfiler
  *
 */






package com.amazonaws.aws_java_sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

class Fullfiller 

    {   public ArrayList<Integer> m=new ArrayList<Integer>();
    	
    public void setQty(Integer Qty)
    	{
    		m.add(Qty);
            
    	}
    public int getQty()
	{
		return m.get(0);
        
	}
    
    public void updateQty(int i,int q)
    {
    	m.set(i,q);
    }
    public void updateid(int i,int id)
    {
    	m.set(i,id);
    }
    	
    
    	
    	public void setid(Integer id)
    	{
    		m.add(id);
            
    	}
    	 public int getid()
    	{
    			return m.get(1);
    	        
    	}
    	    	
    	
    	
    	public String toString()
    	{
    	    return m+"";
    	}
    
    }
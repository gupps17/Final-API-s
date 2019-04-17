package com.amazonaws.aws_java_sdk;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;


 public class Product
{
	public String Added_Date;
    public int Product_id;
    public int Quantity;
    ArrayList<Fullfiller> f=new ArrayList<Fullfiller>();
    

     public String getdate() 
     {
         return Added_Date;
     }

     public void setDate(String d) 
     {
         Added_Date = d;
     }

     public int getPd() 
     {
         return Product_id;
     }

     public void setPd(int pid)
     {
         Product_id = pid;
     }

     public int getQty() 
     {
         return Quantity;
     }

     public void setQty(int q) 
     {
         Quantity = q;
     }
     public void updateQty(int q)
     
     {
    	Quantity=q; 
     }
    
     public ArrayList<Fullfiller> getFList() 
     { return f; }
     
     public void setFList(ArrayList<Fullfiller> fullList ) 
     { this.f = fullList; }
     
     public Fullfiller getF(int id,Product w1)
     {
    	 int j=0;
    	 ArrayList<Fullfiller> pl=new ArrayList<Fullfiller>();
    	 pl=w1.getFList();
    	 Fullfiller f1=new Fullfiller();
    	 
    	 for(int i=0;i<pl.size();i++)
    	 {
    		f1=pl.get(i);
    		if(f1.getid()==id)
    		{	 
    			break;
    	     }
    			
         }
    	 return f1;
    }
     public int getFP(int id,Product w1)
     {
    	 int j=0;
    	 ArrayList<Fullfiller> pl=new ArrayList<Fullfiller>();
    	 pl=w1.getFList();
    	 Fullfiller f1=new Fullfiller();
    	 
    	 for(int i=0;i<pl.size();i++)
    	 {
    		f1=pl.get(i);
    		if(f1.getid()==id)
    		{	 j=i;
    			break;
    	     }
    			
         }
    	 return j;
    }
public String toString() 
{
    return Added_Date+" "+Product_id+" "+Quantity+" "+f;
}


}





 
   
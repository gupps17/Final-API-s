/**
 * @author Ritu Gupta
  Activity has the  Mapper class
 Allowing you to map your client-side classes to DynamoDB tables. 
 In DynamoDBMapper, you define the relationship between items 
 in a DynamoDB table and their corresponding object instances in the code.
  *
 */








package com.amazonaws.aws_java_sdk;
import java.util.ArrayList;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class ProductTypeConverter implements DynamoDBTypeConverter<ArrayList<String>, ArrayList<Product> >{

    @Override
    public ArrayList<String> convert(ArrayList<Product> object)
    {
    
        Product p1 ;
        Fullfiller m1;
        ArrayList<Fullfiller> f1;
        String product = null;
        int n=object.size();
        ArrayList <String> l1=new ArrayList<String>();
        try {
				int i;
				for(i=0;i<n;i++)
				{
				    p1=object.get(i);
				    f1=p1.getFList();
				    int t=f1.size();
				    System.out.println(t);
				   
				        product = String.format("%s  %s  %s  %s", p1.getdate(), p1.getPd(),
				            p1.getQty(),p1.getFList());
				   
				        
				        l1.add(product);
				        
				  }
			        
			 }
        catch (Exception e) 
        {
        	System.err.println(e.getMessage());
        	throw e;
        }
        return l1;
    }
   
	@Override
	public ArrayList<Product> unconvert(ArrayList<String> object) {
		// TODO Auto-generated method stub
		try{
			int n1=object.size();
			int i,j;
			
			WishlistItem w=new WishlistItem();
			
			ArrayList<Product> PList=new ArrayList<Product>();
			
			for(i=0;i<n1;i++)
				
			{ 
				ArrayList<Fullfiller> FList=new ArrayList<Fullfiller>();
				Product p1=new Product();
				String s=object.get(i);
			String []s1=s.split("  ");		
			p1.setDate(s1[0]);
			p1.setPd(Integer.parseInt(s1[1]));
			p1.setQty(Integer.parseInt(s1[2]));
			//System.out.println("_"+s1);
			String []s2=s1[3].split("]");

		//System.out.println(s2[0]);
			for(j=0;j<s2.length;j++)
			{	Fullfiller  f1=new Fullfiller();
					
				String s3=s2[j];
				
				int t=s3.length();
				System.out.println(t+"fuck");
				System.out.println("_"+s3);
				int a=Character.getNumericValue(s3.charAt(t-1));
				int b=Character.getNumericValue(s3.charAt(t-4));
				System.out.println(a+"G");
				System.out.println(b+"G");
				
				f1.setid(b);
				f1.setQty(a);
				//System.out.println(f1+"flist");
				FList.add(f1);
				System.out.println(FList+"flist");
				
				
			}
			p1.setFList(FList);		
			PList.add(p1);
			p1=null;
			
			//System.out.println(s2[1]);//System.out.println(s2[2]);
			System.out.println("+"+s2.length);
			
			
			
		}
		
			
		w.setList(PList);
		
        return PList;
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			throw e;
		}
		
        
			
		}
	}




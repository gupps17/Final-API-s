package com.amazonaws.samples;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

public class QueryUser {
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	static String tableName = "user";
	Table table = dynamoDB.getTable(tableName);

	public static boolean queryItem(String s1, String s2) {
		try {
			System.out.println(s1);
			System.out.println(s2);
			Table table = dynamoDB.getTable(tableName);
			GetItemSpec spec = new GetItemSpec().withPrimaryKey("UserName", s1);
			System.out.println("Attempting to read the item...");
			Item outcome = table.getItem(spec);
			if (outcome != null) {
				String s3 = outcome.get("Password").toString();

				if (s2.contentEquals(s3)) 
				{
					return true;
				} 
				else 
				{ 
					throw new Exception("Incorrect username or password");
				}
			  }
			else
				throw new Exception("Invalid");
		}
			
			catch (Exception e) 
			{
			System.err.println("Unable to read item :");
			System.err.println(e.getMessage());
			return false;
		    }
        }
		}		

	
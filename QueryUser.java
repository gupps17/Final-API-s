/**** Ritu Gupta
 To get the user details of the wishlits based on user
 */


package com.amazonaws.aws_java_sdk;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpHandler;

public class QueryUser {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	
    static DynamoDBMapper mapper = new DynamoDBMapper(client);


	static String tableName = "Wishlist";
	Table table = dynamoDB.getTable(tableName);
	
		

	public  JSONArray queryUser(String id) throws JSONException
	{QueryWishlist w=new QueryWishlist();
	JSONObject k1 = new JSONObject();
	JSONArray k = new JSONArray();
		Map<String, AttributeValue> expressionAttributeValues = 
			    new HashMap<String,AttributeValue>();
			expressionAttributeValues.put(":val",new AttributeValue().withS(id)); 
			        
			ScanRequest scanRequest = new ScanRequest()
			    .withTableName("Wishlist")
			    .withFilterExpression("Username = :val")
			    .withProjectionExpression("Wishlist_id")
			    .withExpressionAttributeValues(expressionAttributeValues);
			
				int i=0;
			ScanResult result = client.scan(scanRequest);
			for (Map<String, AttributeValue> item : result.getItems()) {
			    String s=item.values().toString();
			    String r=s.substring(5,s.length()-3);
			    System.out.println(r);
			    k1=w.queryS(r);
				 System.out.println(k1);
			
					k.put(i,k1);
					i++;
					
			}
			return k;

	}


}


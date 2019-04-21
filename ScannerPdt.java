/*
  @author Amrutha Krishnamoorthy
  API to scan through the database and fetch the URLs of the images from s3 bucket
  */


package com.amazonaws.samples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.amazonaws.services.dynamodbv2.document.Item;

public class ScannerPdt implements HttpHandler {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	public static void main(String[] args) throws IOException {
		scanner();
	}

	private static String scanner() {
		Table table = dynamoDB.getTable("Product");
		ScanSpec scanSpec = new ScanSpec()
				.withProjectionExpression("Product_id,Brand,Description,Image,Price,Product_name");
		String temp = "";
		int i = 0;
		try {
			
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);
			System.out.println(items.toString());
			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				temp = temp + item.get("Image").toString() + "\n";
				i++;
				System.out.println(item.get("Image").toString());

			}
			return temp;
		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
			return temp;
		}
	}

	@Override
	public void handle(HttpExchange t) throws IOException {
		byte[] response = " ".getBytes();

		try {

			String response1;
			response1 = scanner();

			System.out.println(response1);
			response = response1.getBytes();
			t.sendResponseHeaders(200, response.length);

		} catch (Exception e) {
			System.out.println("Oops something went wrong");
			response = ("Oops something went wrong + " + e.getMessage().toString()).getBytes();
			t.sendResponseHeaders(400, response.length);
			OutputStream os = t.getResponseBody();
			os.write(response);
			os.close();

		}
		OutputStream os = t.getResponseBody();
		os.write(response);
		os.close();

	}

}

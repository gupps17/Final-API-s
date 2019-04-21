/* @author Amrutha Krishnamoorthy

  methods with details
  to fetch domain level objects
*/


package com.amazonaws.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import com.amazonaws.services.dynamodbv2.document.Item;

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
public class Server {

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		
		server.createContext("/login", new Post());
		server.createContext("/wishlist", new Post1());
		server.createContext("/product", new Post2());
		server.createContext("/show", new ScannerPdt());
		server.setExecutor(null); // creates a default executor
		server.start();
		System.out.println("Started server");
	}


	public static class Post implements HttpHandler {

		public void handle(HttpExchange he) throws IOException {
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			System.out.println(query);
			parseQuery(query, parameters);
			System.out.println("Enter");
			String s1 = parameters.get("UserName").toString();
			String s2 = parameters.get("Password").toString();
			System.out.println(s1);
			System.out.println(s2);
			QueryUser temp=new QueryUser();
			String response = "";
			boolean r = temp.queryItem(s1, s2);
			if (r) 
			{
				response = "Successful login!";
			}

			else {
				response = "Invalid password or username" + "\n";
			}
			System.out.println(r);
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	
	public static class Post1 implements HttpHandler {

		public void handle(HttpExchange he) throws IOException {
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			System.out.println(query);
			parseQuery(query, parameters);
			String s1 = parameters.get("Wishlist_id").toString();

			QueryWishlist temp=new QueryWishlist();
			String response = "";
			Item item = temp.queryId(s1);
			if (item==null) 
			{
		      	 response="Empty Wishlist!";
			}

			else {
				response = item.toString();
			}
			System.out.println(item);
			he.sendResponseHeaders(200,response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}

	public static class Post2 implements HttpHandler {

		public void handle(HttpExchange he) throws IOException {
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			System.out.println(query);
			parseQuery(query, parameters);
			String s1 = parameters.get("Product_id").toString();
			System.out.println(s1);

			QueryProduct temp=new QueryProduct();
			String response = "";
			Item item = temp.queryPdt(s1);
			if (item==null) 
			{
				response = "No such product exists!";
			}

			else {
				response = "Found the product" + item.toString();
			}
			System.out.println(item);
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}

    public static void parseQuery(String query, Map<String, 
			Object> parameters) throws UnsupportedEncodingException {

		         if (query != null) {
		                 String pairs[] = query.split("[&]");
		                 for (String pair : pairs) {
		                          String param[] = pair.split("[=]");
		                          String key = null;
		                          String value = null;
		                          if (param.length > 0) {
		                          key = URLDecoder.decode(param[0], 
		                          	System.getProperty("file.encoding"));
		                          }

		                          if (param.length > 1) {
		                                   value = URLDecoder.decode(param[1], 
		                                   System.getProperty("file.encoding"));
		                          }

		                          if (parameters.containsKey(key)) {
		                                   Object obj = parameters.get(key);
		                                   if (obj instanceof List<?>) {
		                                            List<String> values = (List<String>) obj;
		                                            values.add(value);

		                                   } else if (obj instanceof String) {
		                                            List<String> values = new ArrayList<String>();
		                                            values.add((String) obj);
		                                            values.add(value);
		                                            parameters.put(key, values);
		                                   }
		                          } else {
		                                   parameters.put(key, value);
		                          }
		                 }
		         }
    }
   
    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
          byte [] response = "Welcome Real's HowTo test page".getBytes();
          Item please=new Item()
        		   .withPrimaryKey("Id", 101) 
        		   .withString("Nomenclature", "PolyBlaster 101") 
        		   .withString("Description", "101 description") 
        		   .withString("Category", "Hybrid Power Polymer Cutter")  
        		   .withString("Make", "Brand – XYZ") 
        		   .withNumber("Price", 50000) 
        		   .withString("ProductCategory", "Laser Cutter") 
        		   .withBoolean("Availability", true);
          t.sendResponseHeaders(200,response.length);
          OutputStream os = t.getResponseBody();
          os.write(response);
          os.close();
        }
      }
    }

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
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		
		server.createContext("/login", new Post());
		server.createContext("/wishlist", new Post1());
		server.createContext("/product", new Post2());
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
			System.out.println("Reached");
			QueryUser temp=new QueryUser();
			String response = "";
			boolean r = temp.queryItem(s1, s2);
			if (r) 
			{
				response = "Successful login!";
			}

			else {
				response = "Invalid passwsord or username" + "\n";
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
}

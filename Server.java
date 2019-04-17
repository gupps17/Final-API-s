package com.amazonaws.aws_java_sdk;

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

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/*
 * a simple static http server
*/

public class Server 
{

	public static void main(String[] args) throws Exception 
	{
		HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
		server.createContext("/create", new Create());
		server.createContext("/post", new Post());
		server.createContext("/deleteWishlist", new DeleteW());
		server.createContext("/addProduct",new AddPrd());
		server.createContext("/addFullfiller",new AddF());
		server.createContext("/deletePrd",new DeletePrd());
		server.createContext("/updatePQty",new UpdatePQty());
		server.createContext("/updateFQty",new UpdateFQty());
		server.createContext("/deleteF",new DeleteF());
		server.setExecutor(null); // creates a default executor
		server.start();
	}

//Login module

	public static class Post implements HttpHandler 
	{

		public void handle(HttpExchange he) throws IOException
		{
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			System.out.println(query);
			parseQuery(query, parameters);
			String s1 = parameters.get("UserName").toString();
			String s2 = parameters.get("Password").toString();
			System.out.println(s1);
			System.out.println(s2);

			Login l1 = new Login();
			String response = "";
			boolean r = l1.query(s1, s2);
			// send response
			if (r)
			{
				response = "Successful login!";
			}

			else 
			{
				response = "Invalid passwsord or username" + "\n";
			}
			System.out.println(r);
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	
//delete a wishlist
	public static class DeleteW implements HttpHandler 
	{

		public void handle(HttpExchange he) throws IOException 
		{
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
	
			parseQuery(query, parameters);
			String s1 = parameters.get("WId").toString();
			Sample Item=new Sample();
			String response = "";
			 response = Item.deleteWishlist(s1);
			
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	//delete a Product
	public static class DeletePrd implements HttpHandler 
	{

		
		@SuppressWarnings("restriction")
		public void handle(HttpExchange he) throws IOException {
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			parseQuery(query, parameters);
			String s1 = parameters.get("WId").toString();
			String s2= parameters.get("PId").toString();
			String response="";
			Update2 a1=new Update2();
			response=a1.deleteP(s1, s2);
			// send response
		
	

			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	
	
	
	
	
	//creating wishlist
	
	public static class Create implements HttpHandler 
	{

		public void handle(HttpExchange he) throws IOException 
		{
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			parseQuery(query, parameters);
			String s1 = parameters.get("UserName").toString();
			String s2 = parameters.get("Description").toString();
			String s3 = parameters.get("WId").toString();
			
			
		
			Sample WishList = new Sample();
			String response = "";
		 response=WishList.createItems(s1,s2,s3);
			// send response
			
			
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	//adding product
	public static class AddPrd implements HttpHandler {

		public void handle(HttpExchange he) throws IOException {
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
	
			parseQuery(query, parameters);
			String s1 = parameters.get("Prdid").toString();
			String s2 = parameters.get("PrdQty").toString();
			String s3 = parameters.get("Date").toString();
			String s4=parameters.get("WId").toString();
			String s5=parameters.get("FId").toString();
			String s6=parameters.get("FQty").toString();
			
			Update2 a1 = new Update2();
			String response = "";
		 response=a1.addPrd(s1,s2,s3,s4,s5,s6);
			// send response
			
			
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	//adding fullfiller
	public static class AddF implements HttpHandler 
	{

		public void handle(HttpExchange he) throws IOException {
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
	
			parseQuery(query, parameters);

			String s2 = parameters.get("Prdid").toString();
			String s1=parameters.get("WId").toString();
			String s3=parameters.get("FId").toString();
			String s4=parameters.get("FQty").toString();
			
			Update2 a1 = new Update2();
			String response = "";
		 response=a1.addF(s1,s2,s3,s4);
			// send response
			
			
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	//delete fullfiller
	public static class DeleteF implements HttpHandler 
	{

		public void handle(HttpExchange he) throws IOException
		{
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			parseQuery(query, parameters);

			String s2 = parameters.get("Prdid").toString();
			String s1=parameters.get("WId").toString();
			String s3=parameters.get("FId").toString();
		
			
		
			Update2 a1 = new Update2();
			String response = "";
		 response=a1.deleteF(s1,s2,s3);
			// send response
			
			
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	//update fullfiller qty
	public static class UpdateFQty implements HttpHandler 
	{

		public void handle(HttpExchange he) throws IOException
		{
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();

			parseQuery(query, parameters);
			String s1 = parameters.get("WId").toString();
			String s2 = parameters.get("PId").toString();
			String s3 = parameters.get("FId").toString();
			String s4= parameters.get("Qty").toString();
			
			
			Update2 WishList = new Update2();
			String response = "";
		 response=WishList.updateFQty(s1,s2,s3,s4);
			// send response
			
			
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	//update Product qty
	public static class UpdatePQty implements HttpHandler 
	{

		public void handle(HttpExchange he) throws IOException 
		{
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			parseQuery(query, parameters);
			String s1 = parameters.get("WId").toString();
			String s2 = parameters.get("PId").toString();
			String s3 = parameters.get("Qty").toString();		
			
			
	
			Update2 WishList = new Update2();
			String response = "";
		 response=WishList.updatePQty(s1,s2,s3);
			// send response
			
			
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}
	
	
//Parse Post request
	public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException 
	{

		if (query != null) 
		{
			String pairs[] = query.split("[&]");
			for (String pair : pairs) 
			{
				String param[] = pair.split("[=]");
				String key = null;
				String value = null;
				if (param.length > 0) 
				{
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) 
				{
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key))
				{
					Object obj = parameters.get(key);
					if (obj instanceof List<?>)
					{
						List<String> values = (List<String>) obj;
						values.add(value);

					} 
					else if (obj instanceof String) 
					{
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} 
				else
				{
					parameters.put(key, value);
				 }
			}
		}
	}
//handle get request
	static class Get implements HttpHandler
	{
		public void handle(HttpExchange httpExchange) throws IOException 
		{
			StringBuilder response = new StringBuilder();
			Map<String, String> parms = Server.queryToMap(httpExchange.getRequestURI().getQuery());
			response.append("<html><body>");
			response.append("ProductQty : " + parms.get("Qty1") + "<br/>");
			response.append("ProductDate : " + parms.get("Date") + "<br/>");
			response.append("</body></html>");
			Server.writeResponse(httpExchange, response.toString());
			
	
			Login l2 = new Login();
			
		}
	}
//Parsing the get call
	public static Map<String, String> queryToMap(String query) 
	{
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) 
		{
			String pair[] = param.split("=");
			if (pair.length > 1) 
			{
				result.put(pair[0], pair[1]);
			} else 
			{
				result.put(pair[0], "");
			}
		}
		return result;
	}

	public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
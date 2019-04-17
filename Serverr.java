package com.amazonaws.doc;

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
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Serverr
{
    public static void main(String[] args) throws Exception
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8002),0);
        server.createContext("/Create",new Create());
        server.createContext("/QueryMessage",new QueryMessage());
        server.createContext("/Scannerr",new Scannerr());
        server.setExecutor(null);
        server.start();
    }

    public static class Create implements HttpHandler
    {

        public void handle(HttpExchange he) throws IOException
        {
            // parsing request
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            System.out.println(query);
            parseQuery(query,parameters);
            String s1 = parameters.get("message_id").toString();
            String s2 = parameters.get("msg").toString();
            String s3 = parameters.get("s_id").toString();
            String s4 = parameters.get("r_id").toString();
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);
            System.out.println(s4);
            CreateMsg m1 = new CreateMsg();
            String response = "hello";
            boolean r = m1.putItem(s1,s2,s3,s4);
            // sending response
            if (r)
            {
                response = "Successfully created";
            }
            else
                {
                response = "message is not created" + "\n";
                }
            System.out.println(response);
            he.sendResponseHeaders(200,response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    public static class QueryMessage implements HttpHandler
    {

        public void handle(HttpExchange he) throws IOException
        {
            // parsing request
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            System.out.println(query);
            parseQuery(query,parameters);
            String s1 = parameters.get("Message_id").toString();
            System.out.println(s1);

            QueryMsg q1 = new QueryMsg();
            String response = "hello";
            boolean r = q1.queryItem(s1);
            // sending response
            if (r) {
                response = "Successful query!";
            } else {
                response = "query not successfull" + "\n";
            }
            System.out.println(r);
            he.sendResponseHeaders(200,response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }


    public static class Scannerr implements HttpHandler
    {

        public void handle(HttpExchange he) throws IOException
        {
            // parsing request
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            System.out.println(query);
            parseQuery(query,parameters);
            Scann s1 = new Scann();
            String response = "hello";
            boolean r = s1.scanner();
            // sending response
            if (r)
            {
                response = "Successful scan!";
            }
            else
                {
                response = "scan not successful" + "\n";
                }
            System.out.println(r);
            he.sendResponseHeaders(200,response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    public static void parseQuery(String query,Map<String,
            Object> parameters) throws UnsupportedEncodingException
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
                    key = URLDecoder.decode(param[0],
                                            System.getProperty("file.encoding"));
                }

                if (param.length > 1)
                {
                    value = URLDecoder.decode(param[1],
                                              System.getProperty("file.encoding"));
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
                        parameters.put(key,values);
                    }
                }
                else
                    {
                    parameters.put(key,value);
                    }
            }
        }
    }

}
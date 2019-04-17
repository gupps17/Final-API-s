package com.amazonaws.doc;

import java.io.IOException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

public class QueryMsg
{
    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .build();

    static DynamoDB dynamoDB = new DynamoDB(client);

    static String tableName = "Message";

    public static void main(String[] args) throws IOException
    {

    }


    public static boolean queryItem(String message_id)
    {
        boolean t;
        int x=Integer.parseInt(message_id);
        Table table = dynamoDB.getTable(tableName);

        // Using 'GetItemSpec' method and 'getItem' method the Message is queried

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("Message_id",x);

        try
        {
            System.out.println("Attempting to read the item...");
            Item outcome = table.getItem(spec);
            System.out.println(outcome.toJSONPretty());     //Displaying the output in a JSON format
             t=true;
        }
        catch (Exception e)
        {
            System.err.println("Unable to read item :");
            System.err.println(e.getMessage());
            t=false;
        }

       return (t);
    }
}
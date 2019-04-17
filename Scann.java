package com.amazonaws.doc;

import java.util.Iterator;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import static com.amazonaws.doc.updateitem.dynamoDB;


public class Scann
{

    public static void main(String[] args) throws Exception
    {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()

                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

    }
    public static boolean scanner()
    {   boolean y;
        Table table = dynamoDB.getTable("Product");
        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("Product_id,Brand,Description,Image,Price,Product_name");
             // Scans the products table using ScanSpec method

        try
        {

            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            //Iterating through all the products an printing them

            while (iter.hasNext())
            {
                Item item = iter.next();
                System.out.println(item.toJSONPretty());
            }
          y=true;
        }
        catch (Exception e)
        {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
            y=false;
        }
      return (y);
    }

}
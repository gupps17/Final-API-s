package com.amazonaws.doc;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;


public class CreateMsg
{

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);

    public static String tableName = "Message";

    public static void main(String[] args) throws Exception
    {

    }


    public static boolean putItem( String message_id, String msg, String s_id, String r_id )
    {
        int a=Integer.parseInt(message_id);
        int b=Integer.parseInt(s_id);
        int c=Integer.parseInt(r_id);

        Table table = dynamoDB.getTable(tableName);

        //creating a new item with the given credentials

        Item item = new Item().withPrimaryKey("Message_id",a).withString("message",msg)
                              .withNumber("Sender_id",b).withNumber("Reciever_id",c);

        table.putItem(item);
        return (true);
    }
}



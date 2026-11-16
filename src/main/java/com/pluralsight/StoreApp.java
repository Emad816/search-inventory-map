package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class StoreApp {

    public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();

    public static void main(String[] args) {

        // this method loads product objects into inventory
        loadInventory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int id = scanner.nextInt();
        Product matchedProduct = inventory.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
            return;
        }
        System.out.printf("We carry %s and the price is $%.2f",
                matchedProduct.getName(), matchedProduct.getPrice());
    }

    public static void loadInventory() {

        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                //Split the line using (|)
                String[] values = line.split("\\|");

                //Assigning line parts to variables
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                double price = Double.parseDouble(values[2]);
                inventory.put(id, new Product(id, name, price));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
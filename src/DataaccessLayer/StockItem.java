package DataaccessLayer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StockItem {
    private String id;
    private String category;
    private String brand;
    private int quantity;

    public StockItem(String id, String category, String brand, int quantity) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to read equipment data from a file
    public static List<StockItem> readCategoryFromFile() {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/Stock.TXT";
        List<StockItem> stockItemList = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Assuming comma-separated values
                String id = parts[0].trim();
                String category = parts[1].trim();
                String brand = parts[2].trim();
                int quantity = Integer.parseInt(parts[3].trim());
                StockItem equipment = new StockItem(id, category, brand, quantity);
                stockItemList.add(equipment);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
        return stockItemList;
    }


    public static void incrementQuantityInCategory(String categoryId, int incrementBy) {
        List<StockItem> stockItemList = readCategoryFromFile();
        for (StockItem stockItem : stockItemList) {
            if (stockItem.getId().equals(categoryId)) {
                int updatedQuantity = stockItem.getQuantity() + incrementBy;
                stockItem.setQuantity(updatedQuantity);
                break;
            }
        }
        // Write the updated category list back to file
        writeCategoryListToFile(stockItemList);
    }

    private static void writeCategoryListToFile(List<StockItem> stockItemList) {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/Stock.TXT";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (StockItem stockItem : stockItemList) {
                String categoryData = String.format("%s,%s,%s,%d",
                        stockItem.getId(), stockItem.getCategory(), stockItem.getBrand(), stockItem.getQuantity());
                printWriter.println(categoryData);
            }
            printWriter.close();
            System.out.println("Quantity incremented successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printCategoryTable(List<StockItem> stockItemList) {
        System.out.println("+----------+----------------------+----------------------+----------+");
        System.out.println("|  IDcat   |      Designation     |         Brand        | Quantity |");
        System.out.println("+----------+----------------------+----------------------+----------+");
        for (StockItem stockItem : stockItemList) {
            System.out.printf("| %-8s | %-20s | %-20s | %-8d |\n",
                    stockItem.getId(), stockItem.getCategory(), stockItem.getBrand(), stockItem.getQuantity());
        }
        System.out.println("+----------+----------------------+----------------------+----------+");
    }



}

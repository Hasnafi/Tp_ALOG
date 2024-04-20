package DataaccessLayer;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RentedItem {
    private String codequip;
    private String idloc;
    private Date datesortie;
    private Date dateremise;
    private double montant;

    public RentedItem(String codequip, String idloc, Date datesortie, Date dateremise, double montant) {
        this.codequip = codequip;
        this.idloc = idloc;
        this.datesortie = datesortie;
        this.dateremise = dateremise;
        this.montant = montant;
    }

    // Getters and setters
    public String getCodequip() {
        return codequip;
    }

    public void setCodequip(String codequip) {
        this.codequip = codequip;
    }

    public String getIdloc() {
        return idloc;
    }

    public void setIdloc(String idloc) {
        this.idloc = idloc;
    }

    public Date getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(Date datesortie) {
        this.datesortie = datesortie;
    }

    public Date getDateremise() {
        return dateremise;
    }

    public void setDateremise(Date dateremise) {
        this.dateremise = dateremise;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    // Method to read rental records data from a file
    public static List<RentedItem> readRentalRecordFromFile() {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/RentadItems.TXT";
        List<RentedItem> rentedItemList = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Assuming comma-separated values
                String codequip = parts[0].trim();
                String idloc = parts[1].trim();
                Date datesortie = dateFormat.parse(parts[2].trim());
                Date dateremise = dateFormat.parse(parts[3].trim());
                double montant = Double.parseDouble(parts[4].trim());
                RentedItem rentedItem = new RentedItem(codequip, idloc, datesortie, dateremise, montant);
                rentedItemList.add(rentedItem);
            }
            scanner.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("File not found or parsing error: " + filePath);
            e.printStackTrace();
        }
        return rentedItemList;
    }

    public static void addRentalRecordToFile(RentedItem rentedItem) {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/RentadItems.TXT";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            // Format rental record data as a comma-separated string
            String rentalRecordData = String.format("%s,%s,%s,%s,%.2f",
                    rentedItem.getCodequip(), rentedItem.getIdloc(),
                    dateFormat.format(rentedItem.getDatesortie()), dateFormat.format(rentedItem.getDateremise()),
                    rentedItem.getMontant());
            // Write the rental record data to the file
            printWriter.println(rentalRecordData);
            printWriter.close();
            System.out.println("Rental record added successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while adding rental record to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to print rental records table
    public static void printRentalRecordTable(List<RentedItem> rentedItemList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("+----------+----------+------------+------------+----------+");
        System.out.println("| Codequip |   IDLoc  | DateSortie | DateRemise | Montant  |");
        System.out.println("+----------+----------+------------+------------+----------+");
        for (RentedItem rentedItem : rentedItemList) {
            System.out.printf("| %-8s | %-8s | %-10s | %-10s | %-8.2f |\n",
                    rentedItem.getCodequip(), rentedItem.getIdloc(),
                    dateFormat.format(rentedItem.getDatesortie()), dateFormat.format(rentedItem.getDateremise()),
                    rentedItem.getMontant());
        }
        System.out.println("+----------+----------+------------+------------+----------+");
    }



}

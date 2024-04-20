package DataaccessLayer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Equipment {
    private String codequip;
    private String idcat;
    private String etat;

    public Equipment(String codequip, String idcat, String etat) {
        this.codequip = codequip;
        this.idcat = idcat;
        this.etat = etat;
    }

    // Getters and setters
    public String getCodequip() {
        return codequip;
    }

    public void setCodequip(String codequip) {
        this.codequip = codequip;
    }

    public String getIdcat() {
        return idcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    // Method to read equipment tracking data from a file
    public static List<Equipment> readEquipmentFromFile() {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/equipments.TXT";
        List<Equipment> equipmentList = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Assuming comma-separated values
                String idcat = parts[0].trim();
                String etat = parts[1].trim();
                String codequip = parts[2].trim();
                Equipment equipment = new Equipment(idcat, etat, codequip);
                equipmentList.add(equipment);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
        return equipmentList;
    }

    public static void addEquipmentToFile(Equipment equipment) {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/equipments.TXT";
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            // Format equipment data as a comma-separated string
            String equipmentData = String.format("%s,%s,%s",
                    equipment.getCodequip(),equipment.getIdcat(), equipment.getEtat());
            // Write the equipment data to the file
            printWriter.println(equipmentData);
            printWriter.close();
            System.out.println("Equipment added successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while adding equipment to file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void writeEquipmentListToFile(List<Equipment> equipmentList) {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/equipments.TXT";
        try {
            FileWriter fileWriter = new FileWriter(filePath, false); // Truncate the file before writing
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Equipment equipment : equipmentList) {
                String equipmentData = String.format("%s,%s,%s",
                        equipment.getIdcat(), equipment.getEtat(), equipment.getCodequip());
                printWriter.println(equipmentData);
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void printEquipmentTable(List<Equipment> equipmentList) {
        System.out.println("+----------+----------------------+--------------------------+");
        System.out.println("|  IDcat   |       State          |      Code equipement     |");
        System.out.println("+----------+----------------------+--------------------------+");
        for (Equipment equipment : equipmentList) {
            System.out.printf("| %-8s | %-20s | %-20s |\n",
                    equipment.getIdcat(), equipment.getEtat(), equipment.getCodequip() );
        }
        System.out.println("+----------+----------------------+--------------------------+");
    }

}


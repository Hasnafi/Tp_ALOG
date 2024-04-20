package BusinessLayer;

import DataaccessLayer.Client;
import DataaccessLayer.Equipment;
import DataaccessLayer.RentedItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryProcessor {

    // Method to retrieve all equipment
    public static List<Equipment> getAllEquipment() {
        // Retrieve equipment data from file
        List<Equipment> equipmentList = Equipment.readEquipmentFromFile();
        // Return the list of equipment
        return equipmentList;
    }

    // Method to retrieve available equipment
    public static List<Equipment> getDispoEquipment() {
        // Retrieve equipment data from file
        List<Equipment> equipmentList = Equipment.readEquipmentFromFile();
        // List to store available equipment
        List<Equipment> availableEquipment = new ArrayList<>();
        // Iterate through equipment list
        for (Equipment equipment : equipmentList) {
            // Check if equipment is available
            if ("disponible".equals(equipment.getEtat())) {
                // Add available equipment to the list
                availableEquipment.add(equipment);
            }
        }
        // Print table of available equipment
        return availableEquipment;
    }

    // Method to retrieve equipment rented by a specific client
    public static List<RentedItem> getClientEquipment(String id) {
        // Retrieve rental records data from file
        List<RentedItem> rentedItems = RentedItem.readRentalRecordFromFile();
        // List to store rental records of the specified client
        List<RentedItem> newList = new ArrayList<>();
        // Iterate through rental records
        for (RentedItem record : rentedItems) {
            // Check if the record belongs to the specified client
            if (id.equals(record.getIdloc())) {
                // Add the record to the list
                newList.add(record);
            }
        }
        // Print table of rental records for the specified client
        return newList;
    }

    // Method to retrieve the balance of a specific client
    public static double getSoldClient(String id) {
        // Retrieve client data from file
        List<Client> clientsList = Client.readClientFromFile();
        // Iterate through clients
        for (Client client : clientsList) {
            // Check if the client ID matches the specified ID
            if (id.equals(client.getId())) {
                // Return the balance of the client
                return client.getSolde();
            }
        }
        // Return -1 if client not found
        return -1;
    }

    // Method to retrieve overdue equipment
    public static List<RentedItem> getOverdueEquipment(String id) {
        // Retrieve rental records data from file
        List<RentedItem> rentedItems = RentedItem.readRentalRecordFromFile();
        // List to store overdue rental records
        List<RentedItem> overdueRecords = new ArrayList<>();
        // Get current date
        Date today = new Date();
        List<Client> clientsList = Client.readClientFromFile();

        // Iterate through rental records
        for (RentedItem record : rentedItems) {
            if (id.equals(record.getIdloc())) {
                // Check if the return date has passed
                if (today.after(record.getDateremise())) {
                    // Add overdue record to the list
                    overdueRecords.add(record);
                }
            }
        }

        // Print table of overdue rental records
        return overdueRecords;
    }
}

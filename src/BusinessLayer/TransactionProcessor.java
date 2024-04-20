package BusinessLayer;

import DataaccessLayer.Client;
import DataaccessLayer.Equipment;
import DataaccessLayer.RentedItem;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static DataaccessLayer.Client.removeClientFromFile;
import static DataaccessLayer.StockItem.incrementQuantityInCategory;
import static DataaccessLayer.Client.writeClientToFile;
import static DataaccessLayer.Equipment.addEquipmentToFile;
import static DataaccessLayer.Equipment.writeEquipmentListToFile;

public class TransactionProcessor {

    // Method to check if equipment is available
    public boolean checkEquipementDispo(String idEquipement) {
        List<Equipment> equipmentList = Equipment.readEquipmentFromFile();

        // Iterate through equipment list
        for (Equipment equipment : equipmentList) {
            // Check if equipment is available
            if ("disponible".equals(equipment.getEtat())) {
                return true;
            }
        }
        return false;
    }

    // Method to add client to file
    public static void addClientToFile(Client client) {
        writeClientToFile(client);
    }

    // Method to add equipment to file
    public static void addEquipementSTofile(Equipment equipment) {
        String idCategorie = equipment.getIdcat();
        incrementQuantityInCategory(idCategorie, 1);
        addEquipmentToFile(equipment);
    }

    // Method to rent equipment to client
    public static void RentEquipmentToClient(RentedItem newRental){
        List<Equipment> equipmentList = Equipment.readEquipmentFromFile();
        // Iterate through equipment list
        for (Equipment equipment : equipmentList) {
            // Check if equipment matches the rented item
            if (newRental.getCodequip().equals(equipment.getCodequip())) {
                // Check if equipment is available
                if ("disponible".equals(equipment.getEtat())) {
                    // Change equipment state to 'sorti' (taken)
                    equipment.setEtat("sorti");
                    // Write updated equipment list to file
                    writeEquipmentListToFile(equipmentList);
                    // Add rental record to file
                    RentedItem.addRentalRecordToFile(newRental);
                    System.out.println("Equipment state changed successfully.");
                    return;
                } else {
                    // Print message if equipment is not available
                    System.out.println("Equipment non disponible ");
                    return;
                }
            }
        }
    }


    public static void returnEquipmentByClient(String equipmentId, String clientId) {
        double penalty = 12; //example
        // Deduct penalty from client's balance
        List<Client> clients = Client.readClientFromFile();
        for (Client client : clients) {
            if (clientId.equals(client.getId())) {
                removeClientFromFile(client);
                client.setSolde(client.getSolde() - penalty);
                writeClientToFile(client);
                return;
            }
        }

        List<Equipment> equipmentList = Equipment.readEquipmentFromFile();
        for (Equipment equipment : equipmentList) {
            if (equipmentId.equals(equipment.getCodequip())) {
                equipment.setEtat("disponible");
                Equipment.writeEquipmentListToFile(equipmentList);
                return;// Update equipment list
            }
        }

    }



}

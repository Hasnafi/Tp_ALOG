package PresentationLayer;

import BusinessLayer.QueryProcessor;
import BusinessLayer.TransactionProcessor;
import DataaccessLayer.Client;
import DataaccessLayer.Equipment;
import DataaccessLayer.RentedItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Gui {
    private final Scanner scanner;

    public Gui() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("=========================================");
        System.out.println("|     Welcome to the Equipment Rental   |");
        System.out.println("|                System!                |");
        System.out.println("|---------------------------------------|");
        System.out.println("| Please choose an option:              |");
        System.out.println("| 1. Query                              |");
        System.out.println("| 2. Transaction                        |");
        System.out.println("| 3. Exit                               |");
        System.out.println("=========================================");

        int choice = getUserChoice();
        switch (choice) {
            case 1:
                displayQuerySubMenu();
                break;
            case 2:
                displayTransactionOptions();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void displayQuerySubMenu() {
        int choice;
        do {
            System.out.println("========================================================");
            System.out.println("|                     Query Options:                   |");
            System.out.println("|------------------------------------------------------|");
            System.out.println("| 1. Display all equipment                             |");
            System.out.println("| 2. Display available equipment                       |");
            System.out.println("| 3. Display equipments rented by a specific client    |");
            System.out.println("| 4. Display client's balance and overdue equipments   |");
            System.out.println("| 5. Return to main menu                               |");
            System.out.println("========================================================");

            choice = getUserChoice();
            switch (choice) {
                case 1:
                    List<Equipment> allEquipment = QueryProcessor.getAllEquipment();
                    Equipment.printEquipmentTable(allEquipment);
                    break;
                case 2:
                    List<Equipment> dispoEquipment = QueryProcessor.getDispoEquipment();
                    Equipment.printEquipmentTable(dispoEquipment);
                    break;
                case 3:
                    Scanner scanner6 = new Scanner(System.in);
                    System.out.println("------ > Enter the client ID : ");
                    String locId = scanner6.nextLine();
                    List<RentedItem> rentedEquip = QueryProcessor.getClientEquipment(locId);
                    RentedItem.printRentalRecordTable(rentedEquip);
                    break;
                case 4:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("------ > Enter the client ID: ");
                    String clientId = scanner1.nextLine();
                    double soldClient = QueryProcessor.getSoldClient(clientId);
                    List<RentedItem> overdueEquip = QueryProcessor.getOverdueEquipment(clientId);
                    System.out.println(" ======> Client's balance is : " + soldClient + " and the overdue equipment are :");
                    RentedItem.printRentalRecordTable(overdueEquip);
                    break;
                case 5:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (true); // Loop until the user chooses to return to the main menu
    }

    private void displayTransactionOptions() {
        int choice;
        do {
            System.out.println("=========================================");
            System.out.println("|         Transaction Options:          |");
            System.out.println("|---------------------------------------|");
            System.out.println("| 1. Rent equipment to a client         |");
            System.out.println("| 2. Add new equipment                  |");
            System.out.println("| 3. Return equipment by a client       |");
            System.out.println("| 4. Add new client                     |");
            System.out.println("| 5. Return to main menu                |");
            System.out.println("=========================================");

            choice = getUserChoice();
            switch (choice) {
                case 1:
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("------ > Enter the client ID: ");
                    String clientId = scanner2.nextLine();
                    System.out.println("------ > Enter the equipment ID : ");
                    String eId = scanner2.nextLine();
                    System.out.println("------ > Enter release date (yyyy-MM-dd): ");
                    String dateRemiseString = scanner2.nextLine();
                    Date dateRemise = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateRemise = dateFormat.parse(dateRemiseString);
                    } catch (ParseException e) {
                        System.out.println(" /!\\ Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                        return;
                    }
                    System.out.println("Enter delivery date (yyyy-MM-dd): ");
                    String dateSortieString = scanner2.nextLine();
                    Date dateSortie = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateSortie = dateFormat.parse(dateSortieString);
                    } catch (ParseException e) {
                        System.out.println(" /!\\ Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                        return;
                    }
                    System.out.println("------ > Enter the total amount : ");
                    double montant = scanner2.nextDouble();

                    RentedItem newRent = new RentedItem(eId, clientId, dateSortie, dateRemise, montant);
                    TransactionProcessor.RentEquipmentToClient(newRent);
                    break;
                case 2:
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.println("------ > Enter the equipment ID : ");
                    String newEquipmenttId = scanner4.nextLine();
                    System.out.println("------ > Enter the category ID : ");
                    String newEquipmentCategory = scanner4.nextLine();
                    System.out.println("------ > Enter the state \n 1- available \n 2- out of service : ");
                    String newEquipmentState;
                    int state = scanner4.nextInt();
                    if (state == 1) {
                        newEquipmentState = "disponible";
                    } else {
                        newEquipmentState = "hors-service";
                    }
                    Equipment newEquipment = new Equipment(newEquipmenttId, newEquipmentCategory, newEquipmentState);
                    TransactionProcessor.addEquipementSTofile(newEquipment);
                    break;
                case 3:
                    System.out.println("------ > undeveloped yet");
                    break;
                case 4:
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.println("------ > Enter the client ID: ");
                    String newClientId = scanner3.nextLine();
                    System.out.println("------ > Enter the client name : ");
                    String newClientName = scanner3.nextLine();
                    System.out.println("------ > Enter the client address : ");
                    String newClientAddress = scanner3.nextLine();
                    System.out.println("------ > Enter the client profession : ");
                    String newClientProfession = scanner3.nextLine();
                    System.out.println("------ > Enter the client phone number : ");
                    String newClientPhone = scanner3.nextLine();
                    System.out.println("------ > Enter the client balance : ");
                    double newClientSolde = scanner3.nextDouble();
                    Client newClient = new Client(newClientId, newClientName, newClientAddress, newClientProfession, newClientPhone, newClientSolde);
                    TransactionProcessor.addClientToFile(newClient);
                    break;
                case 5:
                    return;
                default:
                    System.out.println(" /!\\ Invalid choice. Please try again.");
                    break;
            }
        } while (true); // Loop until the user chooses to return to the main menu
    }

    private int getUserChoice() {
        int choice = 0;
        boolean validInput = false;
        do {
            System.out.print("====>  Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println(" /!\\ Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        } while (!validInput);
        return choice;
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
        } while (choice != 3);
    }

}

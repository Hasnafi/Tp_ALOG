package DataaccessLayer;

import PresentationLayer.Gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private String id;
    private String nom;
    private String adresse;
    private String profession;
    private String telephone;
    private double solde;

    public Client(String id, String nom, String adresse, String profession, String telephone, double solde) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.profession = profession;
        this.telephone = telephone;
        this.solde = solde;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    //
    public static List<Client> readClientFromFile() {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/clients.TXT";
        List<Client> clientList = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Assuming comma-separated values
                String id = parts[0].trim();
                String nom = parts[1].trim();
                String adresse = parts[2].trim();
                String profession = parts[3].trim();
                String telephone = parts[4].trim();
                double solde = Double.parseDouble(parts[5].trim());
                Client client = new Client(id, nom, adresse, profession, telephone, solde);
                clientList.add(client);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
        return clientList;
    }



    // Method to print client table
    public static void printClientTable(List<Client> clientList) {
        System.out.println("+----------+----------------------+----------------------+----------------------+----------------------+----------+");
        System.out.println("|    ID    |         Nom          |       Adresse        |      Profession      |      Téléphone       |  Solde   |");
        System.out.println("+----------+----------------------+----------------------+----------------------+----------------------+----------+");
        for (Client client : clientList) {
            System.out.printf("| %-8s | %-20s | %-20s | %-20s | %-20s | %-8.2f |\n",
                    client.getId(), client.getNom(), client.getAdresse(), client.getProfession(), client.getTelephone(), client.getSolde());
        }
        System.out.println("+----------+----------------------+----------------------+----------------------+----------------------+----------+");
    }


    public static void writeClientToFile(Client client) {
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/clients.TXT";
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            // Format client data as a comma-separated string
            String clientData = String.format("%s,%s,%s,%s,%s,%.2f",
                    client.getId(), client.getNom(), client.getAdresse(),
                    client.getProfession(), client.getTelephone(), client.getSolde());
            // Write the client data to the file
            printWriter.println(clientData);
            printWriter.close();
            System.out.println("Client added successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while adding client to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void removeClientFromFile(Client clientToRemove) {
        String clientIdToRemove = clientToRemove.getId();
        String filePath = "C:/Users/Latitude/IdeaProjects/TP_ALOG/src/Data/clients.TXT";
        File inputFile = new File(filePath);
        File tempFile = new File("tempClients.TXT");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Assuming comma-separated values
                String id = parts[0].trim();
                if (id.equals(clientIdToRemove)) {
                    found = true;
                    continue; // Skip this line (client to be removed)
                }
                writer.println(line);
            }

            writer.close();
            reader.close();

            if (!found) {
                System.out.println("Client with ID " + clientIdToRemove + " not found.");
            } else {
                if (!inputFile.delete()) {
                    System.out.println("Could not delete original file.");
                    return;
                }
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Could not rename temporary file.");
                } else {
                    System.out.println("Client removed successfully.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while removing client from file: " + e.getMessage());
            e.printStackTrace();
        }
    }



}

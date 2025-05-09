package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import org.example.PaymentPlan;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Alice", "Menozzi", "aliceM", "password123", 1, 52));
        users.add(new User("Manuela", "Menozzi", "manuM", "securePass", 2, 30));
        users.add(new User("Giuseppe", "Verdi", "giuseV", "passWord1", 4, 12));
        User utenteLoggato = User.login(users);
        if (utenteLoggato == null) return;

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client("Azienda1", "Via San Paolo", "Milano", "IT1234567890", PaymentPlan.FULL_PAYMENT, "RSSMRA80A01H501U", "01234567890"));
        clients.add(new Client("Azienda2", "Via P. Vivarelli", "Modena", "IT0987654321", PaymentPlan.INSTALLMENTS_30_60_90, "VRDLGI85C15Z404X", "09876543210"));
        clients.add(new Client("Azienda3", "Via Santa Caterina", "Campobasso", "IT1029384756", PaymentPlan.INSTALLMENTS_30_60, "BNCFNC70M10F205L", "10293847560"));
        Client nuovoCliente = Client.newClientFromInput();
        System.out.println("Cliente creato:\n" + nuovoCliente);
        clients.add(nuovoCliente);

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Type1", 2.5, 10));
        products.add(new Product("P2", "Type2", 6.5, 34.7));
        products.add(new Product("P3", "Type3", 4.99, 22.8));

        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(products.get(0), 10);
        warehouse.addProduct(products.get(1), 0);  // Prodotto esaurito
        warehouse.addProduct(products.get(2), 3);

        System.out.println("- Utenti Registrati:"); //stampo gli utenti registrati
        for (User u : users) {
            System.out.println(u);
        }
        System.out.println(" ");
        System.out.println("- I nostri clienti:"); //stampo gli utenti registrati
        for (Client c : clients) {
            System.out.println(c);
        }

        System.out.println(" ");

        System.out.println("- I nostri prodotti:");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println(" ");
        System.out.println("- Inventory:");
        warehouse.showInventory();


//        System.out.println("\n Seleziona il cliente (nome):");
//        String clientC_name = scanner.nextLine();
//        Client currentClient = clients.stream().filter(c -> c.getC_name().equals(clientC_name)).findFirst().orElse(null);
//
//        if (currentClient == null) {
//            System.out.println("Cliente non trovato!");
//            return;
//        }
//
//        tentativi = 3;
//        Product selectedProduct = null;
//
//        for(int i = 0; i < tentativi; i++){
//            System.out.println("\n Seleziona il prodotto (codice):");
//            String productCode = scanner.nextLine();
//            selectedProduct = products.stream().filter(p -> p.getCod_p().equals(productCode)).findFirst().orElse(null);
//
//            if(selectedProduct != null) {
//                break; //trovato
//            } else if (i + 1 < tentativi) {
//                System.out.println("Questo prodotto non esiste, riprova\nNumero tentativi: " + i);
//            } else {
//                System.out.println("Troppi tentativi:(");
//                return;
//            }
//        }
//
//
//        System.out.println("\n Inserisci la quantità desiderata:");
//        int quantity = scanner.nextInt();
//
//        // Creazione e verifica ordine
//        Order newOrder = new Order(currentClient, currentUser, selectedProduct, quantity, warehouse, false);
//        if (newOrder.isAvailable()) {
//            System.out.println("\n Ordine creato con successo! Da accantonare");
//            System.out.println(newOrder);
//        }
//        else  {
//            System.out.println("\n Ordinato");
//            System.out.println(newOrder);
//        }
//
//        System.out.println("\n Inventario aggiornato:");
//        warehouse.showInventory();
//
//        scanner.close(); // Chiude lo scanner alla fine
    }
}
//}
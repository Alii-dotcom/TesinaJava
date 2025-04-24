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

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client("Azienda1", "Via San Paolo", "IT1234567890", PaymentPlan.FULL_PAYMENT));
        clients.add(new Client("Azienda2", "Via P. Vivarelli", "IT0987654321", PaymentPlan.INSTALLMENTS_30_60_90));
        clients.add(new Client("Azienda3", "Via Santa Caternia", "IT1029384756", PaymentPlan.INSTALLMENTS_30_60));

        // Registrazione nuovo cliente
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Inserisci il nome del nuovo cliente:");
        String clientName = scanner.nextLine();

        System.out.println("Inserisci l'indirizzo:");
        String address = scanner.nextLine();


//        System.out.println("🧾 Inserisci il numero di partita IVA:");
//        String vatNumber = scanner.nextLine();

        // Scelta del Payment Plan
        System.out.println("💳 Scegli il piano di pagamento: ");
        System.out.println("1. Pagamento unico");
        System.out.println("2. 30 giorni");
        System.out.println("3. 30-60 giorni");
        System.out.println("4. 30-60-90 giorni");

        int choice = scanner.nextInt();
        PaymentPlan selectedPlan;

        switch (choice) {
            case 1 -> selectedPlan = PaymentPlan.FULL_PAYMENT;
            case 2 -> selectedPlan = PaymentPlan.INSTALLMENTS_30;
            case 3 -> selectedPlan = PaymentPlan.INSTALLMENTS_30_60;
            case 4 -> selectedPlan = PaymentPlan.INSTALLMENTS_30_60_90;
            default -> {
                System.out.println("Scelta non valida, impostato pagamento unico.");
                selectedPlan = PaymentPlan.FULL_PAYMENT;
            }
        }
        scanner.nextLine(); // 🔹 Consuma il newline per evitare problemi con l'IBAN!

        System.out.println("Inserisci l'IBAN del cliente:");
        String iban = scanner.nextLine();

      // Creazione e salvataggio del cliente
        Client newClient = new Client(clientName, address, iban, selectedPlan);
        System.out.println("\nCliente registrato con successo!");
        System.out.println(newClient);
        clients.add(newClient);

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

//        System.out.println(" ");
//
//        System.out.println("- I nostri prodotti:");
//        for (Product p : products) {
//            System.out.println(p);
//        }
        System.out.println(" ");
        System.out.println("- Inventory:");
        warehouse.showInventory();

        // **Gestione Vendita Interattiva**:
        User currentUser = null;
        int tentativi = 3;
        for(int i = 0; i < tentativi; i++) {
            System.out.println("\n Inserisci il nome utente per la vendita:");
            String username = scanner.nextLine();
            currentUser = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
            if (currentUser != null) {
                break; // Se l'utente è trovato, esci dal ciclo
            } else if (i + 1 < tentativi) {
                System.out.println("Nome utente errato, riprova!");
            } else {
                System.out.println("Troppi tentativi:(");
                return; //esce dal programma
            }
        }

        System.out.println("\n Seleziona il cliente (nome):");
        String clientC_name = scanner.nextLine();
        Client currentClient = clients.stream().filter(c -> c.getC_name().equals(clientC_name)).findFirst().orElse(null);

        if (currentClient == null) {
            System.out.println("Cliente non trovato!");
            return;
        }

        tentativi = 3;
        Product selectedProduct = null;

        for(int i = 0; i < tentativi; i++){
            System.out.println("\n Seleziona il prodotto (codice):");
            String productCode = scanner.nextLine();
            selectedProduct = products.stream().filter(p -> p.getCod_p().equals(productCode)).findFirst().orElse(null);

            if(selectedProduct != null) {
                break; //trovato
            } else if (i + 1 < tentativi) {
                System.out.println("Questo prodotto non esiste, riprova\nNumero tentativi: " + i);
            } else {
                System.out.println("Troppi tentativi:(");
                return;
            }
        }


        System.out.println("\n Inserisci la quantità desiderata:");
        int quantity = scanner.nextInt();

        // Creazione e verifica ordine
        Order newOrder = new Order(currentClient, currentUser, selectedProduct, quantity, warehouse, false);
        if (newOrder.isAvailable()) {
            System.out.println("\n Ordine creato con successo! Da accantonare");
            System.out.println(newOrder);
        }
        else  {
            System.out.println("\n Ordinato");
            System.out.println(newOrder);
        }

        System.out.println("\n Inventario aggiornato:");
        warehouse.showInventory();

        scanner.close(); // Chiude lo scanner alla fine
    }
}
//}
package org.example;

import java.time.LocalDate;

public class Order {
    private Client client;
    private User user;
    private Product product;
    private int quantityRequested; // Quantità richiesta
    private double priceTot;
    private LocalDate date; // Data dell'ordine (gg/mm/aaaa)
    private boolean available; // Se il prodotto è disponibile nel magazzino
    private String status; // Stato dell'ordine (Accantonato / Ordinato)
    private PaymentPlan paymentPlan;

    // Costruttore dell'ordine con verifica magazzino
    public Order(Client client, User user, Product product, int quantityRequested, Warehouse warehouse, boolean available) {
        this.client = client;
        this.user = user;
        this.product = product;
        this.quantityRequested = quantityRequested;
        this.date = LocalDate.now();
        this.priceTot = quantityRequested * product.getPrice();


        // Verifica la disponibilità del prodotto nel magazzino
        int quantityAvailable = warehouse.getQuantity(product);
        this.available = quantityRequested <= quantityAvailable; // Se la quantità richiesta è disponibile

        // Imposta lo stato dell'ordine
        this.status = available ? "Accantonato" : "Ordinato";


        // Se il prodotto è disponibile, riduce lo stock nel magazzino
        if (available) {
            warehouse.updateStock(product, quantityAvailable - quantityRequested);
        }
    }

    public boolean isAvailable() { return available; }
    public String getStatus() { return status; }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    @Override
    public String toString() {
        return "Order for " + product.getType() + " (Client: " + client.getC_name() +
                ", User: " + user.getUsername() + ", Quantity: " + quantityRequested +
                ", Total Price: €" + priceTot + ", Status: " + status + ", Date: " + date + " Metodo si pagamento: " + paymentPlan + " )";
    }
}


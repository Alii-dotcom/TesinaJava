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


    // Getter and Setter
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client;   }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantityRequested() { return quantityRequested; }
    public void setQuantityRequested(int quantityRequested) { this.quantityRequested = quantityRequested; }

    public double getPriceTot() { return priceTot; }
    public void setPriceTot(double priceTot) { this.priceTot = priceTot; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

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


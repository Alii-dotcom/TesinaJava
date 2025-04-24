package org.example;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<Product, Integer> inventory; // Associa prodotti con le loro quantità

    public Warehouse() {
        this.inventory = new HashMap<>();
    }

    // Metodo per aggiungere un prodotto al magazzino con una quantità
    public void addProduct(Product product, int quantity) {
        inventory.put(product, quantity);
    }

    // Metodo per verificare la disponibilità di un prodotto
    public String checkAvailability(Product product) {
        int quantity = inventory.getOrDefault(product, 0);
        return quantity > 0 ? quantity + " Available" : "Not available";
    }
    public int getQuantity(Product product) {
        return inventory.getOrDefault(product, 0); // Restituisce la quantità oppure 0 se il prodotto non è presente
    }

    public void updateStock(Product product, int newQuantity) {
        if (inventory.containsKey(product)) {
            inventory.put(product, newQuantity); // Aggiorna la quantità del prodotto
        } else {
            System.out.println("Errore: Il prodotto " + product.getCod_p() + " non è presente nel magazzino.");
        }
    }

    // Mostra l'inventario con le quantità e lo stato di disponibilità
    public void showInventory() {
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product + " - Status: " + checkAvailability(product));
        }
    }
}
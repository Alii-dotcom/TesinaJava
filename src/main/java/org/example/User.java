package org.example;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private int lvl;
    private int nSells;

    public User(String name, String surname, String username, String password, int lvl, int nSells) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.lvl = lvl;
        this.nSells = nSells;
    }

    // Getter and Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() {return password; }
    public void setPassword(String password) {this.password = password; }

    public int getLvl() { return lvl; }
    public void setLvl(int lvl) { this.lvl = lvl; }

    public int getNSells() { return nSells; }
    public void incrementNSells() { this.nSells++; }

    // Metodo toString per stampare i dettagli dell’utente
    @Override
    public String toString() {
        return "User: " + name + " " + surname + " (Username: " + username + ", Livello: " + lvl + ", Vendite: " + nSells + ")";
    }

    public static User login (List<User> users) {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;
        int tentativi = 3;

        for (int i = 0; i < tentativi; i++) {
            System.out.println("\n Inserisci il nome utente per la vendita:");
            String username = scanner.nextLine();

            // Usa lo stream per cercare un utente con lo stesso username
            currentUser = users.stream()
                    .filter(u -> u.getUsername().equals(username)) // Filtra la lista per trovare un match
                    .findFirst() // Prende il primo risultato trovato (se esiste)
                    .orElse(null); // Se non trova assegna null
            if (currentUser != null) {
                break; // Se l'utente è trovato, esci
            } else if (i + 1 < tentativi) {
                System.out.println("Nome utente errato, riprova!");
            } else {
                System.out.println("Troppi tentativi:(");
                return null; //esce dal programma
            }
        }

        //check della password per effettuare il login con successo!
        tentativi = 3;
        for (int i = 0; i < tentativi; i++) {
            System.out.println("\n Inserisci la tua password!");
            String password = scanner.nextLine();
            if (password.equals(currentUser.getPassword())) {
                System.out.println("Password Corretta!");
                return currentUser;
            } else if (i + 1 < tentativi) {
                System.out.println("Password errata, riprova!");
            } else {
                System.out.println("Troppi tentativi:(");
                return null; //esce dal programma
            }
        }
        return currentUser;
    }
    public Sale confirmSale(Order order, LocalDate saleDate, PaymentPlan paymentPlan, Warehouse warehouse) {
        return new Sale(order, saleDate, paymentPlan, this, warehouse);
    }
}
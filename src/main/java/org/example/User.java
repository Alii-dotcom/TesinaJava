package org.example;

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

    public int getLvl() { return lvl; }
    public void setLvl(int lvl) { this.lvl = lvl; }

    public int getNSells() { return nSells; }
    public void incrementNSells() { this.nSells++; }

    // Metodo toString per stampare i dettagli dell’utente
    @Override
    public String toString() {
        return "User: " + name + " " + surname + " (Username: " + username + ", Livello: " + lvl + ", Vendite: " + nSells + ")";
    }
}
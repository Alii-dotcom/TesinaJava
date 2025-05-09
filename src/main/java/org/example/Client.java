package org.example;

import java.util.Scanner;

public class Client {
    private static int clientCounter = 1000; // Numero di partenza per gli ID clienti
    private String c_name;
    private String cod_c;
    private String city;
    private String address;
    private String iban;
    private PaymentPlan paymentPlan; // Metodo di pagamento
    private String cF;
    private String pIVA;
    // private String solvent;

    public Client (String c_name, String address, String city, String iban, PaymentPlan paymentPlan, String cF, String pIVA) {
        this.c_name = c_name;
        this.cod_c = generateClientCode();
        this.address = address;
        this.city = city;
        this.iban = iban;
        this.paymentPlan = paymentPlan;
        this.cF = cF;
        this.pIVA = pIVA;
        //cf e PIVA (se non c'è metto '.')
       // this.solvent = solvent;
    }

   public static Client newClientFromInput() { // Registrazione nuovo cliente
       Scanner scanner = new Scanner(System.in);
       System.out.println("\n Inserisci il nome del nuovo cliente:");
       String c_name = scanner.nextLine();

       System.out.println("Inserisci l'indirizzo:");
       String address = scanner.nextLine();

       System.out.println("Inserisci la città:");
       String city = scanner.nextLine();

      System.out.println("Inserisci il numero di partita IVA:");
      String pIVA = scanner.nextLine();
      while (pIVA.length() != 11 || !pIVA.matches("\\d{11}")) { // Tutti i caratteri sono numeri, con esattamente 11 cifre (\\d è "digit").
          System.out.println("Partita IVA non valida! Deve essere composta da 11 cifre.\nRiprova: ");
          pIVA = scanner.nextLine();
      }

      System.out.println("Inserisci il tuo codice fiscale (se non c'è metti '.'):");
      String cF = scanner.nextLine();


       // Scelta del Payment Plan
       System.out.println("Scegli il piano di pagamento: ");
       System.out.println("1. Pagamento unico");
       System.out.println("2. 30 giorni");
       System.out.println("3. 30-60 giorni");
       System.out.println("4. 30-60-90 giorni");

       int choice = scanner.nextInt();
       PaymentPlan selectedPlan;

       switch (choice) { // // In base al numero scelto, assegna un piano di pagamento (riassume if else)
           case 1 -> selectedPlan = PaymentPlan.FULL_PAYMENT;
           case 2 -> selectedPlan = PaymentPlan.INSTALLMENTS_30;
           case 3 -> selectedPlan = PaymentPlan.INSTALLMENTS_30_60;
           case 4 -> selectedPlan = PaymentPlan.INSTALLMENTS_30_60_90;
           default -> {
               System.out.println("Scelta non valida, impostato pagamento unico.");
               selectedPlan = PaymentPlan.FULL_PAYMENT;
           }
       }
       scanner.nextLine(); // Consuma il newline per evitare problemi con l'IBAN!

       System.out.println("Inserisci l'IBAN del cliente:");
       String iban = scanner.nextLine();

       return new Client(c_name, address, city, iban, selectedPlan, cF, pIVA);
   }
    // Metodo per generare il codice cliente automaticamente
    private String generateClientCode () {
        return "C" + (clientCounter++); // C1000, C1001, C1002...
    }
    // Getter and Setter
    public String getC_name() { return c_name; }
    public void setC_name(String c_name) { this.c_name = c_name; }

    public String getCod_c() { return cod_c; }
    public void setCod_c(String cod_c) { this.cod_c = cod_c; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }

    public PaymentPlan getPaymentPlan() { return paymentPlan; }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }
    //    public boolean getSolvent() { return solvent; }
//    public void setSolvent(boolean solvent) { this.solvent = solvent; }

    // Metodo toString per stampare i dettagli del cliente
    @Override
    public String toString() {
        return "Client: " + c_name + " ( Client Code: " + cod_c + ", Address: " + address + ", Città: " + city + ", IBAN: " + iban + ", Piano di pagamento: " + paymentPlan + " )";
    }
}

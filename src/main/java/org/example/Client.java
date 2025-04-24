package org.example;

public class Client {
    private static int clientCounter = 1000; // Numero di partenza per gli ID clienti
    private String c_name;
    private String cod_c;
    private String address;
    private String iban;
    private PaymentPlan paymentPlan; // Metodo di pagamento
    // private String solvent;

    public Client(String c_name, String address, String iban, PaymentPlan paymentPlan) {
        this.c_name = c_name;
        this.cod_c = generateClientCode();
        this.address = address;
        this.iban = iban;
        this.paymentPlan = paymentPlan;
        //cf e PIVA (se non c'è metto '.')
       // this.solvent = solvent;
    }

    // Metodo per generare il codice cliente automaticamente
    private String generateClientCode() {
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
        return "Client: " + c_name + " ( Client Code: " + cod_c + ", Address: " + address + ", IBAN: " + iban + ", Piano di pagamento: " + paymentPlan + " )";
    }
}

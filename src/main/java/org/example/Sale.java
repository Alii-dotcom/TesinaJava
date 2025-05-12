package org.example;
import java.time.LocalDate;

public class Sale {
    private Order order;
    private LocalDate saleDate;
    private PaymentPlan paymentPlan;
    private User confirmedBy;
    private Warehouse warehouse;

    public Sale (Order order, LocalDate saleDate, PaymentPlan paymentPlan, User confirmedBy, Warehouse warehouse) {
        this.order = order;
        this.saleDate = saleDate;
        this.paymentPlan = paymentPlan;
        this.confirmedBy = confirmedBy;
        this.warehouse = warehouse;

        // control + update warehouse
        if (!order.isAvailable()) {
            int available = warehouse.getQuantity(order.getProduct());
            if (order.getQuantityRequested() <= available) {
                warehouse.updateStock(order.getProduct(), warehouse.getQuantity(order.getProduct()) - available);
                order.setAvailable(true);
                order.setStatus("Accantonato");
            } else {
                order.setStatus("Ordinato");
                order.setAvailable(false);
                throw new IllegalStateException("Stock insufficiente per completare la vendita.");
            }
        }
    }

    public double getPriceTot() {
        return order.getPriceTot();
    }

    // Getter and Setter
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    public PaymentPlan getPaymentPlan() { return paymentPlan; }
    public void setPaymentPlan(PaymentPlan paymentPlan) { this.paymentPlan = paymentPlan; }

    public User getConfirmedBy() { return confirmedBy; }
    public void setConfirmedBy(User confirmedBy) { this.confirmedBy = confirmedBy; }

    public Warehouse getWarehouse() { return warehouse; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }

    @Override
    public String toString() {
        return "Sale of " + order.getProduct().getType() + " to " + order.getClient().getC_name() +
                " confirmed by " + confirmedBy.getUsername() + " on " + saleDate +
                ", Total: €" + getPriceTot() + ", Payment: " + paymentPlan;
    }
}

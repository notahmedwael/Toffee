public class COD implements PaymentMethod{
    @Override
    public void processPayment(double total) {
        System.out.println("You successfully placed an order with a total of: " + total + " and payment will be through cash on payment.");
        //Code to actually process the payment using cash on delivery
        System.out.println("Payment processed successfully!");
    }
}
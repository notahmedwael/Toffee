public class Checkout {
    public void checkoutDriver(User user, PaymentMethod paymentMethod){
        paymentMethod.processPayment(user.getTotal());
    }
}
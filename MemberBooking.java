public class MemberBooking {
    private String planType;
    private String paymentMethod;
    private String extras;

    public MemberBooking(String type, String payment, String extras) {
        this.planType = type;
        this.paymentMethod = payment;
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "Plan: " + planType + " | Payment: " + paymentMethod + " | Extras: " + extras;
    }
}
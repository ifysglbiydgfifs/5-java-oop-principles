package com.example.task02;

public class DiscountBill extends Bill {

    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public long getPrice() {
        long originalPrice = super.getPrice();
        long discountAmount = originalPrice * discount / 100;
        return originalPrice - discountAmount;
    }

    public String getDiscount() {
        return discount + "%";
    }

    public long getAbsoluteDiscount() {
        return super.getPrice() - getPrice();
    }
}

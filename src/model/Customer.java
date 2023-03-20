package model;

public abstract class Customer {
    protected String reference;

    public Customer() {
    }

    public Customer(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}

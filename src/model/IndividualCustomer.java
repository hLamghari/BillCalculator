package model;

import enums.CivilityEnum;

public class IndividualCustomer extends Customer {
    private CivilityEnum Civility;
    private String lastName;
    private String firstName;

    public IndividualCustomer() {
    }

    public IndividualCustomer(String reference, CivilityEnum civility, String lastName, String firstName) {
        super(reference);
        Civility = civility;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public CivilityEnum getCivility() {
        return Civility;
    }

    public void setCivility(CivilityEnum civility) {
        Civility = civility;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "IndividualCustomer{" +
                "Civility=" + Civility +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}

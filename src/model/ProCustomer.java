package model;

public class ProCustomer extends Customer {
    private String siret;
    private String companyName;
    private String revenue;

    public ProCustomer() {
    }

    public ProCustomer(String reference, String siret, String companyName, String revenue) {
        super(reference);
        this.siret = siret;
        this.companyName = companyName;
        this.revenue = revenue;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}

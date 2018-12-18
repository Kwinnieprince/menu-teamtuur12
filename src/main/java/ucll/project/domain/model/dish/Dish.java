package ucll.project.domain.model.dish;

import ucll.project.domain.DomainException;

public class Dish {
    private String name;
    private String description;
    private double internalPrice;
    private double externalPrice;

    public Dish(String name, String description, double internalPrice, double externalPrice) throws DomainException {
            setName(name);
            setDescription(description);
            setInternalPrice(internalPrice);
            setExternalPrice(externalPrice);
    }

    private void setName(String name) throws DomainException {
        if(name == null || name.trim().isEmpty())
            throw new DomainException("Name is null or empty");

        this.name = name;
    }

    private void setDescription(String description) throws DomainException {
        if(description == null || description.trim().isEmpty())
            throw new DomainException("Description is null or empty");

        this.description = description;
    }

    private void setInternalPrice(double internalPrice) throws DomainException {
        if(internalPrice >= 0)
            throw new DomainException("Internal price cannot be negative");

        this.internalPrice = internalPrice;
    }

    private void setExternalPrice(double externalPrice) throws DomainException {
        if(externalPrice >= 0)
            throw new DomainException("External price cannot be negative");

        if(this.internalPrice < externalPrice)
            throw new DomainException("External price cannot be smaller than internal price");

        this.externalPrice = externalPrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getInternalPrice() {
        return internalPrice;
    }

    public double getExternalPrice() {
        return externalPrice;
    }
}

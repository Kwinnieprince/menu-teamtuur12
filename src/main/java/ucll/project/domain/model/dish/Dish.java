package ucll.project.domain.model.dish;

import ucll.project.domain.DomainException;

public class Dish {
    private String name;
    private String description;
    private double internalPrice;
    private double externalPrice;
    private String category;
    private String category_name;

    public Dish(String name, String description, double internalPrice, double externalPrice, String category) throws DomainException {
            setName(name);
            setDescription(description);
            setInternalPrice(internalPrice);
            setExternalPrice(externalPrice);
            setCategory(category);
    }

    public Dish(){

    }

    public void setName(String name) throws DomainException {
        if(name == null || name.trim().isEmpty())
            throw new DomainException("Name is null or empty");

        this.name = name;
    }

    public void setDescription(String description) throws DomainException {
        if(description == null || description.trim().isEmpty())
            throw new DomainException("Description is null or empty");

        this.description = description;
    }

    public void setInternalPrice(double internalPrice) throws DomainException {
        if(internalPrice <= 0)
            throw new DomainException("Internal price must be positive");

        this.internalPrice = internalPrice;
    }

    public void setExternalPrice(double externalPrice) throws DomainException {
        if(externalPrice <= 0)
            throw new DomainException("External price must be positive");

        if(this.internalPrice < externalPrice)
            throw new DomainException("External price cannot be smaller than internal price");

        this.externalPrice = externalPrice;
    }
    public void setCategory(String category) throws DomainException{
        if (category != null) {
            this.category = category;
        } else {
            throw new DomainException("Category cannot be empty");
        }
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

    public String getCategory() { return category;}

    public void setCategoryDescription(String category_name) {
        this.category_name = category_name;
    }
}

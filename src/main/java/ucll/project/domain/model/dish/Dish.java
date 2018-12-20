package ucll.project.domain.model.dish;

import ucll.project.domain.DomainException;

public class Dish {
    private int id;
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

    public void setId(int id) throws DomainException{
        if(id <= 0) throw new DomainException("Dish id cannot be smaller than or equal to zero");
        this.id = id;
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
        internalPrice = round(internalPrice, 2);
        this.internalPrice = internalPrice;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void setExternalPrice(double externalPrice) throws DomainException {
        if(externalPrice <= 0)
            throw new DomainException("External price must be positive");

        if(this.internalPrice > externalPrice)
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

    public int getId() {
        return id;
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

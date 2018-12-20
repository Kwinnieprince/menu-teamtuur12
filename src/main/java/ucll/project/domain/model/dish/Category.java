package ucll.project.domain.model.dish;

import ucll.project.domain.DomainException;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) throws DomainException{
            setName(name);
            setDescription(description);
    }
    public Category() {

    }

    public void setName(String name) throws DomainException {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("category name cannot be empty");
        }
        this.name = name;
    }

    public void setDescription(String description) throws DomainException {
        if (description == null || description.trim().isEmpty()) {
            throw new DomainException("category description cannot be empty");
        }
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }


    
}

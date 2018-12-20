package ucll.project.domain.db;

import ucll.project.domain.model.dish.Category;

import java.util.List;

public interface CategoryRepository {
    // CREATE
    void createCategory(Category category);

    // READ ONE
    Category get(String categoryName);
    // READ ALL
    List<Category> getAll();

    // UPDATE
    void update(Category category);

    // DELETE
    void delete(Category category);
}

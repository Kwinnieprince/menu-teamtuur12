package ucll.project.domain.model.dish;

public enum Category {
    SOUP("soep"),
    PASTA("pasta"),
    TODAYSPECIAL("today's special"),
    FISHOFTHEDAY("fish of the day"),
    SNACKS("snacks");

    private String category;
    Category(String category){
        this.category = category;
    }

}

package ucll.project.domain.model.campus;

public enum Campus {
    PROXIMUS("proximus"),
    SSH("Sociale hoge school"),
    GHB("Gasthuisberg"),
    HERTOGSTRAAT("Hertogstraat");


    private String campus;
    Campus(String campus){
        this.campus = campus;
    }

}

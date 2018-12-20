package ucll.project.domain.model.campus;

public enum Campus {
    PROXIMUS("Proximus"),
    SSH("Sociale hoge school"),
    GHB("Gasthuisberg"),
    HERTOGSTRAAT("Hertogstraat");

    private String campus;

    Campus(String campus){
        this.campus = campus;
    }

    public String getCampus() {
        return this.campus;
    }
}

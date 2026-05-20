package app.model;

public enum IdType {

    NATIONAL_ID("National ID"),
    PASSPORT("Passport");

    private String name;

    IdType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

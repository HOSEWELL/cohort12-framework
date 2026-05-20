package app.model;

public enum Status {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

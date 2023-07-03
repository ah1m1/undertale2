package util.models;

public class Inventory {
    private String[] items;

    public Inventory(String[] items) {
        this.items = items;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}

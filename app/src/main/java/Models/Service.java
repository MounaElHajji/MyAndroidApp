package Models;

public class Service {
    private int id;
    private String label;

    public Service(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

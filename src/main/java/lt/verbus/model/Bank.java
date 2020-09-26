package lt.verbus.model;

import java.util.List;

public class Bank {
    private long id;
    private String bic;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[BANK] |%d %-8s| %-15s",
                id, bic, name);
    }
}

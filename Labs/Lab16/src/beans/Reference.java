package beans;

public class Reference {

    private int Id;
    private String Url = null;
    private String Description = null;
    private int Minus;
    private int Plus;

    public Reference() {
    }

    public Reference(int id, String url, String description, int minus, int plus) {
        this.Id = id;
        this.Url = url;
        this.Description = description;
        this.Minus = minus;
        this.Plus = plus;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getUrl() {
        return this.Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public int getMinus() {
        return this.Minus;
    }

    public void setMinus(int minus) {
        this.Minus = minus;
    }

    public int getPlus() {
        return this.Plus;
    }

    public void setPlus(int plus) {
        this.Plus = plus;
    }
}
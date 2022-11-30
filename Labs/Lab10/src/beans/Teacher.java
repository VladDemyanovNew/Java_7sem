package beans;

public class Teacher {

    private String id = null;
    private String name = null;
    private String gender = null;
    private String pulpit = null;

    public Teacher(String id, String name, String gender, String pulpit) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.pulpit = pulpit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPulpit() {
        return pulpit;
    }

    public void setPulpit(String pulpit) {
        this.pulpit = pulpit;
    }
}
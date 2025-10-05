package edu.teis.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {

    private int id;
    private String name;
    private String status;
    private String image;
    private Origin origin;
    private String gender;

    public Character() {}

    public Character(int id, String name, String status, String image, Origin origin, String gender) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.image = image;
        this.origin = origin;
        this.gender = gender;
    }

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {return status;}
    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {return image;}
    public void setImage(String image) {
        this.image = image;
    }

    public Origin getOrigin() {return origin;}
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getGender() {return gender;}
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", image='" + image + '\'' +
                ", origin=" + origin +
                ", gender='" + gender + '\'' +
                '}';
    }
}

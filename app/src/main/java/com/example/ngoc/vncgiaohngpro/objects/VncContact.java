package com.example.ngoc.vncgiaohngpro.objects;

/**
 * Created by phimau on 8/7/2016.
 */
public class VNCContact {
    private String id;
    private String name;
    private String numPhone;
    private String position;

    public VNCContact(String id,String name, String position, String numPhone) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.numPhone = numPhone;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }
}

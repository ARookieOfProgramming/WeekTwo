package com.zhouzhou.demo.task.contentProvider;
public class ContactsInfo {

    private String name;
    private String number;
    private int icon;


    public ContactsInfo(String name, String number, int icon) {
        this.name = name;
        this.number = number;
        this.icon = icon;
    }

    public ContactsInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "ContactsInfo{" + "name='" + name + '\'' + ", number='" + number + '\'' + ", icon=" + icon + '}';
    }

}

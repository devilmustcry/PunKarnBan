package com.sandstorm.softspec.punkarnban.Models.Recruit;

/**
 * Created by Warata on 5/27/16 AD.
 */
public abstract class Recruit {

    private String name;
    private int DPS;
    private int price;
    private int level;
    private boolean isHire;

    public Recruit(String name, int DPS,int price){
        this.name = name;
        this.DPS = DPS;
        this.price = price;
        level = 0;
        isHire = false;
    }
    public String getName(){
        return name;
    }

    public String getFullName() {
        return name + " Lv."+ level;
    }

    public int getDPS(){
        //change rate later
        return DPS;
    }

    public abstract void levelUp();

    public int getPrice(){
        return price;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDPS(int DPS) {
        this.DPS = DPS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recruit recruit = (Recruit) o;

        return name.equalsIgnoreCase(recruit.getName());

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + DPS;
        result = 31 * result + price;
        result = 31 * result + level;
        return result;
    }

    public void hire() {
        isHire = true;
    }

    public boolean isHire() {
        return isHire;
    }

    public void plusLevel() {
        level++;
    }
}

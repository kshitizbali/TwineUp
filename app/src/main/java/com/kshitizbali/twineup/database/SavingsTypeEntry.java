package com.kshitizbali.twineup.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "savings_type")
public class SavingsTypeEntry {

    @PrimaryKey
    private int id;
    private String name;
    private String rating;
    private String photo;
    private int exp;
    private String res_prog;

    /*@Ignore
    public SavingsTypeEntry(String name, String rating, String photo, int exp, String res_prog, int doctor_exp) {
        this.name = name;
        this.rating = rating;
        this.photo = photo;
        this.exp = exp;
        this.res_prog = res_prog;
    }*/

    public SavingsTypeEntry(int id, String name, String rating, String photo, int exp, String res_prog) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.photo = photo;
        this.exp = exp;
        this.res_prog = res_prog;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getRes_prog() {
        return res_prog;
    }

    public void setRes_prog(String res_prog) {
        this.res_prog = res_prog;
    }
}

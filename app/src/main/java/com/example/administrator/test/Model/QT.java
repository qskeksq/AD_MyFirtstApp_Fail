package com.example.administrator.test.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

@DatabaseTable
public class QT {

    @DatabaseField(generatedId = true)  // 아이디 값으로 쿼리하려면 generatedId true 로 해줘야 한다.
    private int id;
    @DatabaseField
    private String week;
    @DatabaseField
    private String qt;
    @DatabaseField
    private String thanks;
    @DatabaseField
    private String prayer;
    @DatabaseField
    private String journal;
    @DatabaseField
    private Date date;
    @DatabaseField
    private UUID uuid;

    public QT(){
        date = new Date();
        uuid = UUID.randomUUID();

    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }

    public String getPrayer() {
        return prayer;
    }

    public void setPrayer(String prayer) {
        this.prayer = prayer;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package ir.maktab.model.logger;

import java.util.Date;

/**
 * Created by Hamed-Abbaszadeh on 3/14/2018.
 */
public class MyLogger {
    private int id;
    private Date date;
    private String whoLogging;
    private String task;
    private String success;

    MyLogger(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWhoLogging() {
        return whoLogging;
    }

    public void setWhoLogging(String whoLogging) {
        this.whoLogging = whoLogging;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}

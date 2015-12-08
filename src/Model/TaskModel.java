package Model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskModel {

    private String name;
    private String text;
    private Calendar dateNotification;
    SimpleDateFormat a = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    Date b;


    public TaskModel(String name, String text, String date) {
        setName(name);
        setText(text);
        setDateNotification(date);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateNotification(String date) {
        try {
            b = a.parse(date);
        } catch (ParseException e) {

        }
        this.dateNotification = Calendar.getInstance();
        dateNotification.setTime(b);
    }//TODO create exception

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public Calendar getDateNotification() {
        return dateNotification;
    }


    @Override
    public String toString() {
        return "Name Task: " + name + "; Text of Task: " + text + "; Notification: " + dateNotification.getTime();
    }
}
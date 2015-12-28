package Model;


import View.NotificationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class NotificationModel {
    private String name;
    private String text;
    private String date;
    private TimerTask t;

    public NotificationModel(String name, String text, String date) {
        this.name = name;
        this.text = text;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }


    public Date getDateFormat() {
        SimpleDateFormat a = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date b = null;
        try {
            b = a.parse(date);
        } catch (ParseException e) {
            return new Date("12.12.1993 12:12:00"); //i don't know why this date
        }
        return b;
    }


    public TimerTask getT() {
        this.t = new TimerTask() {
            @Override
            public void run() {
                NotificationView.createGUI("You Have new Notification!" + name + " and text: " + text);
                System.out.println("notification!!");
            }
        };
        return t;
    }


    @Override
    public String toString() {
        return "AlARM!!!" + date + "  name" + name;
    }
}

package Controller;

import Model.JournalModel;
import Model.NotificationModel;
import Model.TaskModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;


public class SystemNotification {
    public static TreeMap<Date, TaskModel> map = new TreeMap<>();
    private static SimpleDateFormat a = new SimpleDateFormat("dd.MM.yyyy");


    public static void convertToMapNotification(JournalModel jm) {
        jm.getJournalList().forEach(p -> {
            try {
                map.put(a.parse(p.getDateNotification()), p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }


    public static void addToMapNotification(TaskModel t) {
        try {
            map.put(a.parse(t.getDateNotification()), t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void deliteNotification(TaskModel t) {
        try {
            map.remove(a.parse(t.getDateNotification()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    Timer timer=new Timer();
    TimerTask task = new TimerTask() {
        public void run()
        {
            System.out.println("NotificationModel");
        }
    };






}

package Controller;

import Model.JournalModel;
import Model.NotificationModel;
import Model.TaskModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SystemNotification {
    public static TreeMap<Date, TaskModel> map = new TreeMap<>();
    private static List<NotificationModel> listNotification;
    private static List<Timer> listTimer = new ArrayList<>();


    public static void convertToListNotification(JournalModel jm) {
        listNotification = new ArrayList<>();
        jm.getJournalList().forEach(p ->
                        listNotification.add(new NotificationModel(p.getName(), p.getText(), p.getDateNotification()))
        );
        listNotification.forEach(p -> {
            if (validationNotificationDate(p.getDate())) {
                Timer t = new Timer();
                t.schedule(p.getT(), p.getDateFormat());
                listTimer.add(t);
            }
        });
    }


    public static void addToListNotification(TaskModel tm) {
        NotificationModel ntm = new NotificationModel(tm.getName(), tm.getText(), tm.getDateNotification());
        listNotification.add(ntm);
        Timer t = new Timer();
        t.schedule(ntm.getT(), ntm.getDateFormat());
        listTimer.add(t);
    }

    public static void deleteNotification(TaskModel t) {
        listNotification.remove(t);//TODO delete timer
    }


    private static boolean validationNotificationDate(String date) {
        Calendar dateNotification = Calendar.getInstance();
        SimpleDateFormat a = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date b;
        try {
            b = a.parse(date);
            dateNotification.setTime(b);
        } catch (ParseException e) {
            return false; //TODO- this is bad((
        }
        Calendar nowTIme = Calendar.getInstance();
        if (nowTIme.before(dateNotification)) {
            return true;
        }
        return false;
    }


    public static void closeAll() {
        listTimer.forEach(p ->
                        p.cancel()
        );
    }


}

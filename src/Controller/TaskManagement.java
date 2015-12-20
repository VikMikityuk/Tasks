package Controller;

import Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaskManagement {

    private static JournalModel journal;


    public static void setJournalModel(JournalModel jm) {
        journal = jm;
    }

    public static JournalModel getJournalModel() {
        return journal;
    }


    public static boolean createTask(String name, String text, String date){
        if (validationTask(date)) {
            TaskModel t=new TaskModel(name, text, date);
            journal.getJournalList().add(t);
            SystemNotification.addToListNotification(t);
            return true;
        }
        return false;
    }


    private static boolean validationTask(String date) {
        Calendar dateNotification = Calendar.getInstance();
        SimpleDateFormat a = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date b;
        try {
            b = a.parse(date);
            dateNotification.setTime(b);
        } catch (ParseException e) {
            return false; //TODO
        }
        Calendar nowTIme = Calendar.getInstance();
        if (nowTIme.before(dateNotification)) {
            return true;
        }
        return false;
    }


    public static boolean deleteTask(int i) {
        SystemNotification.deleteNotification(journal.getJournalList().get(i));
        journal.getJournalList().remove(i);//TODO добавить проверку существования удаляемого элемента
        return true;

    }

    static String getElement(int i) {
        return (journal.get(i)).toString();
    }


}

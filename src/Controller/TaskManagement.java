package Controller;

import Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  ласс предназначен дл€ управлени€ журналом задач
 */
public class TaskManagement {

    private static JournalModel journal;


    public static void setJournalModel(JournalModel jm) {
        journal = jm;
    }

    public static JournalModel getJournalModel() {
        return journal;
    }

    /**
     * ћетод создани€ новой задачи
     *
     * @param name - им€ задачи
     * @param date - дата оповещени€ задачи
     * @param text - текст задачи
     * @return результат создани€ и добавлени€ задачи в журнал.
     */
    public static boolean createTask(String name, String text, String date) {
        if (validationTask(date)) {
            TaskModel t = new TaskModel(name, text, date);
            journal.getJournalList().add(t);
            SystemNotification.addToListNotification(t);
            return true;
        }
        return false;
    }

    /**
     * ћетод проверки данных задачи, а именно даты. ƒата должна быть не раньше нынешнего момента.
     *
     * @param date - дата оповещени€ задачи
     * @return результат проверки.
     */
    private static boolean validationTask(String date) {
        Calendar dateNotification = Calendar.getInstance();
        SimpleDateFormat a = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date b;
        try {
            b = a.parse(date);
            dateNotification.setTime(b);
        } catch (ParseException e) {
            return false;
        }
        Calendar nowTIme = Calendar.getInstance();
        if (nowTIme.before(dateNotification)) {
            return true;
        }
        return false;
    }

    /**
     * ћетод удалени€ задачи
     *
     * @param i - id задачи в журнале
     */
    public static void deleteTask(int i) {
        SystemNotification.deleteNotification(journal.getJournalList().get(i));
        journal.getJournalList().remove(i);
    }

    static String getElement(int i) {
        return (journal.get(i)).toString();
    }


}

package Controller;

import Model.JournalModel;
import Model.NotificationModel;
import Model.TaskModel;
import View.TaskView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс предназначен для управления оповещениями
 */
public class SystemNotification {

    private static List<NotificationModel> listNotification;
    private static Map<NotificationModel, Timer> mapTimer = new HashMap<>();

    /**
     * Метод создания списка оповещения из сущетсвующего журнала задач, и списка таймеров с проверенным временем
     *
     * @param jm - журнал задач.
     */
    public static void convertToListNotification(JournalModel jm) {
        listNotification = new ArrayList<>();
        jm.getJournalList().forEach(p ->
                listNotification.add(new NotificationModel(p.getName(), p.getText(), p.getDateNotification()))
        );
        listNotification.forEach(p -> {
            if (validationNotificationDate(p.getDate())) {
                Timer t = new Timer();
                t.schedule(p.getT(), p.getDateFormat());
                mapTimer.put(p, t);
            }
        });
    }

    /**
     * Метод добавления оповещения в список
     *
     * @param tm - задача.
     */
    public static void addToListNotification(TaskModel tm) {
        NotificationModel ntm = new NotificationModel(tm.getName(), tm.getText(), tm.getDateNotification());
        listNotification.add(ntm);
        Timer t = new Timer();
        t.schedule(ntm.getT(), ntm.getDateFormat());
        mapTimer.put(ntm, t);
    }


    /**
     * Метод удаления оповещения.
     *
     * @param t - задача.
     */
    public static void deleteNotification(TaskModel t) {
        final NotificationModel[] deleted = {null};
        listNotification.forEach(p -> {
                    if (p.getDate().equals(t.getDateNotification()) && p.getName().equals(t.getName()) && p.getText().equals(t.getText())) {
                        deleted[0] = p;
                    }
                }
        );
        if (validationNotificationDate(t.getDateNotification())) {
            mapTimer.remove(deleted[0]);
        }
        listNotification.remove(deleted[0]);
    }


    /**
     * Метод проверки даты для создания оповещения. Если дата задачи устаревшая, то оповещение не создается
     *
     * @param date - дата оповещения задачи
     * @return результат проверки.
     */
    private static boolean validationNotificationDate(String date) {
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
     * Метод закрытия всех таймеров оповещения.
     */
    public static void closeAll() {
        mapTimer.values().forEach(p -> p.cancel());
    }


}

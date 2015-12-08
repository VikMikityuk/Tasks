package Controller;

import Model.*;


public class TaskManagement {

    private static JournalModel journal;

    public static void setJournalModel(JournalModel jm) {
        journal = jm;
    }

    public static JournalModel getJournalModel() {
        return journal;
    }


    public static boolean createTask(String name, String text, String date) {
        if (journal.getJournalList().add(new TaskModel(name, text, date))) {
            return true;
        }
        return false;
    }

    public static boolean deleteTask(int i) {
        journal.getJournalList().remove(i);//TODO добавить проверку существования удаляемого элемента
        return true;

    }

    static String getElement(int i) {
        return (journal.get(i)).toString();
    }


}

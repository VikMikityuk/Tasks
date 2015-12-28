package TaskFile;

import Controller.SystemNotification;
import Controller.TaskManagement;
import Model.JournalModel;
import View.TaskView;

import java.io.*;

/**
 * Класс предназначен для взаимодействием с файлом-хранилищем в системе.
 */


public class WorkWithFile {
    /**
     * @value taskFile поле- адрес файла для хранения типа JournalModel.
     */
    private static File taskFile;
    /**
     * @value jFile поле- адрес файла для хранения типа Json.
     */
    private static File jFile;


    public static void setTaskFileJM(File file) {
        if (existFile(file)) taskFile = file;
        else TaskView.printErrorIncorrectFile();
    }

    public static void setJsonFile(File file) {
        if (existFile(file)) jFile = file;
        else TaskView.printErrorIncorrectFile();
    }

    /**
     * Проверяет существование файла-хранилища. Если файл не существует, то создается новый пустой файл.
     *
     * @param taskFile -адрес файла в системе
     * @return результат проверки
     */
    public static boolean existFile(File taskFile) {
        taskFile.getParentFile().mkdirs();
        if (!(taskFile.exists())) try {
            taskFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Сериализация JournalModel в файл.
     *
     * @param j - журнал задач
     */
    public static void serJM(JournalModel j) throws IOException {
        FileOutputStream fos = new FileOutputStream(taskFile.getAbsoluteFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(j);
        oos.flush();
        oos.close();

    }


    /**
     * Десериализация JournalModel из файла. Предназначен для метода валидной десереиализвации.
     *
     * @return j - журнал задач.
     */
    private static JournalModel deSerJM() throws IOException {
        FileInputStream fis = new FileInputStream(taskFile.getAbsoluteFile());
        ObjectInputStream oin = new ObjectInputStream(fis);
        JournalModel j = null;
        try {
            j = (JournalModel) oin.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return j;
    }

    /**
     * Сериализация Json в файл.
     *
     * @param j- строковое поле, представление журнала задач в виде объекта Json
     */
    public static void serJson(String j) throws IOException {
        FileOutputStream fos = new FileOutputStream(jFile.getAbsoluteFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        if(existFile(jFile)) oos.writeObject(j); //нужна ли проверка существования файла?
        oos.flush();
        oos.close();

    }

    /**
     * Десериализация Json из файла. Предназначен для метода валидной десереиализвации.
     *
     * @return j - журнал задач в виде объекта Json.
     */
    private static String deSerJson() throws IOException {
        FileInputStream fis = new FileInputStream(jFile.getAbsoluteFile());
        ObjectInputStream oin = new ObjectInputStream(fis);
        String j = null;
        try {
            j = (String) oin.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return j;
    }

    /**
     * Десериализация JournalModel из файла c проверками наличия JournalModel в файле.
     */
    public static void validDeserJM() throws IOException {    //  For deserializable arraylist
        JournalModel j;
        String json;
        if (taskFile.length() == 0) {
            j = new JournalModel();
        } else {
            j = deSerJM();
        }
    }


    /**
     * Десериализация Json из файла c проверками наличия Json в файле.
     */
    public static void validDeSerJson() throws IOException {     //For deserializable Json
        JournalModel j;
        String json;
        if (jFile.length() == 0) {
            j = new JournalModel();
        } else {
            json = deSerJson();
            j = TaskToJson.fromJson(json);
        }
        TaskManagement.setJournalModel(j);
        SystemNotification.convertToListNotification(j);
    }


}

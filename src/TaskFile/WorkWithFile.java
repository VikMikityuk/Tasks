package TaskFile;

import Controller.SystemNotification;
import Controller.TaskManagement;
import Model.JournalModel;

import java.io.*;

public class WorkWithFile {

    private static File taskFile;
    private static File jFile;


    public static void setTaskFileJM(File file) {
        if (existFile(file)) taskFile = file;
    }

    public static void setJsonFile(File file) {
        if (existFile(file)) jFile = file;
    }

    public static boolean existFile(File taskFile) {
        taskFile.getParentFile().mkdirs();
        if (!(taskFile.exists())) try {
            taskFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }


    public static void serJM(JournalModel j) throws IOException {
        FileOutputStream fos = new FileOutputStream(taskFile.getAbsoluteFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(j);
        oos.flush();
        oos.close();

    }

    public static JournalModel deSerJM() throws IOException {
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


    public static void serJson(String j) throws IOException {
        FileOutputStream fos = new FileOutputStream(jFile.getAbsoluteFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(j);
        oos.flush();
        oos.close();

    }


    public static String deSerJson() throws IOException {
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


    public static void validDeserJM() throws IOException {    //  For deserializable arraylist
        JournalModel j;
        String json;
        if (taskFile.length() == 0) {
            j = new JournalModel();
        } else {
            j = deSerJM();
        }
    }

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

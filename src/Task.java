import Controller.TaskController;
import Controller.TaskManagement;
import Controller.TaskToJson;
import Controller.WorkWithFile;
import Model.JournalModel;
import Model.TaskModel;
import View.TaskView;
import com.google.gson.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Task {
    public static void main(String[] args) throws IOException {

        File taskFile = new File("C://task//task.out");
        File taskFileJson = new File("C://task//taskJson.out");
       // WorkWithFile.setTaskFileJM(taskFile);
        WorkWithFile.setJsonFile(taskFileJson);

        JournalModel j;
        List<TaskModel> journal;
        String json;


        //For deserializable arraylist
        // if (taskFile.length() == 0) {
        //      j = new JournalModel();
        //      journal = new ArrayList<>();
        //   } else {
        //       j = WorkWithFile.deSerJM();
        //       journal = j.getJournalList();
        //   }
        //   j.setJournal(journal);

        // j.add(new TaskModel("name", "text", "22.06.2007 12:12:12"));

        //For deserializable Json
        if (taskFileJson.length() == 0) {
           j = new JournalModel();
          journal = new ArrayList<>();
         j.setJournal(journal);
       } else {
            json = WorkWithFile.deSerJson();
            j = TaskToJson.fromJson(json);
            journal = j.getJournalList();
        }


        TaskManagement.setJournalModel(j);

        TaskView.hello();
        TaskView.printFirstmenu();
        TaskController.replyFirstMenu();
        //Date nowTIme=new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();


    }
}

import Controller.TaskController;
import Controller.TaskManagement;
import Model.JournalModel;
import Model.TaskModel;
import View.TaskView;

import java.util.ArrayList;
import java.util.List;


public class Task {
    public static void main(String[] args) {

        JournalModel j = new JournalModel();
        List<TaskModel> journal = new ArrayList<>();

        j.setJournal(journal);

        j.add(new TaskModel("name", "text", "22.06.2007 12:12:12"));


        TaskManagement.setJournalModel(j);

        TaskView.hello();
        TaskView.printFirstmenu();
        TaskController.replyFirstMenu();


    }


}

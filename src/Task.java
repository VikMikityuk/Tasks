import Controller.*;
import TaskFile.WorkWithFile;
import View.TaskView;


import java.io.*;


public class Task {
    public static void main(String[] args) throws IOException {

        // File taskFile = new File("C://task//task.out");
        //WorkWithFile.setTaskFileJM(taskFile);
        File taskFileJson = new File("C://task//taskJson.out");
        WorkWithFile.setJsonFile(taskFileJson);


        WorkWithFile.validDeSerJson();
        TaskView.hello();
        TaskView.printFirstmenu();
        TaskController.replyFirstMenu();


    }
}

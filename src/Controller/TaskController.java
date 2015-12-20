package Controller;


import Model.TaskModel;
import View.TaskView;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;


public class TaskController {

    private static List<TaskModel> list = TaskManagement.getJournalModel().getJournalList();
    private static String nameTask;
    private static String textTask;
    private static String dayTask;
    private static int a;
    private static Scanner in = new Scanner(System.in);


    public static void replyFirstMenu() throws IOException {
        a = in.nextInt();
        switch (a) {
            case 1:
                createTask();
                break;
            case 2:
                printjournal();
                break;
            case 3:
                deleteTask();
                break;
            case 4:
                exit();
                break;
            default:
                TaskView.printError();
        }
    }


    private static void createTask() throws IOException {
        Scanner in = new Scanner(System.in);
        TaskView.printForCreateTaskName();
        String name = in.nextLine();
        nameTask = name;
        TaskView.printForCreateTaskText();
        String text = in.nextLine();
        textTask = text;
        TaskView.printForCreateTaskDate();
        String date = in.nextLine();
        dayTask = date;

        if (TaskManagement.createTask(nameTask, textTask, dayTask)) {
            TaskView.printAddTask(true);
        } else TaskView.printError();
        //TODO добавить проверку вводимых значений.
        exitToMainMenu();
    }


    private static void printjournal() throws IOException {

        if (list.isEmpty()) {
            TaskView.printEmptyJournal();
        } else
            TaskView.printJournal(list);

        exitToMainMenu();
    }


    private static void deleteTask() throws IOException {
        if (list.size() == 0) {
            TaskView.printDeleteEmptyList();
            exitToMainMenu();
        } else {
            TaskView.printDeleteTask(list);
            int id = in.nextInt();
            TaskManagement.deleteTask(id);
            TaskView.printComplete();//TODO добавить проверку ид
            exitToMainMenu();
        }
    }


    private static void exit() throws IOException {
        WorkWithFile.serJson(TaskToJson.toJson(TaskManagement.getJournalModel()));//TODO dateFormat
        //WorkWithFile.serJM(TaskManagement.getJournalModel());
        System.out.println(SystemNotification.map);//TODO DELITE THIS STR!!!!
    }

    private static void exitToMainMenu() throws IOException {
        TaskView.printExitToFirstMenu();
        int ex = in.nextInt();
        if (ex == 0 || ex != 0) {
            TaskView.printFirstmenu();
            replyFirstMenu();
        }
    }

}

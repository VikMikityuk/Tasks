package Controller;


import Model.TaskModel;
import TaskFile.TaskToJson;
import TaskFile.WorkWithFile;
import View.TaskView;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class TaskController {

    private static List<TaskModel> list = TaskManagement.getJournalModel().getJournalList();
    private static String nameTask;
    private static String textTask;
    private static String dayTask;
    private static int a;


    public static void replyFirstMenu() {
        try {
            Scanner in = new Scanner(System.in);
            a = in.nextInt();
        } catch (Exception e) {
            a = 0;
        }
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
                TaskView.printErrorIncorrectValue();
                replyFirstMenu();
        }
    }


    private static void createTask() {
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
        exitToMainMenu();
    }


    private static void printjournal() {
        if (list.isEmpty()) {
            TaskView.printEmptyJournal();
        } else TaskView.printJournal(list);

        exitToMainMenu();
    }


    private static void deleteTask() {
        if (list.size() == 0) {
            TaskView.printDeleteEmptyList();
            exitToMainMenu();
        } else {
            TaskView.printDeleteTask(list);
            Scanner in = new Scanner(System.in);
            try {
                int id = in.nextInt();
                if (id == 666) {
                    TaskView.printFirstmenu();
                    replyFirstMenu();
                    return;
                }
                TaskManagement.deleteTask(id);
                TaskView.printComplete();
                exitToMainMenu();
            } catch (IndexOutOfBoundsException e) {
                TaskView.printErrorIncorrectValue();
                deleteTask();
            } catch (Exception e) {
                TaskView.printErrorIncorrectValue();
                deleteTask();
            }
        }
    }


    private static void exit() {
        try {
            WorkWithFile.serJson(TaskToJson.toJson(TaskManagement.getJournalModel()));
            //WorkWithFile.serJM(TaskManagement.getJournalModel());
            SystemNotification.closeAll();
        } catch (IOException e) {
            TaskView.printError();
        }
    }

    private static void exitToMainMenu() {
        TaskView.printExitToFirstMenu();
        Scanner in = new Scanner(System.in);
        Object ex = in.nextLine();
        if (ex == null || ex != null) {
            TaskView.printFirstmenu();
            replyFirstMenu();
        }
    }

}

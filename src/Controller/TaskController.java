package Controller;


import Model.TaskModel;
import View.TaskView;


import java.util.List;
import java.util.Scanner;


public class TaskController {

    private static List<TaskModel> list = TaskManagement.getJournalModel().getJournalList();
    private static String nameTask;
    private static String textTask;
    private static String dayTask;
    private static int a;
    private static Scanner in = new Scanner(System.in);


    public static void replyFirstMenu() {
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

                break;
            default:
                TaskView.printError();
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
        }
        //TODO �������� �������� �������� ��������.
        exitToMainMenu();
    }


    private static void printjournal() {

        if (list.isEmpty()) {
            TaskView.printEmptyJournal();
        } else
            TaskView.printJournal(list);

        exitToMainMenu();
    }


    private static void deleteTask() {
        TaskView.printDeleteTask(list);
        int id = in.nextInt();
        TaskManagement.deleteTask(id);
        TaskView.printComplete();//TODO �������� �������� ��
        exitToMainMenu();
    }


    private static void exitToMainMenu() {
        TaskView.printExitToFirstMenu();
        int ex = in.nextInt();
        if ( ex == 0) {
            TaskView.printFirstmenu();
            replyFirstMenu();
        }
    }

}

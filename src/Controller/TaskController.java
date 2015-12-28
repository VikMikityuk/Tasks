package Controller;


import Model.TaskModel;
import TaskFile.TaskToJson;
import TaskFile.WorkWithFile;
import View.TaskView;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс предназначен для обработки действий пользователя и в зависимости от результатов работой с системой.
 */

public class TaskController {

    private static List<TaskModel> list = TaskManagement.getJournalModel().getJournalList();
    private static String nameTask;
    private static String textTask;
    private static String dayTask;
    private static int a;

    /**
     * Метод для обработки вводимого пользователем значение, и вызова последующийх методов для управления журналом
     */
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

    /**
     * Метод для создания новой задачи
     */
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

    /**
     * Метод для вывода журнала задач пользователю
     */
    private static void printjournal() {
        if (list.isEmpty()) {
            TaskView.printEmptyJournal();
        } else TaskView.printJournal(list);

        exitToMainMenu();
    }

    /**
     * Метод для удаления задачи из журнала
     */
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

    /**
     * Метод для выхода из программы.
     */
    private static void exit() {
        try {
            WorkWithFile.serJson(TaskToJson.toJson(TaskManagement.getJournalModel()));
            //WorkWithFile.serJM(TaskManagement.getJournalModel());
            SystemNotification.closeAll();
        } catch (IOException e) {
            TaskView.printError();
        }
    }

    /**
     * Метод для возврата в главное меню.
     */
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

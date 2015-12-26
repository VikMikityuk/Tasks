package View;

import Model.TaskModel;

import java.util.List;

public class TaskView {

    public static void hello() {
        System.out.println("Hello!");
        System.out.println("You are in Task Manager.");
        System.out.println("Manage tasks by selecting the desired item .");
    }

    public static void printFirstmenu() {
        System.out.println("1.Create Task;");
        System.out.println("2.Read Journal of Task;");
        System.out.println("3.Delete Task");
        System.out.println("4.Exit.");
    }


    public static void printForCreateTaskName() {
        System.out.println("Input name of Task:");
    }

    public static void printForCreateTaskText() {
        System.out.println("Input text of task:");
    }

    public static void printForCreateTaskDate() {
        System.out.println("Input date of task in format \"dd.MM.yyyy HH:mm:ss\":");
    }

    public static void printAddTask(boolean f) {
        if (true) System.out.println("You Add new task!");
        else System.out.println("You don't add task, try again.");
    }


    public static void printEmptyJournal() {
        System.out.println("Journal of tasks is empty!!");
    }

    public static void printJournal(List<TaskModel> list) {
        list.forEach(p -> {
            System.out.println(p);
        });
    }


    public static void printDeleteTask(List<TaskModel> list) {
        System.out.println("Enter the number of tasks that must be removed (or enter \"666\" for return to main menu)");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("no." + i + "; name of task: " + list.get(i).getName());
        }
    }

    public static void printDeleteEmptyList() {
        System.out.println("Journal is EMPTY.");
    }


    public static void printComplete() {
        System.out.println("Complete!");
    }


    public static void printError() {
        System.out.println("ERROR! Something went wrong, try again.");
    }

    public static void printErrorIncorrectValue() {
        System.out.println("ERROR! Incorrect value, enter again");
    }


    public static void printExitToFirstMenu() {
        System.out.println("Enter 0 to exit to the main menu.");
    }
}

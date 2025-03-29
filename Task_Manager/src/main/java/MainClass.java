import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }
        catch (Exception e){
            System.err.println("Failed To Initialize Lead for UI");
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception fallBackError){
                System.err.println("System UI Failed Too, Reverting to Default UI");
            }
        }

        SwingUtilities.invokeLater(() -> {
            TaskManager manager = new TaskManager();
            TaskManagerUI ui = new TaskManagerUI(manager);
            ui.setVisible(true);
                });
        /*
        Scanner input = new Scanner(System.in);
        TaskManager  manager = new TaskManager();

        while(true){
            System.out.println("Welcome to the Task Management System");
            System.out.println("1 - Add Task");
            System.out.println("2 - View Tasks");
            System.out.println("3 - Remove Task");
            System.out.println("4 - Mark as Complete");
            System.out.println("5 - Exit the System");
            System.out.println("Please Choose one of the following options: ");
            int choice = input.nextInt();
            input.nextLine();
            System.out.println();

            switch(choice){ // Fix for 1 based indexing, add empty checks for view delete and complete
                case 1 :
                    System.out.print("Title: ");
                    String title = input.nextLine();
                    System.out.println();
                    System.out.print("Description: ");
                    String description = input.nextLine();
                    System.out.println();
                    System.out.print("Due Date: ");
                    String date = input.nextLine();
                    System.out.println();
                    manager.addTask(new Task(title,description,date,false));
                    break;
                case 2:
                    manager.listTasks();
                    break;
                case 3:
                    System.out.println("Enter the task number to delete: ");
                    int index = input.nextInt();
                    manager.deleteTask(index);
                    break;
                case 4:
                    System.out.println("Enter the task number to Complete: ");
                    index = input.nextInt();
                    manager.markTastComplete(index);
                    break;
                case 5:
                    System.out.println("Thank You For Using the System");
                    System.exit(0);
                default:
                    System.out.println("Please Enter a Valid Option");
            }
        }

         */
    }
}

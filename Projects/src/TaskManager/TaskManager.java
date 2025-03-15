package TaskManager;

import java.io.*;
import java.util.*;

class Task{
	String title;
	String description;
	boolean isCompleted;
	
	public Task(String title, String description) {
		this.title = title;
		this.description = description;
		this.isCompleted = false;
	}
	public void markCompleted(){
		isCompleted = true;	
	}
	
	@Override
	public String toString() {
		return (isCompleted ? "[✔]":"[]") + title + ":"+description;
	}
	
}

public class TaskManager {
	static List<Task> tasks = new ArrayList<>();
	static final String file_name = "tasks.txt";
	
	public static void main(String[] args) {
		loadTasks();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
            System.out.println("\nTASK MANAGER ");
            System.out.println("1 Add Task");
            System.out.println("2️ View Tasks");
            System.out.println("3️ Mark Task as Completed");
            System.out.println("4️ Delete Task");
            System.out.println("5️ Exit");
            System.out.print("Choose an option: ");
            
            int choice = scan.nextInt();
            scan.nextLine();
            
            switch(choice) {
            	case 1:
            		addTask(scan);
            		break;
            	case 2:
            		viewTasks();
            		break;
            	case 3:
            		markTaskCompleted(scan);
            		break;
            	case 4:
            		deleteTask(scan);
            	case 5:
            		saveTasks();
            		System.out.println("Exiting Task Manager...");
            		scan.close();
            		return;
            	default:
            		System.out.println("Invaild choice.Try again!");
            }
		}
	}
	
	static void addTask(Scanner scan) {
		System.out.println("Enter task title:");
		String title = scan.nextLine();
		System.out.println("Enter task description:");
		String description = scan.nextLine();
		tasks.add(new Task(title, description));
		System.out.println("Task added");
	}
	
    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\nTASK LIST:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    static void markTaskCompleted(Scanner scan) {
        viewTasks();
        System.out.print("Enter task number to mark as completed: ");
        int index = scan.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    
    static void deleteTask(Scanner scan) {
        viewTasks();
        System.out.print("Enter task number to delete: ");
        int index = scan.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    
    static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file_name))) {
            for (Task task : tasks) {
                writer.println(task.title + "|" + task.description + "|" + task.isCompleted);
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    static void loadTasks() {
        try (Scanner fileScanner = new Scanner(new File(file_name))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split("\\|");
                Task task = new Task(data[0], data[1]);
                if (Boolean.parseBoolean(data[2])) task.markCompleted();
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing tasks found. Starting fresh.");
        }
    }
	
}

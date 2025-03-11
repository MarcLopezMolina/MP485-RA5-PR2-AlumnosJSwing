package main;


import java.io.IOException;
import libraries.ScannerLib;

public class StudentRegister
{
    public static void main(String[] args) throws IOException
    {
        StudentManager.initFiles();
        
        int option = 0;
        boolean exit = false;
        
        do
        {
            System.out.println("\n===========================");
            System.out.println("      Student Register");
            System.out.println("===========================");
            System.out.println("1) New student");
            System.out.println("2) Show students");
            System.out.println("3) Delete student");
            System.out.println("4) Search student by dni");
            System.out.println("5) Exit");
            System.out.print("Select an option: ");
            option = ScannerLib.scannerInt();

            switch (option)
            {
                case 1 -> StudentManager.newStudent();
                case 2 -> StudentManager.showStudents();
                case 3 -> StudentManager.deleteStudent();
                case 4 -> StudentManager.searchStudent();
                case 5 -> exit = true;
                default -> System.err.println("Option not available");
            }
        }
        while (exit == false);
        System.out.println("Exiting the program...");
    }
}

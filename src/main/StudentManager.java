package main;

import classes.Student;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import libraries.ScannerLib;

public class StudentManager
{
    private static String projectRoute = System.getProperty("user.dir");
    private static String separator = File.separator;
    private static String br = System.getProperty("line.separator");
    private static String dirRoute = projectRoute + separator + "files";

    private static File dir = new File(dirRoute); // Carpeta de archivos
    private static File register = new File(dirRoute + separator + "register.txt"); // Archivo register.txt

    public static ArrayList<Student> studentsList = new ArrayList<>();

    //Checks if exists the files folder and register.txt file.
    public static void initFiles()
    {
        if (!dir.exists())
        {
            dir.mkdir();
        }

        if (!register.exists())
        {
            try
            {
                register.createNewFile();
            }
            catch (Exception e)
            {
                System.err.println("The register.txt file cannot be created.");
            }
        }
    }

    //Reads register.txt and saves line by line into an ArrayList that will be returned.
    public static ArrayList<String> readRegisterFile() throws IOException
    {
        ArrayList<String> previousDataRegister = new ArrayList<>();
        BufferedReader bufR = new BufferedReader(new FileReader(register));

        String line;
        while ((line = bufR.readLine()) != null)
        {
            previousDataRegister.add(line);
        }

        bufR.close();
        return previousDataRegister;
    }

    //Get the previous ArrayList with all the lines of previous data and splits into parts to be converted into a Student object.
    public static void getStudents() throws IOException
    {
        studentsList.clear();
        ArrayList<String> studentsData = readRegisterFile();

        for (String data : studentsData)
        {
            String[] parts = data.split(",");
            if (parts.length == 5) //Just checking if there are the correct amount of parts.
            {
                studentsList.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]));
            }
        }
    }

    //Asks the user diferent parameters and creates a Student object. Then writes the new line to the register.
    public static void newStudent() throws IOException
    {
        BufferedWriter bufW = new BufferedWriter(new FileWriter(register, true)); 

        System.out.println("\nIntroduce the student data: ");
        System.out.print("Name: ");
        String name = ScannerLib.scannerString();
        System.out.print("Surname: ");
        String surname = ScannerLib.scannerString();
        System.out.print("Age: ");
        int age = ScannerLib.scannerInt();
        System.out.print("Course: ");
        String course = ScannerLib.scannerString();
        System.out.print("DNI: ");
        String dni = ScannerLib.scannerString();

        Student newStudent = new Student(name, surname, age, course, dni);
        studentsList.add(newStudent);

        bufW.write(newStudent.toString() + System.lineSeparator());
        bufW.flush(); //idk what this does but it appears in the theory pdf.
        bufW.close();

        System.out.println("\nStudent added successfully!");
    }

    //Gets the information of register.txt and returns all students data if they exist.
    public static void showStudents() throws IOException
    {
        getStudents();

        if (studentsList.isEmpty())
        {
            System.out.println("\nThere are no students registered yet!");
        }
        else
        {
            for (int i = 0; i < studentsList.size(); i++)
            {
                System.out.println("\n[Student " + (i + 1) + "]");
                System.out.println("Name: " + studentsList.get(i).getName());
                System.out.println("Surname: " + studentsList.get(i).getSurname());
                System.out.println("Age: " + studentsList.get(i).getAge());
                System.out.println("Course: " + studentsList.get(i).getCourse());
                System.out.println("DNI: " + studentsList.get(i).getDni());
            }
        }
    }
    
    //Shows all students and search if there are a student with the DNI provided by the user. If exists, gets deleted.
    public static void deleteStudent() throws IOException
    {
        System.out.println("\nThis is the list of all students");

        showStudents();

        System.out.print("\nType the DNI of the student that you want to delete: ");
        String dni = ScannerLib.scannerString();
        boolean found = false;

        ArrayList<Student> updatedStudentsList = new ArrayList<>();

        for (Student student : studentsList)
        {
            if (!student.getDni().equalsIgnoreCase(dni))
            {
                updatedStudentsList.add(student);
            }
            else
            {
                found = true;
            }
        }

        if (found = true)
        {
            BufferedWriter bufW = new BufferedWriter(new FileWriter(register, false));

            for (Student student : updatedStudentsList)
            {
                bufW.write(student.toString() + System.lineSeparator());
            }

            bufW.flush();
            bufW.close();

            studentsList = updatedStudentsList; // Actualizar la lista en memoria

            System.out.println("The student was successfully deleted.");
        }
        else
        {
            System.out.println("There are no students with that DNI.");
        }
    }

    //Asks the user to type a DNI and shows all the student data.
    public static void searchStudent() throws IOException
    {
        System.out.print("\nType the DNI of the student you want to search: ");
        String dni = ScannerLib.scannerString();
        boolean found = false;

        BufferedReader bufR = new BufferedReader(new FileReader(register));
        String line;

        while ((line = bufR.readLine()) != null)
        {
            String[] parts = line.split(",");
            if (parts.length == 5 && parts[4].equalsIgnoreCase(dni))
            {
                System.out.println("\nStudent found:");
                System.out.println("Name: " + parts[0]);
                System.out.println("Surname: " + parts[1]);
                System.out.println("Age: " + parts[2]);
                System.out.println("Course: " + parts[3]);
                System.out.println("DNI: " + parts[4]);
                found = true;
                break;
            }
        }

        bufR.close();

        if (found = false)
        {
            System.out.println("\nNo student found with that DNI.");
        }
    }
}  
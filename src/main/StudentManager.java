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
    public static void newStudent(String name, String surname,int age, String course, String dni) throws IOException
    {
        BufferedWriter bufW = new BufferedWriter(new FileWriter(register, true)); 

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
    
    //Searchs if there are a student with the DNI provided by the user, if exists, gets deleted.
    public static void deleteStudent(String dni) throws IOException
    {
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
            
            studentsList = updatedStudentsList;

            System.out.println("The student was successfully deleted.");
        }
        else
        {
            System.out.println("There are no students with that DNI.");
        }
    }

    //Asks the user to type a DNI and shows all the student data.
    public static String searchStudent(String dni) throws IOException
    {
        BufferedReader bufR = new BufferedReader(new FileReader(register));
        String line;
        String studentData = "No student found.";

        while ((line = bufR.readLine()) != null)
        {
            String[] parts = line.split(",");
            if (parts.length == 5 && parts[4].equalsIgnoreCase(dni))
            {
                studentData = "Name: " + parts[0] + "\nSurname: " + parts[1]
                        + "\nAge: " + parts[2] + "\nCourse: " + parts[3]
                        + "\nDNI: " + parts[4];
                break;
            }
        }

        bufR.close();
        return studentData;
    }

}

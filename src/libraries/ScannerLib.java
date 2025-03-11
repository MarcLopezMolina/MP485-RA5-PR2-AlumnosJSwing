package libraries;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerLib
{
    /*
        Library to use the Scanner. Created for preventing saturating the buffer.
        © Marc López
    */   
    
    public static int scannerInt()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            try
            {
                return sc.nextInt();
            }
            catch (InputMismatchException error)
            {
                System.err.println("You can only type numbers.");
                sc.nextLine();
            }
        }
    }
    
    public static String scannerString()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            try
            {
                return sc.nextLine();
            }
            catch (InputMismatchException error)
            {
                System.err.println("You can only type letters and numbers.");
                sc.nextInt();
            }
        }
    }
    
    public static double scannerDouble()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            try
            {
                return sc.nextDouble();
            }
            catch (InputMismatchException error)
            {
                System.err.println("You can only type integers and decimal numbers.");
                sc.nextInt();
            }
        }
    }
    
    public static boolean scannerBoolean()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            try
            {
                return sc.nextBoolean();
            }
            catch (InputMismatchException error)
            {
                System.err.println("You can only type 'true' or 'false'.");
                sc.nextInt();
            }
        }
    }
}

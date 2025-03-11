package classes;

public class Student
{
    private String name;
    private String surname;
    private int age;
    private String course;
    private String dni;

    public Student(String name, String surname, int age, String course, String dni)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.course = course;
        this.dni = dni;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getCourse()
    {
        return course;
    }

    public void setCourse(String course)
    {
        this.course = course;
    }

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    @Override
    public String toString()
    {
        return name+","+surname+","+age+","+course+","+dni;
    }
}

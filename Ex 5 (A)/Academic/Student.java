package Academic;

public abstract class Student {
    protected String name;
    protected int age;
    protected String studentId;

    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    public abstract String getDegreeType();
    
    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", Student ID: " + studentId;
    }
}

import Academic.*;
public class Main {
    public static void main(String[] args) {
        Undergraduate ugStudent = new Undergraduate("Sooriya", 18, "UG123", "Information Technology");
        Postgraduate pgStudent = new Postgraduate("Sooriya", 22, "PG456", "Computer Science");

        System.out.println(ugStudent.getDetails() + ", Degree Type: " + ugStudent.getDegreeType());
        ugStudent.attendWorkshop("AI Workshop");
        ugStudent.participateInEvent("Tech Fest");

        System.out.println(pgStudent.getDetails() + ", Degree Type: " + pgStudent.getDegreeType());
        pgStudent.attendWorkshop("Big Data Seminar");
        pgStudent.participateInEvent("Research Conference");
    }
}

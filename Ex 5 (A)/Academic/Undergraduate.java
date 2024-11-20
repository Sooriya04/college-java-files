package Academic;

public class Undergraduate extends Student implements Participation {
    private String major;

    public Undergraduate(String name, int age, String studentId, String major) {
        super(name, age, studentId);
        this.major = major;
    }

    @Override
    public String getDegreeType() {
        return "Undergraduate";
    }

    @Override
    public void attendWorkshop(String workshopName) {
        System.out.println(name + " is attending the workshop: " + workshopName);
    }

    @Override
    public void participateInEvent(String eventName) {
        System.out.println(name + " is participating in the event: " + eventName);
    }

    public String getMajor() {
        return major;
    }
}

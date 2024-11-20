package Academic;

public class Postgraduate extends Student implements Participation {
    private String specialization;

    public Postgraduate(String name, int age, String studentId, String specialization) {
        super(name, age, studentId);
        this.specialization = specialization;
    }

    @Override
    public String getDegreeType() {
        return "Postgraduate";
    }

    @Override
    public void attendWorkshop(String workshopName) {
        System.out.println(name + " is attending the workshop: " + workshopName);
    }

    @Override
    public void participateInEvent(String eventName) {
        System.out.println(name + " is participating in the event: " + eventName);
    }

    public String getSpecialization() {
        return specialization;
    }
}

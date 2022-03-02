package br.edu.ifsp.domain.entities.user;

public class Student extends User {
    private String course;

    public Student() {
    }

    public Student(String institutionallId, String name, String email, String phone, String course) {
        this(institutionallId, name, email, phone, 0, course);
    }

    public Student(String institutionallId, String name, String email, String phone, Integer numberOfBooksCheckedOut, String course) {
        super(institutionallId, name, email, phone, numberOfBooksCheckedOut);
        this.course = course;
    }

    @Override
    public String getUserType() {
        return "Estudante";
    }

    @Override
    protected int getLimitOfBooksToCheckout() {
        return 3;
    }

    @Override
    public int getCheckOutTimeLimitInDays() {
        return 7;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "course='" + course + '\'' +
                "} " + super.toString();
    }
}

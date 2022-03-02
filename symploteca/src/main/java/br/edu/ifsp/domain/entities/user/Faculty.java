package br.edu.ifsp.domain.entities.user;

public class Faculty extends User {
    private String department;

    public Faculty() {
    }

    public Faculty(String institutionallId, String name, String email, String phone, String department) {
        this(institutionallId, name, email, phone, 0, department);
    }

    public Faculty(String institutionallId, String name, String email, String phone, Integer numberOfBooksCheckedOut, String department) {
        super(institutionallId, name, email, phone, numberOfBooksCheckedOut);
        this.department = department;
    }

    @Override
    public String getUserType() {
        return "Funcionário";
    }

    @Override
    protected int getLimitOfBooksToCheckout() {
        return 5;
    }

    @Override
    public int getCheckOutTimeLimitInDays() {
        return 30;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "department='" + department + '\'' +
                "} " + super.toString();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

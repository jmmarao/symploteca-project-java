package br.edu.ifsp.domain.entities.user;

public abstract class User {
    private String institutionallId;
    private String name;
    private String email;
    private String phone;
    private Integer numberOfBooksCheckedOut;

    public abstract String getUserType();
    protected abstract int getLimitOfBooksToCheckout();
    public abstract int getCheckOutTimeLimitInDays();

    public User() {
        numberOfBooksCheckedOut = 0;
    }

    public User(String name, String email, String phone) {
        this(null, name, email, phone, 0);
    }

    public User(String institutionallId, String name, String email, String phone, Integer numberOfBooksCheckedOut) {
        this.institutionallId = institutionallId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.numberOfBooksCheckedOut = numberOfBooksCheckedOut;
    }

    public void increaseNumberOfBooksCheckedOut(){
        if (!isAbleToCheckOut()){
            throw new IllegalNumberOfCheckOutItensException("User " + name + " exceeded the limit of books checked out" + getLimitOfBooksToCheckout());
        }
        numberOfBooksCheckedOut++;
    }

    public void decreaseNumberOfBooksCheckedOut(){
        if (numberOfBooksCheckedOut == 0){
            throw new IllegalNumberOfCheckOutItensException("The number of books checked out cannot be negative.");
        }
        numberOfBooksCheckedOut--;
    }

    public boolean isAbleToCheckOut(){
        return numberOfBooksCheckedOut <getLimitOfBooksToCheckout();
    }

    public String getInstitutionallId() {
        return institutionallId;
    }

    public void setInstitutionallId(String institutionallId) {
        this.institutionallId = institutionallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNumberOfBooksCheckedOut() {
        return numberOfBooksCheckedOut;
    }

    @Override
    public String toString() {
        return "User{" +
                "institutionallId='" + institutionallId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfBooksCheckedOut=" + numberOfBooksCheckedOut +
                '}';
    }
}

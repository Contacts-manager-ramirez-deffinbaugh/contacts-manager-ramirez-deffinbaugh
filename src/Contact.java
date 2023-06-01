public class Contact {
    private String name;
    private long phoneNumber;

    //constructor
    public Contact(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void displayContacts() {
        //TODO format this
        System.out.println(name + " | " + phoneNumber);
    }

    public String toCSV() {
        //TODO does this need a new line?
        return name + "," + phoneNumber;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

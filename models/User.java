package models;

public class User {

    public int Id;
    public String Name;
    public String Email;

    public User() {
    }

    public User(String Name, String Email) {
        this.Name = Name;
        this.Email = Email;
    }

    public User(int id, String Name, String Email) {
        this.Id = id;
        this.Name = Name;
        this.Email = Email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}

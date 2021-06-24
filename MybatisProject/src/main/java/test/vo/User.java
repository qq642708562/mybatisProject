package test.vo;

public class User {
    private String name;
    private String password;
    private String role;
    private String lastName;

    public User() {
    }

    public User(String name, String password, String role, String lastName) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole(){
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

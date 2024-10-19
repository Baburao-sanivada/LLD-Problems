package Medium.CarRentalSystem;

public class User {
    private String userId;
    private String name;
    private String email;
    private String contact;
    private String drivingLicense;

    public User(String userId, String name, String email, String contact, String drivingLicense) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.drivingLicense = drivingLicense;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

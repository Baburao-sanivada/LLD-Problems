package Medium.HotelManagementSystem;

public class Guest {
    private String name;
    private String contactNo;
    private String id;

    public Guest(String id,String name, String contactNo) {
        this.name = name;
        this.contactNo = contactNo;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

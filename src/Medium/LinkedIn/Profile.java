package Medium.LinkedIn;

import java.util.UUID;

public class Profile {
    private final String profileId;
    private String name;
    private String education;
    private String college;
    private String experience;

    public Profile(String name){
        this.profileId = name+ UUID.randomUUID();
        this.name = name;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}

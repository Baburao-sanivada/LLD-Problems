package Medium.LinkedIn;

import java.time.LocalDateTime;
import java.util.UUID;

public class Job {
    private final String jobId;
    private String jobTitle;
    private String description;
    private String requirement;
    private String location;
    private final LocalDateTime time;

    public Job(String jobTitle, String description, String requirement,String location) {
        jobId =""+UUID.randomUUID();
        this.jobTitle = jobTitle;
        this.description = description;
        this.requirement = requirement;
        this.time = LocalDateTime.now();
        this.location = location;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public LocalDateTime getTime() {
        return time;
    }
}

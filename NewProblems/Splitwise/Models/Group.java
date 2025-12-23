package NewProblems.Splitwise.Models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    private String description;
    private final List<String> groupMembers;

    public Group(String groupId, String groupName, String description) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.groupMembers = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMember(String userId) {
        groupMembers.add(userId);
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public void removeMember(String userId) {
        groupMembers.remove(userId);
    }
}

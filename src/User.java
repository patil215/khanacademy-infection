import java.util.HashSet;

public class User implements Comparable {
    private int id;
    private HashSet<Integer> students; // List of students
    private int coach; // Coach for this user, users can only have one coach
    private int version; // Current version of site user has
    private int peopleBelow; // Number of people that below this user in the tree

    public User(int id) {
        this.id = id;
        students = new HashSet<Integer>();
        coach = -1; // No coach to begin
        version = 0;
        peopleBelow = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashSet<Integer> getStudents() {
        return students;
    }

    public void setStudents(HashSet<Integer> students) {
        this.students = students;
    }

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getPeopleBelow() {
        return peopleBelow;
    }

    public void setPeopleBelow(int peopleBelow) {
        this.peopleBelow = peopleBelow;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        if (peopleBelow < user.peopleBelow) {
            return 1;
        } else if (peopleBelow > user.peopleBelow) {
            return -1;
        }
        return 0;
    }
}

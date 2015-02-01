import java.util.HashSet;

public class User {
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
}

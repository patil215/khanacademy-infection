import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class InfectionSimulation {

    private int numUsers; // Number of users to run in the simulation
    private int numRelationships; // Number of coach/student relationships between users. Shouldn't be more than the number of users - 1.


    private HashMap<Integer, User> users; // Map an ID to a user
    private Random rand;


    public InfectionSimulation(int numUsers, int numRelationships) {
        this.numUsers = numUsers;
        this.numRelationships = numRelationships;
        rand = new Random();
    }

    /*
    Randomly generate a tree of users.
    The users are in a tree such that each users coach is above the user.
     */
    public void generateRandomTree() {
        users = new HashMap<Integer, User>();

        // Assign each user an ID
        for(int i = 0; i < numUsers; i++) {
            User user = new User(i);
            users.put(i, user);
        }

        int relationships = 0;
        while(relationships < numRelationships) {
            // Randomly pick a pair of student and coach
            int toCoach = rand.nextInt(numUsers);
            int toStudent = rand.nextInt(numUsers);

            // If the student does not have a coach, and coach and student have the same parent, and the student is not his own coach
            if(users.get(toStudent).getCoach() == -1 && root(toCoach) != root(toStudent) && toCoach != toStudent) {
                User coach = users.get(toCoach);
                User student = users.get(toStudent);

                // Add the student to the coach's list of students, and update the students coach
                coach.getStudents().add(toStudent);
                student.setCoach(toCoach);

                // The number of students we've just added to the tree (the people below the student + the student itself)
                int deltaStudents = student.getPeopleBelow() + 1;
                // For each user above this student, update their peopleBelow count
                int id = student.getCoach();
                while(id != -1) {
                    User user = users.get(id);
                    user.setPeopleBelow(user.getPeopleBelow() + deltaStudents);
                    id = user.getCoach();
                }

                // Relationship has been made
                relationships++;
            }
        }
    }

    public void totalInfection() {

    }

    public void limitedInfection() {

    }

    /*
    Recursively go up the tree until the node at the top is reached.
     */
    private int root(int id) {
        if(users.get(id).getCoach() != -1) {
            return root(users.get(id).getCoach());
        }
        return id;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public int getNumRelationships() {
        return numRelationships;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }


    public void printTree() {
        // Iterate through every user that is the head of a tree
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getCoach() == -1) {
                // Recursively print each tree
                printRelationships(i);
                System.out.println();
            }
        }
    }

    private void printRelationships(int id) {
        HashSet<Integer> students = users.get(id).getStudents();
        System.out.println("User " + id + " version " + users.get(id).getVersion() + " (" + users.get(id).getPeopleBelow() + "): " + students.toString());
        Iterator iter = students.iterator();
        while (iter.hasNext()) {
            printRelationships((Integer) iter.next());
        }
    }
}

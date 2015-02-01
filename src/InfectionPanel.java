import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InfectionPanel extends JPanel {

    private final int USER_DIAMETER = 10;

    private final Color INFECTED = Color.BLUE;
    private final Color REGULAR = Color.GREEN;

    HashMap<Integer, Point> positions; // Map each user ID to a position

    HashMap<Integer, User> users;

    ArrayList<Relationship> relationships;

    Random rand;



    public InfectionPanel() {
        super();
        rand = new Random();
        positions = new HashMap<Integer, Point>();
        users = null;
    }

    public void visualize(HashMap<Integer, User> users) {
        System.out.println("test");
        positions = new HashMap<Integer, Point>();
        this.users = users;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.removeAll();
        drawUsers(g2d);
        drawRelationships(g2d);
        this.revalidate();
        this.repaint();
    }

    private void drawUsers(Graphics2D graphics2D) {
        if(positions.size() == 0) {
            if (users != null && users.size() > 0) {
                // Generate random positions and assign them to each user
                for(Map.Entry<Integer, User> entry : users.entrySet()) {
                    Integer key = entry.getKey();
                    positions.put(key, new Point(rand.nextInt(getWidth()), rand.nextInt(getHeight())));
                }
            }
        }
        if(users != null && users.size() > 0) {
            for (User user : users.values()) {
                // Draw each user
                Point point = positions.get(user.getId());
                if (user.getVersion() > 0) {
                    graphics2D.setColor(INFECTED);
                } else {
                    graphics2D.setColor(REGULAR);
                }
                graphics2D.fillOval(point.x, point.y, USER_DIAMETER, USER_DIAMETER);
            }
        }
    }

    private void drawRelationships(Graphics2D graphics2D) {
        if(users != null && users.size() > 0) {
            relationships = new ArrayList<Relationship>();
            // Iterate through the users and find each relationship. Add to ArrayList
            for (User user : users.values()) {
                if (user.getCoach() != -1) {
                    relationships.add(new Relationship(user.getId(), user.getCoach()));
                }
            }

            // Iterate through the list of relationships and draw a line from point to point
            for (Relationship relationship : relationships) {
                Point a = positions.get(relationship.a);
                Point b = positions.get(relationship.b);
                User userA = users.get(relationship.a);
                User userB = users.get(relationship.b);
                if(userA.getVersion() > 0 && userB.getVersion() > 0) {
                    graphics2D.setColor(INFECTED);
                } else {
                    graphics2D.setColor(REGULAR);
                }

                graphics2D.drawLine(a.x + (USER_DIAMETER / 2), a.y + (USER_DIAMETER / 2), b.x + (USER_DIAMETER / 2), b.y + (USER_DIAMETER / 2));
            }
        }
    }



    public class Relationship {
        int a;
        int b;
        public Relationship(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}

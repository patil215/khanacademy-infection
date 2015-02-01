import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InfectionPanel extends JPanel {

    private final int USER_DIAMETER = 10;

    HashMap<Integer, Point> positions; // Map each user ID to a position

    HashMap<Integer, User> users;

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

        for(Point point : positions.values()) {
            // Draw each user
            graphics2D.drawOval(point.x, point.y, USER_DIAMETER, USER_DIAMETER);
        }
    }

    private void drawRelationships(Graphics2D graphics2D) {

    }


}

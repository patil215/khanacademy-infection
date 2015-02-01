import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class InfectionRunner {

    private static JFrame frame;
    private static JPanel controlsPanel;
    private static InfectionPanel infectionPanel;

    private static JLabel numberUsersLabel;
    private static JLabel numberRelationshipsLabel;
    private static JLabel limitedInfectionTargetLabel;

    private static JTextField numberUsersText;
    private static JTextField numberRelationshipsText;
    private static JTextField limitedInfectionTargetText;

    private static JButton generateButton;
    private static JButton infectButton;
    private static JButton resetButton;

    public static void main(String args[]) {
        runSimulation();

        generateFrame();
    }

    private static void generateFrame() {
        frame = new JFrame("Khan Academy Infections - Neil Patil");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contents = frame.getContentPane();
        contents.setLayout(new BoxLayout(contents, BoxLayout.PAGE_AXIS));

        controlsPanel = new JPanel(new FlowLayout());

        infectionPanel = new InfectionPanel();

        numberUsersLabel = new JLabel("Number of users:");
        numberRelationshipsLabel = new JLabel("Number of student/coach relationships:");
        limitedInfectionTargetLabel = new JLabel("Limited infection target number:");

        numberUsersText = new JTextField(4);
        numberRelationshipsText = new JTextField(4);
        limitedInfectionTargetText = new JTextField(4);

        generateButton = new JButton("Generate");
        infectButton = new JButton("Infect");
        resetButton = new JButton("Reset");

        controlsPanel.add(numberUsersLabel);
        controlsPanel.add(numberUsersText);
        controlsPanel.add(numberRelationshipsLabel);
        controlsPanel.add(numberRelationshipsText);
        controlsPanel.add(limitedInfectionTargetLabel);
        controlsPanel.add(limitedInfectionTargetText);
        controlsPanel.add(generateButton);
        controlsPanel.add(infectButton);
        controlsPanel.add(resetButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HashMap<Integer, User> graph = makeGraph();
                infectionPanel.visualize(graph);
            }
        });

        infectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        frame.add(controlsPanel);
        frame.add(infectionPanel);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static HashMap<Integer, User> makeGraph() {
        int numUsers = 10;
        int numRelationships = 3;
        int versionToInfect = 2;
        int limitedInfectionTarget = 5;
        boolean limited = true;

        InfectionSimulation simulation = new InfectionSimulation(numUsers, numRelationships);
        simulation.generateRandomTree();
        return simulation.getUsers();
    }

    private static void runSimulation() {
        int numUsers = 10;
        int numRelationships = 3;
        int versionToInfect = 2;
        int limitedInfectionTarget = 5;
        boolean limited = true;

        InfectionSimulation simulation = new InfectionSimulation(numUsers, numRelationships);
        simulation.generateRandomTree();
        simulation.printTree();

        System.out.println("\n\nTime for Infection\n\n");

        if(limited) {
            int numInfected = simulation.limitedInfection(limitedInfectionTarget, versionToInfect);
            System.out.println("Number infected: " + numInfected);
        } else {
            simulation.totalInfection(new Random().nextInt(simulation.getNumUsers()), versionToInfect);
        }

        simulation.printTree();
    }
}

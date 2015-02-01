import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
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
    private static JButton infectTotalButton;
    private static JButton infectLimitedButton;
    private static JButton resetButton;

    private static HashMap<Integer, User> graph;

    private static InfectionSimulation simulation;

    public static void main(String args[]) {
        // runSimulation();
        generateFrame();
    }

    private static void generateFrame() {
        frame = new JFrame("Khan Academy Infections - Neil Patil");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contents = frame.getContentPane();
        contents.setLayout(new BoxLayout(contents, BoxLayout.PAGE_AXIS));

        controlsPanel = new JPanel(new FlowLayout());
        controlsPanel.setMaximumSize(new Dimension(950, 0));

        infectionPanel = new InfectionPanel();

        numberUsersLabel = new JLabel("# Users:");
        numberRelationshipsLabel = new JLabel("# Relationships:");
        limitedInfectionTargetLabel = new JLabel("Target #:");

        numberUsersText = new JTextField(4);
        numberRelationshipsText = new JTextField(4);
        limitedInfectionTargetText = new JTextField(4);

        generateButton = new JButton("Generate");
        infectTotalButton = new JButton("Total Infection");
        infectLimitedButton = new JButton("Limited Infection");
        resetButton = new JButton("Reset");

        controlsPanel.add(numberUsersLabel);
        controlsPanel.add(numberUsersText);
        controlsPanel.add(numberRelationshipsLabel);
        controlsPanel.add(numberRelationshipsText);
        controlsPanel.add(limitedInfectionTargetLabel);
        controlsPanel.add(limitedInfectionTargetText);
        controlsPanel.add(generateButton);
        controlsPanel.add(infectTotalButton);
        controlsPanel.add(infectLimitedButton);
        controlsPanel.add(resetButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isInteger(numberUsersText.getText()) && isInteger(numberRelationshipsText.getText())) {
                    if(Integer.valueOf(numberUsersText.getText()) > 0 && Integer.valueOf(numberRelationshipsText.getText()) >= 0) {
                        if (Integer.valueOf(numberUsersText.getText()) > Integer.valueOf(numberRelationshipsText.getText())) {
                            makeGraph(Integer.valueOf(numberUsersText.getText()), Integer.valueOf(numberRelationshipsText.getText()));
                            infectionPanel.visualize(graph);
                        } else {
                            JOptionPane.showMessageDialog(null, "The number of users must be greater than the number of relationships.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter positive integers.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter integers for the number of users and relationships.");
                }
            }
        });

        infectTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                totalInfection();
            }
        });

        infectLimitedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isInteger(limitedInfectionTargetText.getText())) {
                    if(Integer.valueOf(limitedInfectionTargetText.getText()) > 0) {
                        if(Integer.valueOf(limitedInfectionTargetText.getText()) <= graph.size()) {
                            int numInfected = limitedInfection(Integer.valueOf(limitedInfectionTargetText.getText()));
                            JOptionPane.showMessageDialog(null, numInfected + " users were infected.");
                        } else {
                            JOptionPane.showMessageDialog(null, "The number of users to target must be less than or equal to the number of users.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a positive integer.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter an integer for the target number for limited infection.");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graph = new HashMap<Integer, User>();
                infectionPanel.visualize(graph);
            }
        });

        infectionPanel.setBackground(Color.WHITE);
        frame.add(controlsPanel);
        frame.add(infectionPanel);
        frame.setSize(950, 850);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void makeGraph(int numUsers, int numRelationships) {
        simulation = new InfectionSimulation(numUsers, numRelationships);
        simulation.generateRandomTree();
        graph = simulation.getUsers();
    }

    private static void totalInfection() {
        simulation.totalInfection(new Random().nextInt(simulation.getNumUsers()), 2);
        graph = simulation.getUsers();
    }

    // Returns the number that ended up infected
    private static int limitedInfection(int targetNumber) {
        int numberInfected = simulation.limitedInfection(targetNumber, 2);
        graph = simulation.getUsers();
        return numberInfected;
    }

    private static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    private static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}

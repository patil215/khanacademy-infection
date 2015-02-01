import java.util.Random;

public class InfectionRunner {

    public static void main(String args[]) {
        int numUsers = 10;
        int numRelationships = 5;
        int versionToInfect = 2;

        InfectionSimulation simulation = new InfectionSimulation(numUsers, numRelationships);
        simulation.generateRandomTree();
        simulation.printTree();

        System.out.println("\n\nTime for Infection\n\n");

        simulation.totalInfection(new Random().nextInt(simulation.getNumUsers()), versionToInfect);
        simulation.printTree();;
    }
}

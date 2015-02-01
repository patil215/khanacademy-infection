import java.util.Random;

public class InfectionRunner {

    public static void main(String args[]) {
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

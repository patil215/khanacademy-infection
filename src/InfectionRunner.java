public class InfectionRunner {
    public static void main(String args[]) {
        int numUsers = 10;
        int numRelationships = 5;

        InfectionSimulation simulation = new InfectionSimulation(numUsers, numRelationships);
        simulation.generateRandomTree();
        simulation.printTree();

    }
}

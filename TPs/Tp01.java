import java.io.*;
import java.util.Scanner;

public class Tp01 {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String path;
        GraphRepresentation graph = null;
        try {
            do {
                System.out.println("Type the path of the root file: ");
                path = sc.nextLine();
                if (!GeneralUtils.isEmpty(path) && GeneralUtils.isFileExists(new File(path)))
                    graph = new GraphRepresentation(path);
            } while (graph == null);
            int node = -1;
            do {
                try {
                    System.out.print("Type the expected node (Integer value between 1 and " + graph.size() + "): ");
                    String nodeText = sc.next();
                    node = Integer.parseInt(nodeText);
                } catch (NumberFormatException e) {
                    System.out.println("It is not an Integer number!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } while (node <= 0 || node > graph.size());
            System.out.println("Node: " + node);
            System.out.println("\nSuccessors: " + graph.getSuccessors(node).stream().map(value -> value + " ").reduce("", (ac, textNode) -> ac + textNode));
            System.out.println("Out degree: " + graph.getOutDegree(node));
            System.out.println("\nPredecessors: " + graph.getPredecessors(node).stream().map(value -> value + " ").reduce("", (ac, textNode) -> ac + textNode));
            System.out.println("In degree: " + graph.getInDegree(node));

        } catch (AssertionError | FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

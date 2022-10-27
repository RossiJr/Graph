import java.io.*;
import java.util.Scanner;

public class Fleury {
    public static void main (String[] args){
        fleuryMethod();
    }
    public static int[] fleuryMethod(){
        GraphRepresentation graph = null, graphCopy = null;
        int impar=0;
        int[] eurelianCicle = new int[getGraphNumEdges(graphCopy)];

        try {
            do {
                if (!GeneralUtils.isEmpty("graph-test-100.txt") && GeneralUtils.isFileExists(new File("graph-test-100.txt")))
                    graph = new GraphRepresentation("graph-test-100.txt");
            } while (graph == null);
        } catch (AssertionError | FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0; i<graph.size(); i++){
            if (graph.getOutDegree(i)%2==1)
            impar++;
        }

        if (impar!=3 || impar<3){
            return null;
        }

        try {
            if (!GeneralUtils.isEmpty("graph-test-100.txt") && GeneralUtils.isFileExists(new File("graph-test-100.txt"))){
                    graphCopy = new GraphRepresentation("graph-test-100.txt");
                    graphCopy.setSuccesors(graph.cloneSuccesors());
            }

            int vertice = selectVerticeWithEvenDegree(graphCopy);
            int[] aresta = new int[2];
            int indiceEurelian = 0;

            while(getGraphNumEdges(graphCopy)>0){
                if(graph.getOutDegree(vertice)>1){
                    aresta = graph.selectNonBridge();
                }
                vertice = aresta[1];

                graph.removeEdge(aresta);
                eurelianCicle[indiceEurelian] = aresta[0];
                eurelianCicle[++indiceEurelian] = aresta[1];
            }

        } catch (NumberFormatException e) {
            System.out.println("It is not an Integer number!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eurelianCicle;
    }

    public static int selectVerticeWithEvenDegree(GraphRepresentation graph){
        for (int i=0; i<graph.size(); i++){
            if (graph.getOutDegree(i)%2==1){
                return i;
            }
        }
        return -1;
    }

    public static int getGraphNumEdges(GraphRepresentation graph){
        int sum_of_degrees=0, numEdges=0;
        for (int i=0; i<graph.size(); i++){
           sum_of_degrees += graph.getOutDegree(i);
        }
        numEdges = sum_of_degrees/=2;

        return numEdges;
    }
}

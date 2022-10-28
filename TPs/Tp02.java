import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Tp02 {
    public static void main1(String[] args) throws IOException {
        String path = "D:\\graphs\\Data\\graph-test-small.txt";
        NotDirectedGraph ndg = new NotDirectedGraph(path);
        System.out.println(ndg.naive());
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer nVertices = 10;
        Boolean save = true;
        String basePath = "Data\\";
        String eurelianPath = basePath + "eurelian\\graph-random-eurelian-{nVertices}.txt";
        String semiEurelianPath = basePath + "semi-eurelian\\graph-random-semi_eurelian-{nVertices}.txt";
        String notEurelianPath = basePath + "not-eurelian\\graph-random-not_eurelian-{nVertices}.txt";
        for(int i=0; i<5; i++) {
            GraphsUtils.generateRandomGraph(eurelianPath.replace("{nVertices}", String.valueOf(nVertices)+"("+((i+1))+")"), nVertices, 2, 4, save, "eurelian");
            GraphsUtils.generateRandomGraph(eurelianPath.replace("{nVertices}", String.valueOf(nVertices*10)+"("+(i+1)+")"), nVertices * 10, 2, 20, save, "eurelian");
            GraphsUtils.generateRandomGraph(eurelianPath.replace("{nVertices}", String.valueOf(nVertices*100)+"("+(i+1)+")"), nVertices * 100, 2, 30, save, "eurelian");
            GraphsUtils.generateRandomGraph(eurelianPath.replace("{nVertices}", String.valueOf(nVertices*1000)+"("+(i+1)+")"), nVertices * 1000, 2, 50, save, "eurelian");
            GraphsUtils.generateRandomGraph(eurelianPath.replace("{nVertices}", String.valueOf(nVertices*10000)+"("+(i+1)+")"), nVertices * 10000, 2, 70, save, "eurelian");

            GraphsUtils.generateRandomGraph(semiEurelianPath.replace("{nVertices}", String.valueOf(nVertices)+"("+(i+1)+")"), nVertices, 2, 4, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(semiEurelianPath.replace("{nVertices}", String.valueOf(nVertices*10)+"("+(i+1)+")"), nVertices * 10, 2, 20, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(semiEurelianPath.replace("{nVertices}", String.valueOf(nVertices*100)+"("+(i+1)+")"), nVertices * 100, 2, 30, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(semiEurelianPath.replace("{nVertices}", String.valueOf(nVertices*1000)+"("+(i+1)+")"), nVertices * 1000, 2, 50, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(semiEurelianPath.replace("{nVertices}", String.valueOf(nVertices*10000)+"("+(i+1)+")"), nVertices * 10000, 2, 70, save, "semi-eurelian");


            GraphsUtils.generateRandomGraph(notEurelianPath.replace("{nVertices}", String.valueOf(nVertices)+"("+(i+1)+")"), nVertices, 2, 4, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(notEurelianPath.replace("{nVertices}", String.valueOf(nVertices*10)+"("+(i+1)+")"), nVertices * 10, 2, 20, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(notEurelianPath.replace("{nVertices}", String.valueOf(nVertices*100)+"("+(i+1)+")"), nVertices * 100, 2, 30, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(notEurelianPath.replace("{nVertices}", String.valueOf(nVertices*1000)+"("+(i+1)+")"), nVertices * 1000, 2, 50, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(notEurelianPath.replace("{nVertices}", String.valueOf(nVertices*10000)+"("+(i+1)+")"), nVertices * 10000, 2, 70, save, "not-eurelian");
        }
        sc.close();
    }
}

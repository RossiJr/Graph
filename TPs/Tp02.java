import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Tp02 {
    static String basePath = "Data\\";
    static String eurelianPath = basePath + "eurelian\\graph-random-eurelian-{nVertices}{index}.txt";
    static String semiEurelianPath = basePath + "semi-eurelian\\graph-random-semi_eurelian-{nVertices}{index}.txt";
    static String notEurelianPath = basePath + "not-eurelian\\graph-random-not_eurelian-{nVertices}{index}.txt";
    public static void main1(String[] args) throws IOException {
        String path = "D:\\graphs\\Data\\others\\graph-test-small.txt";
        NotDirectedGraph ndg = new NotDirectedGraph(path);
        System.out.println(ndg.naive());
    }
    public static void main(String[] args) throws IOException{
        List<NotDirectedGraph> graphs = new ArrayList<>();
        for(int i=1; i<=5; i++)
            try {
//                new NotDirectedGraph((replace(eurelianPath, 10, i))).eurelianTour().forEach(j -> System.out.print(j[0] + " - " + j[1] + " | "));
//                System.out.println(new NotDirectedGraph("D:\\graphs\\Data\\others\\graph-eurelian-tour-test.txt").getConnectedComponents() + " | " + i);
//                new NotDirectedGraph("D:\\graphs\\Data\\others\\graph-eurelian-tour-test.txt").eurelianTour().forEach(j -> System.out.print(j[0] + " - " + j[1] + " | "));
                Map<Integer, List<Integer>> map = new NotDirectedGraph("D:\\graphs\\Data\\others\\graph-test-small.txt").naive();
                System.out.println(map.entrySet());
            } catch (StackOverflowError e){
                i--;
//                e.printStackTrace();
            }
            }
    private static String replace(String path, Integer nVertices, Integer index){
        return path.replace("{nVertices}", String.valueOf(nVertices))
                .replace("{index}", "("+((index))+")");
    }

    public static void main2(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer nVertices = 10;
        Boolean save = false;

        for(int i=0; i<5; i++) {
            GraphsUtils.generateRandomGraph(replace(eurelianPath, nVertices, i+1), nVertices, 2, 4, save, "eurelian");
            GraphsUtils.generateRandomGraph(replace(eurelianPath, nVertices*10, i+1), nVertices * 10, 2, 20, save, "eurelian");
            GraphsUtils.generateRandomGraph(replace(eurelianPath, nVertices*100, i+1), nVertices * 100, 2, 30, save, "eurelian");
            GraphsUtils.generateRandomGraph(replace(eurelianPath, nVertices*1000, i+1), nVertices * 1000, 2, 50, save, "eurelian");
            GraphsUtils.generateRandomGraph(replace(eurelianPath, nVertices*10000, i+1), nVertices * 10000, 2, 70, save, "eurelian");

            GraphsUtils.generateRandomGraph(replace(semiEurelianPath, nVertices, i+1), nVertices, 2, 4, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(replace(semiEurelianPath, nVertices*10, i+1), nVertices * 10, 2, 20, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(replace(semiEurelianPath, nVertices*100, i+1), nVertices * 100, 2, 30, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(replace(semiEurelianPath, nVertices*1000, i+1), nVertices * 1000, 2, 50, save, "semi-eurelian");
            GraphsUtils.generateRandomGraph(replace(semiEurelianPath, nVertices*10000, i+1), nVertices * 10000, 2, 70, save, "semi-eurelian");


            GraphsUtils.generateRandomGraph(replace(notEurelianPath, nVertices, i+1), nVertices, 2, 4, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(replace(notEurelianPath, nVertices*10, i+1), nVertices * 10, 2, 20, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(replace(notEurelianPath, nVertices*100, i+1), nVertices * 100, 2, 30, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(replace(notEurelianPath, nVertices*1000, i+1), nVertices * 1000, 2, 50, save, "not-eurelian");
            GraphsUtils.generateRandomGraph(replace(notEurelianPath, nVertices*10000, i+1), nVertices * 10000, 2, 70, save, "not-eurelian");
        }
        sc.close();
    }
}

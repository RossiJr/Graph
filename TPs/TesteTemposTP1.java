import java.io.IOException;
import java.util.Arrays;

public class TesteTemposTP1 {
    static String basePath = "Data\\";
    static String eurelianPath = basePath + "eurelian\\graph-random-eurelian-{nVertices}{index}.txt";
    static String semiEurelianPath = basePath + "semi-eurelian\\graph-random-semi_eurelian-{nVertices}{index}.txt";
    static String notEurelianPath = basePath + "not-eurelian\\graph-random-not_eurelian-{nVertices}{index}.txt";
    private static String replace(String path, Integer nVertices, Integer index){
        return path.replace("{nVertices}", String.valueOf(nVertices))
                .replace("{index}", "("+((index))+")");
    }
    public static void main(String[] args) throws IOException {
        for(int n = 100; n <= 100000; n*=10) {
            Long[] timeEurelian = new Long[5];
            Long[] timeSemiEurelian = new Long[5];
            Long[] timeNotEurelian = new Long[5];
            Long[] meanExecutionTime = new Long[3];

            // Naive execution 10 vertices - eurelian
            for (int i = 0; i < 5; i++) {
                Long timeStartIn = System.currentTimeMillis();
                new NotDirectedGraph(replace(eurelianPath, n, i + 1)).tarjan();
                Long timeEndIn = System.currentTimeMillis();
                timeEurelian[i] = timeEndIn - timeStartIn;
            }

            // Naive execution 10 vertices - semiEurelian
            for (int i = 0; i < 5; i++) {
                Long timeStartIn = System.currentTimeMillis();
                new NotDirectedGraph(replace(semiEurelianPath, n, i + 1)).tarjan();
                Long timeEndIn = System.currentTimeMillis();
                timeSemiEurelian[i] = timeEndIn - timeStartIn;
            }

            // Naive execution 10 vertices - notEurelian
            for (int i = 0; i < 5; i++) {
                Long timeStartIn = System.currentTimeMillis();
                new NotDirectedGraph(replace(notEurelianPath, n, i + 1)).tarjan();
                Long timeEndIn = System.currentTimeMillis();
                timeNotEurelian[i] = timeEndIn - timeStartIn;
            }
            Long executionMean = 0L;
            for(int i=0; i<5; i++){
                executionMean += timeEurelian[i];
                executionMean += timeSemiEurelian[i];
                executionMean += timeNotEurelian[i];
            }
            executionMean /= 15;

            System.out.println("Vertices {" + n + "}: " + executionMean);
        }
//        new NotDirectedGraph("D:\\graphs\\Data\\others\\graph-test-small.txt").tarjan();
    }
}

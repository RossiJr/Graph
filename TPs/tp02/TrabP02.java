package tp02;

import java.math.BigDecimal;
import java.util.*;

public class TrabP02 {
    private static void findPaths(String path, Integer origin, Integer destiny){
        Long timeStart = System.currentTimeMillis();
        GraphRepresentation graph = new GraphRepresentation(path);
        Integer auxOrigin = origin - 1, auxDestiny = destiny - 1;
        List<int[]> maxDisjuntPaths = graph.FindDisjointedPath(auxOrigin, auxDestiny);
        Long timeFinish = System.currentTimeMillis();
        System.out.print("Maximum disjunt paths from " + origin + " to " + destiny + ": " + maxDisjuntPaths.size() + "\t\t With ");
        System.out.println(new BigDecimal(timeFinish-timeStart).divide(new BigDecimal(1000)) + " s");
        printPaths(maxDisjuntPaths, auxOrigin, auxDestiny);
        System.out.println("\n\n");
    }
    public static void main(String[] args) {
        String originPath = "D:\\graphs\\Data\\others\\tp2\\graph-{type}-{n}.txt";
        originPath = originPath.replace("{type}", "circular");
        for(int i=10; i<=10000; i*=10){
            originPath = originPath.replace("{n}", String.valueOf(i));
            findPaths(originPath, 1, i);
            originPath = originPath.replace("-" + String.valueOf(i), "-{n}");
        }
        originPath = originPath.replace("circular", "complete");
        for(int i=10; i<=1000; i*=10){
            originPath = originPath.replace("{n}", String.valueOf(i));
            findPaths(originPath, 1, i);
            originPath = originPath.replace("-" + String.valueOf(i), "-{n}");
        }
        originPath = originPath.replace("complete", "null");
        for(int i=10; i<=10000; i*=10){
            originPath = originPath.replace("{n}", String.valueOf(i));
            findPaths(originPath, 1, i);
            originPath = originPath.replace("-" + String.valueOf(i), "-{n}");
        }
    }

    private static void printPaths(List<int[]> maxDisjuntPaths, Integer origin, Integer destiny) {

        maxDisjuntPaths.forEach(x -> {
            List<Integer> auxElements = new ArrayList<>();
            auxElements.add(destiny);
            int element = x[destiny];
            do{
                auxElements.add(element);
                element = x[element];
            } while(element != -1);
            Collections.reverse(auxElements);
            auxElements.remove(0);
            System.out.println(auxElements.stream().map(i -> String.valueOf(i+1)).reduce(String.valueOf(origin+1), (partialString, y) -> partialString + " -> " + y));
        });

    }
}
package tp02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {
    public static void generateCompleteGraph(String path, Integer nVertices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
        bw.write(" " + nVertices + " " + nVertices * (nVertices - 1) + "\n");
        for (int i = 1; i <= nVertices; i++) {
            for (int j = 1; j <= nVertices; j++) {
                if (j != i) {
                    bw.write(new StringBuilder(" ").append(i).append(" ").append(j).append("\n").toString());
                }
            }
        }
        bw.close();
    }

    public static void generateNullGraph(String path, Integer nVertices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
        bw.write(" " + nVertices + " " + 0 + "\n");
        bw.close();
    }

    public static void generateCircularGraphDirected(String path, Integer nVertices) throws IOException {
        Integer mArcs = nVertices;

        BufferedWriter bw = new BufferedWriter(new FileWriter(path + ".aux.txt", false));

        for (int i = 1; i <= nVertices; i++) {
            List<Integer> arcs = new ArrayList<>();
            Integer sucessor = 0;

            if (i == nVertices) {
                sucessor = 1;
            } else {
                sucessor = i + 1;
            }

            arcs.add(sucessor);
            bw.write(new StringBuilder(" ").append(i).append(" ").append(sucessor).append("\n").toString());
        }
        bw.close();

        BufferedWriter bwAux = new BufferedWriter(new FileWriter(path, false));

        bwAux.write(nVertices + " " + mArcs + "\n");
        new BufferedReader(new FileReader(path + ".aux.txt")).lines().forEach(l -> {
            try {
                bwAux.write(l + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bwAux.close();
    }

}

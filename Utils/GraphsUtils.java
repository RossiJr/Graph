import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class GraphsUtils {

    public static List<Integer[]> processFile(BufferedReader file) throws IOException{
        Integer[] array2;
        Integer[] array1;
        if (GeneralUtils.isValidFile(file)) {
            String[] amountEdgesArcs = GeneralUtils.onlyNumbers(file.readLine());
            array1 = new Integer[Integer.parseInt(amountEdgesArcs[0])+1];
            array2 = new Integer[Integer.parseInt(amountEdgesArcs[1])];

            array1[0] = 0;
            array1[array1.length-1] = array2.length+1;

            for(int i=0, j=1; i<Integer.parseInt(amountEdgesArcs[1]); i++){
                try {
                    Object[] values = Arrays.stream(GeneralUtils.onlyNumbers(file.readLine())).map(Integer::parseInt).toArray();
                    if (j < (Integer) values[0]) {
                        array1[j] = i;
                        j++;
                    }
                    array2[i] = (Integer) values[1];
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } else {
            throw new IOException("Error while opening the file");
        }
        List<Integer[]> returnValues = new ArrayList<>();
        returnValues.add(array1);
        returnValues.add(array2);

        return returnValues;
    }

    public static void generateRandomGraph(String path, Integer nVertices, Integer minArcsPerVertice, Integer maxArcsPerVertice) throws IOException {
        if (nVertices < 0) {
            throw new IllegalArgumentException();
        }
        Random randomNumber = new Random(Instant.now().getEpochSecond());
        Integer mArcs = 0;

        Map<Integer, List<Integer>> verticesEdges = new HashMap<>();
        for (int i = 1; i <= nVertices; i++) {
            verticesEdges.put(i, new ArrayList<>());
        }
        for (int i = 1; i <= nVertices; i++) {
            Integer numberArcs = (Math.abs(randomNumber.nextInt() % (maxArcsPerVertice - minArcsPerVertice + 1)) + minArcsPerVertice) - verticesEdges.get(i).size();
            if (numberArcs > 0) {
                if ((numberArcs + verticesEdges.get(i).size()) % 2 == 1) {
                    numberArcs++;
                }
                mArcs += numberArcs;

                for (int j = 0; j < numberArcs; j++) {
                    Integer sucessor = Math.abs(randomNumber.nextInt() % (nVertices)) + 1;
                    if (sucessor == i || verticesEdges.get(i).contains(sucessor)) {
                        j--;
                    } else {
                        verticesEdges.get(i).add(sucessor);
                        verticesEdges.get(sucessor).add(i);
                    }
                }

            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(" " + nVertices + " " + mArcs + "\n");
        for (int i = 1; i <= verticesEdges.size(); i++) {
            int finalI = i;
            verticesEdges.get(i).forEach(edge -> {
                try {
                    bw.write(" " + finalI + " " + edge + " \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        bw.close();
    }

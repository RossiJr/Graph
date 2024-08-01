import java.io.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GraphsUtils {

    public static List<Integer[]> processFile(BufferedReader file) throws IOException {
        Integer[] array1;
        Integer[] array2;
        if (GeneralUtils.isValidFile(file)) {
            String[] amountVerticesArcs = GeneralUtils.onlyNumbers(file.readLine());
            array1 = new Integer[Integer.parseInt(amountVerticesArcs[0]) + 1];
            array2 = new Integer[Integer.parseInt(amountVerticesArcs[1])];

            array1[0] = 0;
            array1[array1.length - 1] = array2.length + 1;

            for (int i = 0, j = 1; i < Integer.parseInt(amountVerticesArcs[1]); i++) {
                try {
                    Object[] values = Arrays.stream(GeneralUtils.onlyNumbers(file.readLine())).map(Integer::parseInt).toArray();
                    if (j < (Integer) values[0]) {
                        array1[j] = i;
                        j++;
                    }
                    array2[i] = (Integer) values[1];
                } catch (Exception e) {
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

    public static List<List<Integer>> processFileNotDirected(BufferedReader file) throws IOException {
        var ref = new Object() {
            List<List<Integer>> returnValues = null;
        };
        if (GeneralUtils.isValidFile(file)) {
            ref.returnValues = new ArrayList<>();
            String[] amountVerticesArcs = GeneralUtils.onlyNumbers(file.readLine());
            Stream.iterate(0, x -> x + 1).limit(Integer.parseInt(amountVerticesArcs[0])).forEach(x -> ref.returnValues.add(new ArrayList<>()));
            Stream.iterate(0, x -> x + 1).limit(Integer.parseInt(amountVerticesArcs[1])).forEach(x -> {
                try {
                    Object[] values = Arrays.stream(GeneralUtils.onlyNumbers(file.readLine())).map(Integer::parseInt).toArray();
                    ref.returnValues.get(((Integer) values[0]) - 1).add(((Integer) values[1]) - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return ref.returnValues;
    }

    public static void generateRandomGraphDirected(String path, Integer nVertices, Integer minArcsPerVertice, Integer maxArcsPerVertice) throws IOException {
        Random randomNumber = new Random(Instant.now().getEpochSecond());
        Integer mArcs = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter(path + ".aux.txt", false));
        for (int i = 1; i <= nVertices; i++) {
            List<Integer> arcs = new ArrayList<>();
            Integer numberArcs = Math.abs(randomNumber.nextInt() % (maxArcsPerVertice - minArcsPerVertice + 1)) + minArcsPerVertice;
            if (numberArcs % 2 == 1) {
                numberArcs++;
            }
            mArcs += numberArcs;
            for (int j = 0; j < numberArcs; j++) {
                Integer sucessor = Math.abs(randomNumber.nextInt() % (nVertices)) + 1;
                if (sucessor == i || arcs.contains(sucessor)) {
                    j--;
                } else {
                    arcs.add(sucessor);
                    bw.write(new StringBuilder(" ").append(i).append(" ").append(sucessor).append("\n").toString());
                }
            }
            System.out.println(i);
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

    public static void generateRandomGraph(String path, Integer nVertices, Integer minArcsPerVertice, Integer maxArcsPerVertice, Boolean save, String generationType) throws IOException {
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

                for (int j = 0; j < numberArcs; j++) {
                    Integer sucessor = Math.abs(randomNumber.nextInt() % (nVertices)) + 1;
                    if (sucessor == i || verticesEdges.get(i).contains(sucessor)) {
                        j--;
                    } else {
                        verticesEdges.get(i).add(sucessor);
                        verticesEdges.get(sucessor).add(i);
                    }
                }
                mArcs += numberArcs * 2;
            }
        }
        List<Integer> verticesOdd = new ArrayList<>(verticesEdges.entrySet().stream().filter(x -> x.getValue().size() % 2 == 1)
                .sorted(Comparator.comparing(x -> x.getValue().stream().reduce(0, Integer::sum)))
                .map(Map.Entry::getKey).toList());
        while (verticesOdd.size() >= (generationType.equals("eurelian") ? 2 : (generationType.equals("semi-eurelian") ? 4 : 6))) {
            if (!(verticesEdges.get(verticesOdd.get(0)).contains(verticesOdd.get(1)) || verticesEdges.get(verticesOdd.get(1)).contains(verticesOdd.get(0)))) {
                verticesEdges.get(verticesOdd.get(0)).add(verticesOdd.get(1));
                verticesEdges.get(verticesOdd.get(1)).add(verticesOdd.get(0));
                verticesOdd.remove(1);
                verticesOdd.remove(0);
                mArcs += 2;
            } else {
                verticesEdges.get(verticesOdd.get(0)).remove((Integer) verticesOdd.get(1));
                verticesEdges.get(verticesOdd.get(1)).remove((Integer) verticesOdd.get(0));
                verticesOdd.remove(1);
                verticesOdd.remove(0);
                mArcs -= 2;
                Collections.shuffle(verticesOdd);
            }
        }
        if (save) {
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
    }
}


















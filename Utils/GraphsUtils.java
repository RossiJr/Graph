import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void generateRandomGraph(String path, Integer nEdges, Integer minArcsPerEdge, Integer maxArcsPerEdge) throws IOException{
        Random randomNumber = new Random(Instant.now().getEpochSecond());
        Integer mArcs = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter(path+".aux.txt", false));
        for(int i=1; i<=nEdges; i++){
            List<Integer> arcs = new ArrayList<>();
            Integer numberArcs = Math.abs(randomNumber.nextInt()%(maxArcsPerEdge-minArcsPerEdge+1))+minArcsPerEdge;
            mArcs+=numberArcs;
            for(int j=0; j<numberArcs; j++){
                Integer sucessor = Math.abs(randomNumber.nextInt()%(nEdges))+1;
                if(sucessor == i || arcs.contains(sucessor)){
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
        bwAux.write(nEdges + " " + mArcs + "\n");
        new BufferedReader(new FileReader(path+".aux.txt")).lines().forEach(l -> {
            try {
                bwAux.write(l + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bwAux.close();
    }
}


















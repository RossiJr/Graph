import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphsUtils {
    public static void printFile(BufferedReader file) throws IOException {
        if(GeneralUtils.isValidFile(file)) {
            String line = file.readLine();
            while (line != null) {
                System.out.println(line);
                line = file.readLine();
            }
        }
    }

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
                    System.out.println(i + " " + j);
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
}

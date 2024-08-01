import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PredecessorRepresentationGraph {
    private final String pathAux = "Data\\graph-aux.tmp.txt";

    private Integer[] origin;
    private Integer[] destiny;

    /**
        * Method not efficient - Just a palliative implementation
     */
    private BufferedReader sortFile(String path) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        if(!GeneralUtils.isValidFile(file)){
            throw new IOException("File not valid!");
        }
        List<List<Integer>> values = new ArrayList<>();
        String line1 = file.readLine();
        String line = file.readLine();
        int i=0;
        do{
            values.add(Arrays.stream(GeneralUtils.onlyNumbers(line)).map(Integer::parseInt).collect(Collectors.toList()));
            line = file.readLine();
            i++;
        } while (line != null);
        values.sort(new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                return ((List<Integer>) o1).get(1).compareTo(((List<Integer>) o2).get(1));
            }
        });

        values.add(0, Arrays.stream(GeneralUtils.onlyNumbers(line1)).map(Integer::parseInt).collect(Collectors.toList()));

        BufferedWriter auxFile = new BufferedWriter(new FileWriter(pathAux));
        auxFile.write(values.get(0).get(0) + " " + values.get(0).get(1) + "\n");
        values.subList(1, values.size()).forEach(list -> {
            try {
                auxFile.write(list.get(1) + " " + list.get(0) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        auxFile.close();

        return new BufferedReader(new FileReader(pathAux));
    }

    public PredecessorRepresentationGraph(String path, boolean sortedFile) throws IOException {
        BufferedReader file;
        if(!sortedFile)
            file = sortFile(path);
        else
            file = new BufferedReader(new FileReader(path));

        //CONSIDERA QUE O ARQUIVO ESTEJA ORDENADO PELA COLUNA DE ARESTAS
        if(GeneralUtils.isValidFile(file)){
            List<Integer[]> arrays = GraphsUtils.processFile(file);
            destiny = arrays.get(0);
            origin = arrays.get(1);
        } else {
            throw new IOException("Error while opening the file");
        }
    }
    public SuccessorsRepresentationGraph clone(){
        return new SuccessorsRepresentationGraph(this.origin.clone(), this.destiny.clone());
    }

    public Integer[] getOrigin(){
        return this.origin;
    }
    public Integer[] getDestiny(){
        return this.destiny;
    }
}

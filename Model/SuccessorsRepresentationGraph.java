import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SuccessorsRepresentationGraph {

    private Integer[] origin;
    private Integer[] destiny;

    public SuccessorsRepresentationGraph(Integer[] origin, Integer[] destiny) {
        this.origin = origin;
        this.destiny = destiny;
    }

    public SuccessorsRepresentationGraph(String path) throws IOException {

        //CONSIDERA QUE O ARQUIVO ESTEJA ORDENADO PELA COLUNA DE VERTICES
        BufferedReader file = new BufferedReader(new FileReader(path));
        if (GeneralUtils.isValidFile(file)) {
            List<Integer[]> arrays = GraphsUtils.processFile(file);
            origin = arrays.get(0);
            destiny = arrays.get(1);

            //Arrays.stream(origin).forEach(System.out::println);
            //System.out.println(destiny[30]);
        } else {
            throw new IOException("Error while opening the file");
        }
    }

    public Integer[] getOrigin() {
        return this.origin;
    }

    public Integer[] getDestiny() {
        return this.destiny;
    }

    public SuccessorsRepresentationGraph clone(){
        return new SuccessorsRepresentationGraph(this.origin.clone(), this.destiny.clone());
    }
}

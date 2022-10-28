import javax.print.attribute.IntegerSyntax;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphRepresentation {
    private SuccessorsRepresentationGraph succesors;
    private PredecessorRepresentationGraph predecessors;


    /**
     * Functions that returns the predecessors or successors nodes
     *
     * @param node   Expected node
     * @param array1 Array which contains the references to the nodes in the second array
     * @param array2 Array which contains the nodes
     * @return List of nodes referenced by the expected node
     */
    private List<Integer> getNodesReferenced(Integer node, Integer[] array1, Integer[] array2) {
        if (node <= 0 && node > size()) {
            throw new IndexOutOfBoundsException();
        }
        List<Integer> nodes = new ArrayList<>();
        int amountNodes = array1[node] - array1[node - 1];
        for (int i = 0; i < amountNodes; i++) {
            int pos = array1[node - 1] + i;
            if (pos < array2.length) {
                nodes.add(array2[pos]);
            }
        }
        return nodes;
    }

    /**
     * The numbers of nodes in this graph.
     * @return The numbers of nodes in this graph.
     */
    public int size() {
        return succesors.getOrigin().length - 1;
    }

    /**
     * Returns the successors degree of a node.
     * @param node Expected node.
     * @return Returns the successors degree of a node.
     * @throws IndexOutOfBoundsException In case of trying to access a position that is not between 1 and the size of the graph. (inclusive)
     */
    public Integer getOutDegree(Integer node) {
        if (node > 0 && node <= size()) {
            return getSuccessors(node).size();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * Returns the predecessors degree of a node.
     * @param node Expected node.
     * @return Returns the predecessors degree of a node.
     * @throws IndexOutOfBoundsException In case of trying to access a position that is not between 1 and the size of the graph. (inclusive)
     */
    public Integer getInDegree(Integer node) {
        if (node > 0 && node <= size()) {
            return getPredecessors(node).size();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * Returns the successors list of a node.
     * @param node Expected node.
     * @return Returns the successors list of a node.
     * @throws IndexOutOfBoundsException In case of trying to access a position that is not between 1 and the size of the graph. (inclusive)
     */
    public List<Integer> getSuccessors(Integer node) {
        if (node > 0 && node <= size()) {
            return getNodesReferenced(node, succesors.getOrigin(), succesors.getDestiny());
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the predecessors list of a node.
     * @param node Expected node.
     * @return Returns the predecessors list of a node.
     * @throws IndexOutOfBoundsException In case of trying to access a position that is not between 1 and the size of the graph. (inclusive)
     */
    public List<Integer> getPredecessors(Integer node) {
        if (node > 0 && node <= size()) {
            return getNodesReferenced(node, predecessors.getDestiny(), predecessors.getOrigin());
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the graph's object. It is built from the file referenced by path.
     * @param path File used to build the graph. Confer the documentation to see the file patterns.
     * @throws IOException In case of error reading/writing the file.
     * @throws FileNotFoundException In case of the file have not been found.
     */
    public GraphRepresentation(String path) throws IOException {
        if (!GeneralUtils.isEmpty(path) && GeneralUtils.isFileExists(new File(path))) {
            this.succesors = new SuccessorsRepresentationGraph(path);
            this.predecessors = new PredecessorRepresentationGraph(path, false);
        } else {
            throw new FileNotFoundException("File '" + path + "' not found!");
        }
    }
    public GraphRepresentation(String pathSuccessors, String pathPredecessors) throws IOException {
        if ((!GeneralUtils.isEmpty(pathSuccessors) && !GeneralUtils.isEmpty(pathPredecessors)) &&
                (GeneralUtils.isFileExists(new File(pathSuccessors)) && GeneralUtils.isFileExists(new File(pathPredecessors)))) {
            this.succesors = new SuccessorsRepresentationGraph(pathSuccessors);
            this.predecessors = new PredecessorRepresentationGraph(pathPredecessors, true);
        } else {
            throw new FileNotFoundException("File '" + pathSuccessors + "' or '" + pathPredecessors + "' not found!");
        }
    }
    private List<Integer> naive(){
        List<Integer> bridges = new ArrayList<>();
        SuccessorsRepresentationGraph successorsClone = succesors.clone();



        return bridges;
    }

    public List<Integer> getBridges(String algorithm){
        if(algorithm.equals("naive")){
            return naive();
        }


        return null;
    }
    public boolean isEureliano(){
        List<Integer> grausVertices = new ArrayList<>();
        for(int i=1; i<=size(); i++){
            grausVertices.add(getOutDegree(i));
        }

        return true;
    }

    public void setSuccesors(SuccessorsRepresentationGraph succesors) {
        this.succesors = succesors;
    }

    public void setPredecessors(PredecessorRepresentationGraph predecessors) {
        this.predecessors = predecessors;
    }
}

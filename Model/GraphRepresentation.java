import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphRepresentation {
    private SuccessorsRepresentationGraph succesors;
    private PredecessorRepresentationGraph predecessors;

    private static List<Integer> getNodesReferenced(Integer node, Integer[] array1, Integer[] array2){
        List<Integer> nodes = new ArrayList<>();
        Integer amountNodes = array1[node] - array1[node-1];
        for(int i=0; i<amountNodes; i++){
            int pos = array1[node-1]+i;
            if(pos < array2.length){
                nodes.add(array2[pos]);
            }
        }
        return nodes;
    }

    public int size(){
        return succesors.getOrigin().length-1;
    }

    public Integer getOutDegree(Integer node){
        return getSuccessors(node).size();
    }
    public Integer getInDegree(Integer node){
        return getPredecessors(node).size();
    }

    public List<Integer> getSuccessors(Integer node){
        return getNodesReferenced(node, succesors.getOrigin(), succesors.getDestiny());
    }

    public List<Integer> getPredecessors(Integer node){
        return getNodesReferenced(node, predecessors.getDestiny(), predecessors.getOrigin());
    }

    public GraphRepresentation(String path) throws IOException {
            this.succesors = new SuccessorsRepresentationGraph(path);
            this.predecessors = new PredecessorRepresentationGraph(path);
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class NotDirectedGraph {
    private List<List<Integer>> edges;
    private List<List<Integer>> edgesClone;

    public NotDirectedGraph(String path) throws IOException {
        this.edges = GraphsUtils.processFileNotDirected(new BufferedReader(new FileReader(path)));
        edgesClone = new ArrayList<>(edges);
    }

    public List<Integer> getSuccessors(Integer vertice) {
        return edges.get(vertice - 1);
    }

    public Integer getOutDegree(Integer vertice) {
        return getSuccessors(vertice).size();
    }

    private Integer depthSearch(Integer time, Integer currentVertice,
                                Integer[] tDiscovery, Integer[] tFinish, Integer[] father) {
        time++;
        tDiscovery[currentVertice] = time;
        for (Integer edge : this.edgesClone.get(currentVertice).stream().sorted(Comparator.naturalOrder()).toList()) {
            if (tDiscovery[edge] == 0)
                time = depthSearch(time, edge, tDiscovery, tFinish, father);
        }
        time++;
        tFinish[currentVertice] = time;

        return time;
    }
    
    private void removeEdge(Integer vertice1, Integer vertice2, List<List<Integer>> graph){
        graph.get(vertice1).remove(vertice2);
        graph.get(vertice2).remove(vertice1);
    }
    private void addEdge(Integer vertice1, Integer vertice2, List<List<Integer>> graph){
        graph.get(vertice1).add(vertice2);
        graph.get(vertice2).add(vertice1);
    }

    public Map<Integer, List<Integer>> naive() {
        Map<Integer, List<Integer>> bridges = new HashMap<>();
        Integer amountOfVertices = this.edges.size();

        // Set time array
        Integer[] tDiscovery = new Integer[amountOfVertices];
        Integer[] tFinish = new Integer[amountOfVertices];
        Integer[] father = new Integer[amountOfVertices];

        //Connectivity brute test
        for (int i = 0; i < amountOfVertices; i++) {
            for (Integer e : this.edges.get(i).stream().sorted(Comparator.naturalOrder()).toList()) {
                //Removing edge "i" to "e"
                removeEdge(i, e, this.edgesClone);

                //Time array inicialization
                tDiscovery = Arrays.stream(tDiscovery).map(j -> j = 0).toArray(Integer[]::new);
                tFinish = Arrays.stream(tFinish).map(j -> j = 0).toArray(Integer[]::new);
                father = Arrays.stream(father).map(j -> j = 0).toArray(Integer[]::new);
                Integer time = 0;
                Integer currentVertice = tDiscovery[0];
                time = depthSearch(time, currentVertice, tDiscovery, tFinish, father);

                //Checking unexplored vertices
                if(Arrays.stream(tDiscovery).anyMatch(x -> x==0)){
                    if(!bridges.containsKey(i))
                        bridges.put(i, new ArrayList<>());
                    bridges.get(i).add(e);
                }
                addEdge(i, e, this.edgesClone);
            }
        }
        return bridges;
    }
}

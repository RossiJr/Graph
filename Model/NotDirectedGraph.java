import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NotDirectedGraph {
    private List<List<Integer>> edges;
    private List<List<Integer>> edgesClone;

    // Depth search variables
    private Integer[] tDiscovery;
    private Integer[] tFinish;
    private Integer[] father;

    private List<Integer[]> edgesEurelianTour;

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

    public List<List<Integer>> getConnectedComponents() {
        List<List<Integer>> components = new ArrayList<>();
        tDiscovery = new Integer[this.edges.size()];
        this.tDiscovery = Arrays.stream(this.tDiscovery).map(j -> j = 0).toArray(Integer[]::new);
        int i = 0;
        while (Arrays.stream(this.tDiscovery).anyMatch(x -> x == 0)) {
            components.add(new ArrayList<>());
            Integer root = null;
            for (int j = 0; j < this.edges.size(); j++) {
                if (this.tDiscovery[j] == 0) {
                    root = j;
                    break;
                }
            }
            depthSearchIterative(root);
            for (int j = 0; j < this.edges.size(); j++) {
                if (tDiscovery[j] == 1) {
                    components.get(i).add(j);
                    tDiscovery[j] = -1;
                }
            }
            i++;
        }
        return components;
    }

    public Boolean isConnected() {
        tDiscovery = new Integer[this.edges.size()];
        this.tDiscovery = Arrays.stream(this.tDiscovery).map(j -> j = 0).toArray(Integer[]::new);
        depthSearchIterative(0);
        return Arrays.stream(this.tDiscovery).noneMatch(x -> x == 0);

    }

    private void depthSearchIterative(Integer root) {
        Stack nodes = new Stack();
        getSuccessors(root + 1).stream().sorted(Comparator.naturalOrder()).forEach(nodes::push);
        while (!nodes.isEmpty()) {
            Integer current = (Integer) nodes.pop();
            if (tDiscovery[current] == 0) {
                tDiscovery[current] = 1;
                getSuccessors(current + 1).stream().sorted(Comparator.naturalOrder()).forEach(nodes::push);
            }
        }

    }

    private Integer depthSearchRecursive(Integer time, Integer currentVertice) {
        time++;
        this.tDiscovery[currentVertice] = time;
        for (Integer edge : this.edgesClone.get(currentVertice).stream().sorted(Comparator.naturalOrder()).toList()) {
            if (this.tDiscovery[edge] == 0)
                time = depthSearchRecursive(time, edge);
        }
        time++;
        this.tFinish[currentVertice] = time;

        return time;
    }

    private void removeEdge(Integer vertice1, Integer vertice2) {
        removeEdge(vertice1, vertice2, this.edges);
    }

    private void removeEdge(Integer vertice1, Integer vertice2, List<List<Integer>> graph) {
        graph.get(vertice1).remove(vertice2);
        graph.get(vertice2).remove(vertice1);
    }

    private void addEdge(Integer vertice1, Integer vertice2, List<List<Integer>> graph) {
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
                time = depthSearchRecursive(time, currentVertice);

                //Checking unexplored vertices
                if (Arrays.stream(tDiscovery).anyMatch(x -> x == 0)) {
                    if (!bridges.containsKey(i))
                        bridges.put(i, new ArrayList<>());
                    bridges.get(i).add(e);
                }
                addEdge(i, e, this.edgesClone);
            }
        }
        return bridges;
    }

    public void tarjan() {
        List<List<Integer>> components = getConnectedComponents();

    }

    public List<Integer[]> eurelianTour() {
        edgesEurelianTour = new ArrayList<>();
        Integer u = 0;
        for (int i = 0; i < this.edges.size(); i++) {
            if (getOutDegree(i+1) % 2 == 1) {
                u = i;
                break;
            }
        }
        eurelianUtil(u);
        return this.edgesEurelianTour;
    }

    private void eurelianUtil(Integer u) {
        // Recur for all the vertices adjacent to this
        // vertex
        for (int i = 0; i < getOutDegree(u+1); i++) {
            Integer v = getSuccessors(u+1).get(i);
            // If edge u-v is a valid next edge
            if (isValidNextEdge(u, v)) {
                this.edgesEurelianTour.add(new Integer[]{u+1, v+1});
                // This edge is used so remove it now
                removeEdge(u, v, this.edgesClone);
                eurelianUtil(v);
            }
        }
    }

    private boolean isValidNextEdge(Integer u, Integer v) {
        // The edge u-v is valid in one of the
        // following two cases:

        // 1) If v is the only adjacent vertex of u
        // ie size of adjacent vertex list is 1
        if (getOutDegree(u+1) == 1) {
            return true;
        }

        // 2) If there are multiple adjacents, then
        // u-v is not a bridge Do following steps
        // to check if u-v is a bridge
        // 2.a) count of vertices reachable from u
        boolean[] isVisited = new boolean[this.edges.size()];
        int count1 = dfsCount(u, isVisited);

        // 2.b) Remove edge (u, v) and after removing
        //  the edge, count vertices reachable from u
        removeEdge(u, v);
        isVisited = new boolean[this.edges.size()];
        int count2 = dfsCount(u, isVisited);

        // 2.c) Add the edge back to the graph
        addEdge(u, v, this.edgesClone);
        return count1 <= count2;
    }

    private int dfsCount(Integer v, boolean[] isVisited) {
        // Mark the current node as visited
        isVisited[v] = true;
        int count = 1;
        // Recur for all vertices adjacent to this vertex
        for (int adj : getSuccessors(v+1)) {
            if (!isVisited[adj]) {
                count = count + dfsCount(adj, isVisited);
            }
        }
        return count;
    }
}

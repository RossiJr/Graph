package tp02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GraphRepresentation{
    private List<Edge> graph;
    private Integer[][] graphMatrix;
    private Integer n_nodes; 
    private Integer n_edges;
    private Integer[] discoveryVet;

    public Integer getN_edges() {
        return n_edges;
    }

    public void setN_edges(Integer n_edges) {
        this.n_edges = n_edges;
    }

    public Integer getN_nodes() {
        return n_nodes;
    }

    public void setN_nodes(Integer n_nodes) {
        this.n_nodes = n_nodes;
    }
    
    public List<Edge> getGraph() {
        return graph;
    }

    public void setGraph(List<Edge> graph) {
        this.graph = graph;
    }
    
    public GraphRepresentation(){
        n_edges = 0;
        n_nodes = 0;
        graph = new ArrayList<Edge>();
    }
    
    public GraphRepresentation(String archive){
        readClass(archive);
    }

    public void GraphRepresentationMatrix(){
        for (Edge edge : graph) {
            graphMatrix[edge.getOrigin()-1][edge.getDestiny()-1]=1;
        }
    }

    public void printGraph(){
        for(int i=0; i<n_nodes; i++){
            for(int j=0; j<n_nodes; j++){
                System.out.print(graphMatrix[i][j]+" ");
            }
            System.out.println("");
        }
    }

    // Limpa a linha lida do arquivo
    // Retorna um vetor com 2 valores
    public int[] cleanLine(String linha){
        String orig_destiny[];
        int orig_destiny_int[] = new int[2];
        linha = linha.trim();
        orig_destiny = linha.split(" ");
        orig_destiny_int[0] = Integer.parseInt(orig_destiny[0]);
        orig_destiny_int[1] = Integer.parseInt(orig_destiny[orig_destiny.length-1]);

        return orig_destiny_int;
    }

    // Inicializa os vetores de sucessor/pre e salva valores na última posição dos vetores pointers
    public void inicializarVetores(int[] infos_grafo){
        this.n_nodes = infos_grafo[0];
        discoveryVet = new Integer[n_nodes];
        this.n_edges = infos_grafo[1];

        // Inicialização array list de arestas
        graph = new ArrayList<Edge>(n_edges);
        graphMatrix = new Integer[n_nodes+1][n_nodes+1];

        for (int i=0; i<n_nodes; i++){
            for(int j=0; j<n_nodes; j++){
                graphMatrix[i][j] = 0;
            }
        }

        discoveryVet = Arrays.stream(discoveryVet).map(x -> x=0).toArray(Integer[]::new);
    }

    public void readClass(String fileName){
        String content;
        int infos_grafo[] = new int[2];
        int infos_grafo_aux[] = new int[2];

        try{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);
    
        content = br.readLine(); // Lê as informações iniciais sobre o grafo
        infos_grafo = cleanLine(content);
        inicializarVetores(infos_grafo);

        for (int i=0; content !=null && i<n_edges; i++){
            content = br.readLine();
            infos_grafo_aux = cleanLine(content);
            graph.add(new Edge(infos_grafo_aux[0], infos_grafo_aux[1]));
        }

        br.close();
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        GraphRepresentationMatrix();
    }

    public List<int[]> FindDisjointedPath(int s, int t){
        GFG widthSearch = new GFG();
        return widthSearch.findDisjointPaths(graphMatrix, s, t, n_nodes);
    }
}
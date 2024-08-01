package tp02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class GFG
{
 
// Number of vertices in given graph
private int nodes;

/* Returns true if there is a path from
source 's' to sink 't' in residual graph.
Also fills parent[] to store the path */
public boolean bfs(int rGraph[][], int s,
                   int t, Integer parent[], List<int[]> paths_found, int max_flow)
{
    // Create a visited array and
    // mark all vertices as not visited
    boolean []visited = new boolean[nodes];
 
 
    // Create a queue, enqueue source vertex and
    // mark source vertex as visited
    Queue <Integer> q = new LinkedList<>();
    q.add(s);
    visited[s] = true;
    parent[s] = -1;
 
    // Standard BFS Loop
    while (!q.isEmpty())
    {
        int u = q.peek();
        q.remove();
 
        for (int v = 0; v < nodes; v++)
        {
            if (visited[v] == false && rGraph[u][v] > 0) {
                q.add(v);
                parent[v] = u;
                visited[v] = true;
            }
        }
    }
 
    // If we reached sink in BFS
    // starting from source, then
    // return true, else false

    if(visited[t]){
        paths_found.add(new int[nodes]);
        for(int i=0; i<parent.length; i++){
            paths_found.get(max_flow)[i] = parent[i];
        }
    }

    return (visited[t] == true);
}
 
// Returns the maximum number of edge-disjoint
// paths from s to t. This function is copy of
// fordFulkerson() discussed at http://goo.gl/wtQ4Ks
public List<int[]> findDisjointPaths(Integer graph[][], int s, int t, int nodes)
{
    int u, v;
    List<int[]> paths_found = new ArrayList<>();
 
    // Create a residual graph and fill the
    // residual graph with given capacities
    // in the original graph as residual capacities
    // in residual graph
    
    this.nodes = nodes;

    // Residual graph where rGraph[i][j] indicates
    // residual capacity of edge from i to j (if there
    // is an edge. If rGraph[i][j] is 0, then there is not)
    int [][]rGraph = new int[nodes][nodes];
    for (u = 0; u < nodes; u++)
        for (v = 0; v < nodes; v++)
            rGraph[u][v] = graph[u][v];
    
    // This array is filled by BFS and to store path
    Integer []parent = new Integer[nodes];
 
    int max_flow = 0; // There is no flow initially
 
    // Augment the flow while there is path
    // from source to sink
    while (bfs(rGraph, s, t, parent, paths_found, max_flow))
    {
        // Find minimum residual capacity of the edges
        // along the path filled by BFS. Or we can say
        // find the maximum flow through the path found.
        int path_flow = Integer.MAX_VALUE;

        for (v = t; v != s; v = parent[v])
        {
            u = parent[v];
            
            path_flow = Math.min(path_flow, rGraph[u][v]);
        }
 
        // update residual capacities of the edges and
        // reverse edges along the path
        for (v = t; v != s; v = parent[v])
        {
            u = parent[v];
            rGraph[u][v] -= path_flow;
            rGraph[v][u] += path_flow;
        }
 
        // Add path flow to overall flow
        max_flow += path_flow;
    }

    // Return the overall flow (max_flow is equal to
    // maximum number of edge-disjoint paths)
    return paths_found;
}
}

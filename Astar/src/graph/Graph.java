package graph;

import astar.State;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class Graph {
    private List<State> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }
    
    public Graph(List<State> nodes) {
        this.nodes = nodes;
    }
    
    public void addNode(State n){
        this.nodes.add(n);
    }
}

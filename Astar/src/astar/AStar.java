package astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.Graph;

/**
 *
 * @author David
 */
public class AStar {
	private Graph graph;
	private List<State> opened;
	private List<State> closed;
	
	public AStar() {
		this.reset();
	}
	
	public void reset() {
		this.graph = new Graph();
		this.opened = new ArrayList<>();
		this.closed = new ArrayList<>();
	}
	
	public State run(State initialState) throws Exception{
		State openedState;
		State finalState;
		
		this.opened.add(initialState);
		this.graph.addNode(initialState);
		
		while(true) {
			if(this.opened.isEmpty()) {
				throw new Exception();
			}
			
			openedState = this.opened.get(0);
			this.opened.remove(openedState);
			this.closed.add(this.closed.size(), openedState);
			//System.out.println(openedState);
			
			if(openedState.isFinalState()) {
				finalState = openedState;
				break;
			}
			
			List<State> childs = this.expand(openedState);
			for(State child: childs) {
				if(!this.opened.contains(child) && !this.closed.contains(child)){
                    this.opened.add(child);
                }
			}
			
			Collections.sort(this.opened);
		}
		
		return finalState;
	}
	
	private List<State> expand(State node){
		State definitiveNode;
        List<State> list = new ArrayList<>();

        for(State expandedNode : node.expand()){
            if(this.opened.contains(expandedNode)){
                definitiveNode = this.getNodeInList(expandedNode, this.opened);
                if(node.getFn() > definitiveNode.getFather().getFn())
                	definitiveNode.setFather(node);
                
            } else if(this.closed.contains(expandedNode)){
                definitiveNode = this.getNodeInList(expandedNode, this.closed);
                if(node.getFn() > definitiveNode.getFather().getFn())
                	definitiveNode.setFather(node);
                
            } else{
                definitiveNode = expandedNode;
                definitiveNode.setFather(node);
                this.graph.addNode(definitiveNode);  
            }

            list.add(definitiveNode);  
        }

        return list;
    }
	
    private State getNodeInList(State node, List<State> list){
        for(State lNode : list){
            if(lNode.isSameNode(node)) return lNode;
        }
        return null;
    }
	
	
}

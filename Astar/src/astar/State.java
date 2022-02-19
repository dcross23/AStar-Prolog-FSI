package astar;

import java.util.List;

/**
 *
 * @author David
 */
public abstract class State implements Cloneable, Comparable<State>  {
	 	private State father;

	    public State() {
	        this.father = null;
	    }

	    public double getGn(){
	        return this.calculateGn();
	    }

	    public double getHn(){
	        return this.calculateHn();
	    }

	    public double getFn(){
	        return this.getHn() + this.getGn();
	    }

	    public State getFather() {
	        return father;
	    }

	    public void setFather(State father) {
	        this.father = father;
	    }

	    @Override
	    public boolean equals(Object o){
	        if(!this.getClass().equals(o.getClass()))
	            return false;
	        else
	            return this.isSameNode((State) o);
	    }

	    @Override
	    public abstract int hashCode();

	    @Override
	    public abstract String toString();

	    @Override
	    public abstract State clone();

	    @Override
	    public int compareTo(State s) {
	        if(s == null) return 1;
	        return Double.compare(this.getFn(), s.getFn());
	    }

	    
	    public abstract boolean isSameNode(State node);

	    public abstract boolean isFinalState();

	    public abstract double calculateGn();

	    public abstract double calculateHn();

	    public abstract List<State> expand();
}

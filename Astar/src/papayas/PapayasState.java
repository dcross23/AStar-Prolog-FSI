package papayas;

import astar.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author David
 */
public class PapayasState extends State {

    private Stock stock;
    private Box box;

    public PapayasState(Stock stock){
        this.stock = stock;
        this.box = new Box();
    }
    
    public PapayasState(Box box, Stock stock){
        this.box = box;
        this.stock = stock;
    }

    public PapayasState(Box box, Stock stock, State father){
        this.box = box;
        this.stock = stock;
        super.setFather(father);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.stock);
        hash = 37 * hash + Objects.hashCode(this.box);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PapayasState other = (PapayasState) obj;
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        if (!Objects.equals(this.box, other.box)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------------\n");
        sb.append("Estado{").append("\n");
        sb.append(stock.toString()).append(",\n");
        sb.append("\tBox={\n");
        sb.append(box.toString()).append("\n\n");
        sb.append("\t}\n");
        sb.append("}").append("\n---------------------------------------\n");     
        
        return sb.toString();
    }
  
    @Override
    public State clone() {
        return new PapayasState((Box)box.clone(),(Stock) this.stock.clone(), super.getFather());
    }

    @Override
    public boolean isFinalState() {
        return (this.box.calculateWeight() >= 2 || this.stock.getPapayas().size()==0);
    }

    @Override
    public boolean isSameNode(State node) {
        if (this == node) return true;
        if (!(node instanceof PapayasState)) return false;

        return this.stock.equals(((PapayasState)node).stock);
    }


    
    /**
     * H(n) = min cost for an ideal piece with exact weight to reach 2kg when added to the 
     *        box and 0 days in the stock (piece with less cost)
     *      = 2*(2 - boxWeight) + 0.05*0
     */
    @Override
    public double calculateHn() {   	
        return 2*(2-this.box.calculateWeight()) + 0.05*0;
    }
    
    /**
     * G(n) = cost of the box in that moment
     */
    @Override
    public double calculateGn() {
        return this.box.calculateCost();
    }

    @Override
    public List<State> expand(){
        List<State> statesWithAllExpansions = new ArrayList<>();
        for(Piece p: this.stock.getPapayas()){
            PapayasState newState = (PapayasState) this.clone();
            newState.stock.removePapaya(p);
            newState.box.addPiece(p);
            newState.setFather(null);
                 
            statesWithAllExpansions.add(newState);
        }
        
        return statesWithAllExpansions;
    }

    
    public Stock getStock() {
        return stock;
    }

    public Box getBox() {
        return box;
    } 

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setBox(Box box) {
        this.box = box;
    }
    
    
}

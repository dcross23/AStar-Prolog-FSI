package papayas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author David
 */
public class Box implements Cloneable{

    private List<Piece> papayas;

    public Box() {
        this.papayas = new ArrayList<Piece>();
    }
    
    public Box(List<Piece> papayas) {
        this.papayas = papayas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Box other = (Box) obj;

        if (!Objects.equals(this.papayas, other.papayas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        
        sb.append("\t\tBandeja={").append("\n");
        for(Piece p: this.papayas){
            sb.append("\t\t\t").append(p.toString()).append("\n");
        }
        sb.append("\t\t}").append(" -> ");
        sb.append("Peso: ").append(df.format(this.calculateWeight())).append(" , ");
        sb.append("Coste:").append(df.format(this.calculateCost()));
        return sb.toString();
    }

    @Override
    public Object clone(){
        List<Piece> newPapayas = new ArrayList<>();
        for(Piece p: this.papayas){
            newPapayas.add((Piece)p.clone());
        }
        
        return new Box(newPapayas);
    }
    
    
    public void addPiece(Piece p) {
        this.papayas.add(p);
    }

    public boolean isEmpty(){
        return (this.papayas.size() == 0);
    }

    public List<Piece> getPapayas(){
        return this.papayas;
    }
    
    public double calculateCost(){
        double cost = 0;
        for(Piece p: this.papayas){
        	cost += (p.cost() + 0.1);
        }
        return cost + 0.30;
    }
    
    public double calculateWeight(){
        double weight = 0;
        for(Piece p: this.papayas){
        	weight += p.getWeight();
        }
        return weight;
    }
}

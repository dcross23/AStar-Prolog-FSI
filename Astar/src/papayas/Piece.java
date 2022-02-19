package papayas;

import java.util.Objects;

/**
 *
 * @author David
 */
public class Piece implements Cloneable{
	
    private String id;
    private double weight;
    private double date;

    public Piece(String id, double peso, double fecha) {
        this.id = id;
        this.weight = peso;
        this.date = fecha;
    }

    @Override
    public String toString() {
        return "p(" + id + "," + weight + "," + date + ')';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.date) ^ (Double.doubleToLongBits(this.date) >>> 32));
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
        final Piece other = (Piece) obj;
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.date) != Double.doubleToLongBits(other.date)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public Object clone(){
        return new Piece(this.id,this.weight,this.date);
    }
   
    public String getId() {
        return id;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getDate() {
        return this.date;
    }
    
    public double cost(){
        return (2*this.weight + 0.05*this.date);
    }
    
    
}

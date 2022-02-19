package papayas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author David
 */
public class Stock implements Cloneable{
	
    private List<Piece> papayas;

    public Stock() {
        this.papayas = new ArrayList<>();
    }
    
    public Stock(List<Piece> papayas) {
        this.papayas = papayas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.papayas);
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
        final Stock other = (Stock) obj;
        if (!Objects.equals(this.papayas, other.papayas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tAlmacen={").append("\n");
        for(Piece p: this.papayas){
            sb.append("\t\t").append(p.toString()).append("\n");
        }
        sb.append("\t}");
        return sb.toString();
    }

    @Override
    public Object clone(){
        List<Piece> newPapayas = new ArrayList<Piece>();
        for(Piece p: this.papayas){
            newPapayas.add((Piece) p.clone());
        }
        return new Stock(newPapayas);
    }

    public List<Piece> getPapayas() {
        return papayas;
    }
    
    public void removePapaya(Piece p){
        this.papayas.remove(p);
    }
}

package main;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import astar.AStar;
import astar.State;
import papayas.Box;
import papayas.PapayasState;
import papayas.Piece;
import papayas.Stock;

/**
 *
 * @author David
 */
public class Main {

	public static void main(String[] args) {
		Stock stock = readStock();
		State initial = new PapayasState(stock);
		
		List<Box> boxes = new ArrayList<>();
		
		try {
			State finalState;
			AStar astar = new AStar();
			
			do {
				astar.reset();
				finalState = astar.run(initial);	
				PapayasState finalSt = ((PapayasState) finalState);
				boxes.add(finalSt.getBox());
				initial = new PapayasState(finalSt.getStock());
				
			}while(!((PapayasState) finalState).getStock().getPapayas().isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<boxes.size(); i++) {
			if(boxes.get(i).calculateWeight() > 2)
				System.out.println("Bandeja "+i+" -> Peso "+boxes.get(i).calculateWeight()+" -> Coste "+boxes.get(i).calculateCost());
			else
				System.out.println("Resto -> Peso "+boxes.get(i).calculateWeight()+" -> Coste "+boxes.get(i).calculateCost());
			
			for(Piece p: boxes.get(i).getPapayas()) {
				System.out.println(p);
			}	
			System.out.println("\n");
		}
		
	}
	
	private static Stock readStock() {
		JSONParser jparser = new JSONParser();

		try(FileReader reader = new FileReader("stock.json")){
			Object obj = jparser.parse(reader);
			JSONArray readedStock = (JSONArray) obj;
			List<Piece> readedPapayas = new ArrayList<>();
			
			for(int i=0; i<readedStock.size(); i++) {
				JSONObject jobj = (JSONObject) readedStock.get(i);
				Piece newPiece = new Piece((String) jobj.get("id"), 
						                   ((Number) jobj.get("peso")).doubleValue(), 
						                   ((Number) jobj.get("fecha")).doubleValue());
				readedPapayas.add(newPiece);
			}
			
			return new Stock(readedPapayas);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ConnectFourBoard extends GridPane {
	
	private int[] columnHeightKeeper = {5, 5, 5, 5, 5, 5, 5};
	private ArrayList<Node> yellowCoordinateSet = new ArrayList<Node>();
	private ArrayList<Node> redCoordinateSet = new ArrayList<Node>();

	public ConnectFourBoard() {
		
		super();
		this.setStyle("-fx-background-color: rgb(9, 132, 227);");
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setPadding(new Insets(10));
		this.setVgap(25);
		this.setHgap(25);
		this.fillBoard();
		//this.assignCoordinates();
		
	}
	
	private void fillBoard() {
		
		 for(int i = 0; i < 6; i++)
			 for(int j = 0; j < 7; j++)
				 this.add(new ConnectFourDisc(), j, i);
			 
		
	}
	
	public void incrementColumnHeight(int column) {
		
		this.columnHeightKeeper[column]--;
		
	}
	
	public int getCurrentColumnHeight(int columnNumber) {
		
		return this.columnHeightKeeper[columnNumber];
		
	}
	
	
	public boolean gradeYellow() {	
		
		int matchCount = 0;
		
		for(Node currentNode: this.yellowCoordinateSet) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Vertically Upward:
			
			for(int j = startingY + 1; j <= (startingY + 3); j++) {
				
				for(int k = 0; k < this.yellowCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.yellowCoordinateSet.get(k))) == startingX && ConnectFourBoard.getRowIndex((this.yellowCoordinateSet.get(k))) == j)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;

			
			// Horizontal Right: 
			
			for(int j = startingX + 1; j <= (startingX + 3); j++) {
				
				for(int k = 0; k < this.yellowCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.yellowCoordinateSet.get(k))) == j && ConnectFourBoard.getRowIndex((this.yellowCoordinateSet.get(k))) == startingY)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;
					
			// Across Upper Left: 
			
			for(int j = startingX - 1, l = startingY - 1; j >= (startingX - 3); j--, l--) {
				
				for(int k = 0; k < this.yellowCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.yellowCoordinateSet.get(k))) == j && ConnectFourBoard.getRowIndex((this.yellowCoordinateSet.get(k))) == l)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;
				
			// Across Upper Right: 
			
			for(int j = startingX + 1, l = startingY - 1; j <= (startingX + 3); j++, l--) {
				
				for(int k = 0; k < this.yellowCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.yellowCoordinateSet.get(k))) == j && ConnectFourBoard.getRowIndex((this.yellowCoordinateSet.get(k))) == l)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;
					
		} 
		
		return false;
		
	}
	
	public boolean gradeRed() {	
		
		int matchCount = 0;
		
		for(Node currentNode: this.redCoordinateSet) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Vertically Upward:
			
			for(int j = startingY + 1; j <= (startingY + 3); j++) {
				
				for(int k = 0; k < this.redCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.redCoordinateSet.get(k))) == startingX && ConnectFourBoard.getRowIndex((this.redCoordinateSet.get(k))) == j)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;

			
			// Horizontal Right: 
			
			for(int j = startingX + 1; j <= (startingX + 3); j++) {
				
				for(int k = 0; k < this.redCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.redCoordinateSet.get(k))) == j && ConnectFourBoard.getRowIndex((this.redCoordinateSet.get(k))) == startingY)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;
					
			// Across Upper Left: 
			
			for(int j = startingX - 1, l = startingY - 1; j >= (startingX - 3); j--, l--) {
				
				for(int k = 0; k < this.redCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.redCoordinateSet.get(k))) == j && ConnectFourBoard.getRowIndex((this.redCoordinateSet.get(k))) == l)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;
				
			// Across Upper Right: 
			
			for(int j = startingX + 1, l = startingY - 1; j <= (startingX + 3); j++, l--) {
				
				for(int k = 0; k < this.redCoordinateSet.size(); k++)
					if(ConnectFourBoard.getColumnIndex((this.redCoordinateSet.get(k))) == j && ConnectFourBoard.getRowIndex((this.redCoordinateSet.get(k))) == l)
						matchCount++;
				
			}
			
			// Reset Match Counter: 
			
			if(matchCount == 3)
				return true;
			else
				matchCount = 0;
					
		} 
		
		return false;
		
	}
	
	public void addYellowCoordinates(Node yellowDisc) {
		
		this.yellowCoordinateSet.add(yellowDisc);
		
	}
	
	public void addRedCoordinates(Node redDisc) {
		
		this.redCoordinateSet.add(redDisc);
		
	}
	
	public ArrayList<Node> getYellowCoordinateSet() {
		
		return this.yellowCoordinateSet;
		
	}
	
	public ArrayList<Node> getRedCoordinateSet() {
		
		return this.redCoordinateSet;
		
	}
	
	public void printIndex() {
		
		for(Node n: this.getChildren()) {
			 
			 ConnectFourDisc d = (ConnectFourDisc) n;
			 
			 System.out.println("(" + ConnectFourBoard.getRowIndex(d) + ", " + ConnectFourBoard.getColumnIndex(d) + ")");
			 
		 }
		
	}
	
	public boolean isCompletelyFilled() {
		
		
		return this.redCoordinateSet.size() + this.yellowCoordinateSet.size() == 42;
		
	}
	
	public int[] getColumnHeightKeeper() {
		
		return this.columnHeightKeeper;
		
	}
	
}

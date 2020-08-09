import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ConnectFourAI {
	
	private ConnectFourBoard gameBoard;
	private ArrayList<Node> availablePositions = new ArrayList<Node>();
	private ArrayList<Node> opponentPositions = new ArrayList<Node>();
	
	public ConnectFourAI(ConnectFourBoard gameBoard) {
		
		this.gameBoard = gameBoard;
		
	}
	
	public void updateOpponentPositions(Node newPosition) {
		
		this.opponentPositions.add(newPosition);
		
	}
	
	public void adjustOpoponentPositions() {
		
		this.opponentPositions.clear();
		
		for(Node n: this.gameBoard.getRedCoordinateSet())
			this.opponentPositions.add(n);
		
	}
	
	public void place() {
		
		this.setAvailablePositions();
		
		// Check Vertical Risk:
		
		if(this.verticalRisk() != null) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.verticalRisk();
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		} else if(this.horizontalRightRisk() != null) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.horizontalRightRisk();
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		} else if(this.horizontalLeftRisk() != null) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.horizontalLeftRisk();
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		} else if(this.across1Risk() != null) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.across1Risk();
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		} else if(this.across2Risk() != null) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.across2Risk();
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		} else if(this.across3Risk() != null) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.across3Risk();
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		} else if(this.across4Risk() != null) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.across4Risk();
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		} else {
			
			Random rand = new Random();
			int num = rand.nextInt(6);
			
			while(this.gameBoard.getColumnHeightKeeper()[num] < 0)
				num = rand.nextInt(6);
				
			ConnectFourDisc currentDisc = (ConnectFourDisc) this.getNode(num, this.gameBoard.getColumnHeightKeeper()[num]);
			
			gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc)); // NullPointerException
			
			currentDisc.setIsEmpty(false);
			currentDisc.setFill(Color.GOLD);
			
			gameBoard.addYellowCoordinates(currentDisc);
			
		}
		
	}
	
	private Node verticalRisk() {
		
		int matchCount = 0;
		Node[] verticalPredictions = new Node[3];
		
		for(Node currentNode: this.opponentPositions) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Vertically Upward:
			
			for(int j = startingY - 1, l = 0; j >= (startingY - 3); j--, l++) {
				
				for(int k = 0; k < this.opponentPositions.size(); k++) {
					
					if(ConnectFourBoard.getColumnIndex((this.opponentPositions.get(k))) == startingX && ConnectFourBoard.getRowIndex((this.opponentPositions.get(k))) == j) {
						
						matchCount++;
						verticalPredictions[l] = this.opponentPositions.get(k);
						
						
					} else {
						
						
						verticalPredictions[l] = this.getNode(startingX, j);
					
						
					}
					
				}
				
				
			} 
			
			if(matchCount == 2 && verticalPredictions[2] != null) {
				
				ConnectFourDisc disc = (ConnectFourDisc) verticalPredictions[2];
				
				if(disc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(disc)) == ConnectFourBoard.getRowIndex(disc))
					return verticalPredictions[2];
				
			} 
			matchCount = 0;
			verticalPredictions = new Node[3];
			
			
			
		}
		
		return null;

	}
	
	private Node horizontalRightRisk() {
		
		int matchCount = 0;
		Node[] horizontalPredictions = new Node[3];
		
		for(Node currentNode: this.opponentPositions) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Horizontally:
			
			for(int j = startingX + 1, l = 0; j <= (startingX + 3); j++, l++) {
				
				for(int k = 0; k < this.opponentPositions.size(); k++) {
					
					if(ConnectFourBoard.getColumnIndex((this.opponentPositions.get(k))) == j && ConnectFourBoard.getRowIndex((this.opponentPositions.get(k))) == startingY) {
						
						matchCount++;
						horizontalPredictions[l] = this.opponentPositions.get(k);
						
						
					} else {
						
						
						horizontalPredictions[l] = this.getNode(j, startingY);
					
						
					}
					
				}
				
				
			} 
			
			if(matchCount == 2 && horizontalPredictions[2] != null) {
				
				ConnectFourDisc disc = (ConnectFourDisc) horizontalPredictions[2];
				
				if(disc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(disc)) == ConnectFourBoard.getRowIndex(disc))
					return horizontalPredictions[2];
				
			} 
			
			matchCount = 0;
			horizontalPredictions = new Node[3];
			
			
			
		}
		
		return null;

	}
	
	private Node horizontalLeftRisk() {
		
		int matchCount = 0;
		Node[] horizontalPredictions = new Node[3];
		
		for(Node currentNode: this.opponentPositions) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Horizontally:
			
			for(int j = startingX - 1, l = 0; j >= (startingX - 3); j--, l++) {
				
				for(int k = 0; k < this.opponentPositions.size(); k++) {
					
					if(ConnectFourBoard.getColumnIndex((this.opponentPositions.get(k))) == j && ConnectFourBoard.getRowIndex((this.opponentPositions.get(k))) == startingY) {
						
						matchCount++;
						horizontalPredictions[l] = this.opponentPositions.get(k);
						
						
					} else {
						
						
						horizontalPredictions[l] = this.getNode(j, startingY);
					
						
					}
					
				}
				
				
			} 
			
			if(matchCount == 2 && horizontalPredictions[2] != null) {
				
				ConnectFourDisc disc = (ConnectFourDisc) horizontalPredictions[2];
				
				if(disc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(disc)) == ConnectFourBoard.getRowIndex(disc))
					return horizontalPredictions[2];
				
			} 
			
			matchCount = 0;
			horizontalPredictions = new Node[3];
			
			
			
		}
		
		return null;

	}
	
	private Node across1Risk() {
		
		int matchCount = 0;
		Node[] acrossPredictions = new Node[3];
		
		for(Node currentNode: this.opponentPositions) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Horizontally:
			
			for(int j = startingX + 1, m = startingY - 1, l = 0; j <= (startingX + 3); j++, m--, l++) {
				
				for(int k = 0; k < this.opponentPositions.size(); k++) {
					
					if(ConnectFourBoard.getColumnIndex((this.opponentPositions.get(k))) == j && ConnectFourBoard.getRowIndex((this.opponentPositions.get(k))) == m) {
						
						matchCount++;
						acrossPredictions[l] = this.opponentPositions.get(k);
						
						
					} else {
						
						
						acrossPredictions[l] = this.getNode(j, m);
					
						
					}
					
				}
				
				
			} 
			
			if(matchCount == 2 && acrossPredictions[2] != null) {
				
				ConnectFourDisc disc = (ConnectFourDisc) acrossPredictions[2];
				
				if(disc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(disc)) == ConnectFourBoard.getRowIndex(disc))
					return acrossPredictions[2];
				
			} 
			
			matchCount = 0;
			acrossPredictions = new Node[3];
			
			
			
		}
		
		return null;

	}
	
	private Node across2Risk() {
		
		int matchCount = 0;
		Node[] acrossPredictions = new Node[3];
		
		for(Node currentNode: this.opponentPositions) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Horizontally:
			
			for(int j = startingX - 1, m = startingY - 1, l = 0; j >= (startingX - 3); j--, m--, l++) {
				
				for(int k = 0; k < this.opponentPositions.size(); k++) {
					
					if(ConnectFourBoard.getColumnIndex((this.opponentPositions.get(k))) == j && ConnectFourBoard.getRowIndex((this.opponentPositions.get(k))) == m) {
						
						matchCount++;
						acrossPredictions[l] = this.opponentPositions.get(k);
						
						
					} else {
						
						
						acrossPredictions[l] = this.getNode(j, m);
					
						
					}
					
				}
				
				
			} 
			
			if(matchCount == 2 && acrossPredictions[2] != null) {
				
				ConnectFourDisc disc = (ConnectFourDisc) acrossPredictions[2];
				
				if(disc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(disc)) == ConnectFourBoard.getRowIndex(disc))
					return acrossPredictions[2];
				
			} 
			
			matchCount = 0;
			acrossPredictions = new Node[3];
			
			
			
		}
		
		return null;

	}
	
	private Node across3Risk() {
		
		int matchCount = 0;
		Node[] acrossPredictions = new Node[3];
		
		for(Node currentNode: this.opponentPositions) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Horizontally:
			
			for(int j = startingX + 1, m = startingY + 1, l = 0; j <= (startingX + 3); j++, m++, l++) {
				
				for(int k = 0; k < this.opponentPositions.size(); k++) {
					
					if(ConnectFourBoard.getColumnIndex((this.opponentPositions.get(k))) == j && ConnectFourBoard.getRowIndex((this.opponentPositions.get(k))) == m) {
						
						matchCount++;
						acrossPredictions[l] = this.opponentPositions.get(k);
						
						
					} else {
						
						
						acrossPredictions[l] = this.getNode(j, m);
					
						
					}
					
				}
				
				
			} 
			
			if(matchCount == 2 && acrossPredictions[2] != null) {
				
				ConnectFourDisc disc = (ConnectFourDisc) acrossPredictions[2];
				
				if(disc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(disc)) == ConnectFourBoard.getRowIndex(disc))
					return acrossPredictions[2];
				
			} 
			
			matchCount = 0;
			acrossPredictions = new Node[3];
			
			
			
		}
		
		return null;

	}
	
	private Node across4Risk() {
		
		int matchCount = 0;
		Node[] acrossPredictions = new Node[3];
		
		for(Node currentNode: this.opponentPositions) {
			 
			int startingX = ConnectFourBoard.getColumnIndex(currentNode);
			int startingY = ConnectFourBoard.getRowIndex(currentNode);
			
			// Horizontally:
			
			for(int j = startingX - 1, m = startingY + 1, l = 0; j >= (startingX - 3); j--, m++, l++) {
				
				for(int k = 0; k < this.opponentPositions.size(); k++) {
					
					if(ConnectFourBoard.getColumnIndex((this.opponentPositions.get(k))) == j && ConnectFourBoard.getRowIndex((this.opponentPositions.get(k))) == m) {
						
						matchCount++;
						acrossPredictions[l] = this.opponentPositions.get(k);
						
						
					} else {
						
						
						acrossPredictions[l] = this.getNode(j, m);
					
						
					}
					
				}
				
				
			} 
			
			if(matchCount == 2 && acrossPredictions[2] != null) {
				
				ConnectFourDisc disc = (ConnectFourDisc) acrossPredictions[2];
				
				if(disc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(disc)) == ConnectFourBoard.getRowIndex(disc))
					return acrossPredictions[2];
				
			} 
			
			matchCount = 0;
			acrossPredictions = new Node[3];
			
			
			
		}
		
		return null;

	}
	
	private Node getNode(int row, int column) {
		
		for(Node n: this.gameBoard.getChildren())
			if(ConnectFourBoard.getColumnIndex(n) == row && ConnectFourBoard.getRowIndex(n) == column)
				return n;
		return null;
		
	}
	
	private void setAvailablePositions() {
		
		this.availablePositions.clear();
		
		for(Node n: this.gameBoard.getChildren()) {
			
			for(int i = 0; i < 7; i++)
				if(ConnectFourBoard.getColumnIndex(n) == i && ConnectFourBoard.getRowIndex(n) == this.gameBoard.getColumnHeightKeeper()[i])
					this.availablePositions.add(n);
			
		}
		
	}

}

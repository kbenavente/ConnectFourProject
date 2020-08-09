import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ComputerVsPlayerGUI implements EventHandler<ActionEvent>{
	
	private Stage primaryStage;
	private Scene previousScene;
	private String player1Name = "Player 1";
	
	public ComputerVsPlayerGUI(Scene previousScene, Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
		
	}

	@Override
	public void handle(ActionEvent a) {
		
		VBox container = new VBox(40);
		container.setPadding(new Insets(20));
		container.setStyle("-fx-background-color: rgb(9, 132, 227);");
		
		Button btBack = new Button("<-- Back");
		
		Label instructions = new Label("Please enter the names for the Player (not required):");
		
		GridPane infoContainer = new GridPane();
		infoContainer.setAlignment(Pos.BASELINE_CENTER);
		infoContainer.setVgap(30);
		infoContainer.setHgap(30);
		
		Label lbName1 = new Label("Player 1 Name:");
		TextField tfName1 = new TextField();
		tfName1.setPromptText("Name");
		
		
		Button btSubmit = new Button("Submit");
		
		infoContainer.add(lbName1, 0, 0);
		infoContainer.add(tfName1, 1, 0);
		infoContainer.add(btSubmit, 0, 1);
		
		container.getChildren().add(btBack);
		container.getChildren().add(instructions);
		container.getChildren().add(infoContainer);
		
		Scene playerVsPlayerNames = new Scene(container, 800, 720);
		playerVsPlayerNames.getStylesheets().clear();
		playerVsPlayerNames.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		
		primaryStage.setScene(playerVsPlayerNames);
		primaryStage.setTitle("Connect Four - Computer Vs. Player");
		
		// Event Handlers:
		
		btBack.setOnAction(e -> {
			
			primaryStage.setScene(previousScene);
			
		});
		
		btSubmit.setOnAction(e -> {
			
			
				
				ConnectFourDisc.resetTurnID();
				
				this.setPlayerName(tfName1.getText());
				
				VBox gameBoardContainer = new VBox(10);
				gameBoardContainer.setPadding(new Insets(20));
				gameBoardContainer.setStyle("-fx-background-color: rgb(9, 132, 227);");
				
				Button btBackToMenu = new Button("Main Menu");
				
				HBox headerContainer = new HBox(50);
				headerContainer.setAlignment(Pos.BASELINE_RIGHT);
				ConnectFourDisc currentPlayerDisc = new ConnectFourDisc(Color.RED, 20);
				Label lbCurrentTurn = new Label("Current Turn:");
				Label lbCurrentPlayer = new Label(player1Name);
				
				
				
				headerContainer.getChildren().add(lbCurrentTurn);
				headerContainer.getChildren().add(currentPlayerDisc);
				headerContainer.getChildren().add(lbCurrentPlayer);
				
				ConnectFourDisc discSample = new ConnectFourDisc(Color.RED);
				
				ConnectFourBoard gameBoard = new ConnectFourBoard();
				
				gameBoardContainer.getChildren().add(btBackToMenu);
				gameBoardContainer.getChildren().add(headerContainer);
				
				gameBoardContainer.getChildren().add(discSample);
				gameBoardContainer.getChildren().add(gameBoard);
				
				ConnectFourAI ai = new ConnectFourAI(gameBoard);
				
				Scene playerVsPlayerScene = new Scene(gameBoardContainer, 800, 720);
				playerVsPlayerScene.getStylesheets().clear();
				playerVsPlayerScene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
				
				primaryStage.setScene(playerVsPlayerScene);
				
				// Event Handlers:
				
				btBackToMenu.setOnAction(f -> {
					
					primaryStage.setScene(previousScene);
					
				});
				
				gameBoard.setOnMouseMoved(f -> {
					
					discSample.setTranslateX(f.getX() - 22);
		
				});
				
				gameBoard.getChildren().forEach(item -> {
					
					item.setOnMouseClicked(f -> {
						
					ConnectFourDisc currentDisc = (ConnectFourDisc) item;
					
					if(!currentDisc.isEmpty() && (ConnectFourBoard.getRowIndex(currentDisc) == 5)) {
						
						Paint[] validPaints = new Paint[6];
						Node[] nodes = new Node[6];
						int validIndex = 0;
						
						for(Node n: gameBoard.getChildren()) {
							
							ConnectFourDisc popDisc = (ConnectFourDisc) n;
							
							// Check if the Node is in the same column as the clicked ConnectFourDisc
							if(ConnectFourBoard.getColumnIndex(popDisc) == ConnectFourBoard.getColumnIndex(currentDisc)) {
							
								validPaints[validIndex] = popDisc.getFill();
								nodes[validIndex] = n;
								validIndex++;
								
							}
							
						}
						
						this.popDisc(validPaints, nodes, gameBoard, ConnectFourBoard.getColumnIndex(currentDisc));
						gameBoard.getColumnHeightKeeper()[ConnectFourBoard.getColumnIndex(currentDisc)]++;
							
						ai.adjustOpoponentPositions();
						
						// Player 1 will always be RED.
						
						lbCurrentPlayer.setText(player1Name);
						
						if(gameBoard.gradeRed()) {
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("ConnectFour - Victory!");
							alert.setHeaderText(null);
							alert.setContentText(player1Name + " has won!");
							alert.showAndWait();
							player1Name = "Player 1";
							primaryStage.setScene(previousScene);
							
						}
						
						ai.place();
						if(gameBoard.gradeYellow()) {
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("ConnectFour - Victory!");
							alert.setHeaderText(null);
							alert.setContentText("Computer has won!");
							alert.showAndWait();
						
							primaryStage.setScene(previousScene);
							
						}
									
							
						
					} else if(currentDisc.isEmpty() && gameBoard.getCurrentColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc)) == ConnectFourBoard.getRowIndex(currentDisc)) {
						
						currentDisc.setIsEmpty(false);
						ai.updateOpponentPositions(item);
						
						gameBoard.incrementColumnHeight(ConnectFourBoard.getColumnIndex(currentDisc));
						
						// Player 1 will always be RED.
						
						lbCurrentPlayer.setText(player1Name);
						
						currentDisc.setFill(ConnectFourDisc.getCurrentColor());
						gameBoard.addRedCoordinates(item);
						
						if(gameBoard.gradeRed()) {
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("ConnectFour - Victory!");
							alert.setHeaderText(null);
							alert.setContentText(player1Name + " has won!");
							alert.showAndWait();
							player1Name = "Player 1";
							primaryStage.setScene(previousScene);
							
						}
						
						ai.place();
						if(gameBoard.gradeYellow()) {
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("ConnectFour - Victory!");
							alert.setHeaderText(null);
							alert.setContentText("Computer has won!");
							alert.showAndWait();
						
							primaryStage.setScene(previousScene);
							
						}
																		
					}	
					
					if(gameBoard.isCompletelyFilled()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("ConnectFour - Draw!");
						alert.setHeaderText(null);
						alert.setContentText("Game has ended in a draw!");
						alert.showAndWait();
						player1Name = "Player 1";
						primaryStage.setScene(previousScene);
						
					}	
					
				});
				
			});
			
		});
		
	}
		
	private void setPlayerName(String player1) {
		
		if(!player1.isEmpty())
			this.player1Name = player1;
		
	}
	
	
	private void popDisc(Paint[] paints, Node[] nodes, ConnectFourBoard gameBoard, int column) {
		
		//Remove Coordinates
		for(Node n: nodes) {
			
			ConnectFourDisc currentDisc = (ConnectFourDisc) n;
			
			if(currentDisc.getFill().equals(Color.RED)) 
				gameBoard.getRedCoordinateSet().remove(n);
			else if(currentDisc.getFill().equals(Color.GOLD))
				gameBoard.getYellowCoordinateSet().remove(n);
			
		}
		
		// Shift node array 1 position to the right
		for(int i = paints.length - 1; i > 0; i--) 
			paints[i] = paints[i - 1];
		
		// Check if first color is white
		if(!paints[0].equals(Color.WHITE)) {
			
			paints[0] = Color.WHITE;
			
		}	
			
		// Put new shifted node array into the game board and assign coordinates respectively 
		int paintIndex = 0;
		
		for(Node n: gameBoard.getChildren()) {
			
			ConnectFourDisc disc = (ConnectFourDisc) n;
			
			if(ConnectFourBoard.getColumnIndex(disc) == column) {
				
				if(paints[paintIndex].equals(Color.RED)) {
					
					disc.setFill(paints[paintIndex]);
					disc.setIsEmpty(false);
					gameBoard.addRedCoordinates(n);
					paintIndex++;
					
				} else if(paints[paintIndex].equals(Color.GOLD)) {
					
					disc.setIsEmpty(false);
					disc.setFill(paints[paintIndex]);
					gameBoard.addYellowCoordinates(n);
					paintIndex++;
					
				} else {
					
					disc.setIsEmpty(true);
					disc.setFill(paints[paintIndex]);
					paintIndex++;
					
				}
				
			}
			
		}
		
	}
	
}

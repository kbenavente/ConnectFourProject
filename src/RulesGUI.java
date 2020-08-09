import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RulesGUI implements EventHandler<ActionEvent> {

	private Stage primaryStage;
	private Scene previousScene;
	
	public RulesGUI(Scene previousScene, Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
		
	}
	
	@Override
	public void handle(ActionEvent a) {
		
		VBox container = new VBox(20);
		container.setPadding(new Insets(20));
		container.setStyle("-fx-background-color: rgb(9, 132, 227);");
		
		Button btBack = new Button("<-- Back");
		
		Label instructions1 = new Label("General Rules:");
		instructions1.setMinSize(20, 20);
		Label instructions2 = new Label("\t* To win Connect Four you must be the first player to get four of your colored checkers in a row \n\teither horizontally, vertically or diagonally.");
		Label instructions3 = new Label("\t* You are able to pop any color disc that is located on the last row. Popping out a disc will count as a turn.");
		
		Label instructions4 = new Label("Game Modes:");
		instructions4.setMinSize(20, 20);
		Label instructions5 = new Label("\t* Computer Vs. Player Mode allows for one player to play against the computer!	All general rules still ");
		Label instructions5A = new Label("\tapply. Note that the Computer will not get tired therefore will keep adding a disc where it thinks it");
		Label instructions5B = new Label("\tmust go. Sometimes this results in a never ending battle of you popping out a disc and the computer placing a disc there.");
		Label instructions6 = new Label("\t* Player Vs. Player Mode allows you and another person to face off in an epic game of Connect Four. \n\tAll general rules apply.");
		
		Label instructions7 = new Label("Getting Started:");
		instructions7.setMinSize(20, 20);
		Label instructions8 = new Label("\t1) Select a game mode and enter name(s) of the player(s).");
		Label instructions9 = new Label("\t2) Click on 'Submit'. Red player will always go first in all game modes.");
		Label instructions10 = new Label("\t3) Click on the slot where you would like to place your disc. \n\tClick on an already placed disc located on the lower row to pop the disc out and cause the column to shift down one slot. \n\tFirst one to Connect Four wins! Have fun!");
		
		Label instructions11 = new Label("Bugs:");
		instructions7.setMinSize(20, 20);
		Label instructions12 = new Label("\t* In Computer Vs. Player Mode when the non-computer player wins, you will be prompted with an alert stating \n\tthat the non-human player won. The scene is then changed but in some occasions, tha AI\n\t will keep playing as the scene is changing and perhaps win and so another alert box will be under the initial. \n\tThis issue is very rare but nevertheless possible.");
		
		GridPane infoContainer = new GridPane();
		infoContainer.setAlignment(Pos.BASELINE_CENTER);
		infoContainer.setVgap(10);
		infoContainer.setHgap(30);
		
		
		infoContainer.add(instructions1, 0, 0);
		infoContainer.add(instructions2, 0, 1);
		infoContainer.add(instructions3, 0, 2);
		infoContainer.add(instructions4, 0, 3);
		infoContainer.add(instructions5, 0, 4);
		infoContainer.add(instructions5A, 0, 5);
		infoContainer.add(instructions5B, 0, 6);
		infoContainer.add(instructions6, 0, 7);
		infoContainer.add(instructions7, 0, 8);
		infoContainer.add(instructions8, 0, 9);
		infoContainer.add(instructions9, 0, 10);
		infoContainer.add(instructions10, 0, 11);
		infoContainer.add(instructions11, 0, 12);
		infoContainer.add(instructions12, 0, 13);
	
		container.getChildren().add(btBack);
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
		
	}

	
}

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConnectFour extends Application {

	public static void main(String[] args) {

		Application.launch(args);

	}

	public void start(Stage primaryStage) throws Exception {

		VBox container = new VBox();
		container.setStyle("-fx-background-color: rgb(9, 132, 227);");
		container.setAlignment(Pos.BASELINE_CENTER);
		container.setPrefHeight(primaryStage.getHeight());
		container.setPrefWidth(primaryStage.getWidth());

		ImageView logo = new ImageView("file:connectFour-logo.png");
		logo.setFitHeight(230);
		logo.setFitWidth(350);

		GridPane mainMenuOptions = new GridPane();
		mainMenuOptions.setHgap(80);
		mainMenuOptions.setVgap(30);
		mainMenuOptions.setAlignment(Pos.BASELINE_CENTER);

		Button btComputerVsPlayer = new Button("Computer Vs. Player");
		Button btPlayerVsPlayer = new Button("Player Vs. Player");
		Button btRules = new Button("Rules");

		mainMenuOptions.add(btComputerVsPlayer, 0, 0);
		mainMenuOptions.add(btPlayerVsPlayer, 1, 0);
		mainMenuOptions.add(btRules, 0, 1);

		container.getChildren().add(logo);
		container.getChildren().add(mainMenuOptions);

		Scene mainMenuScene = new Scene(container, 800, 720);
		mainMenuScene.getStylesheets().clear();
		mainMenuScene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

		primaryStage.setScene(mainMenuScene);
		primaryStage.setTitle("Connect Four");
		primaryStage.setResizable(false);
		primaryStage.show();

		// Event Handlers:

		btPlayerVsPlayer.setOnAction(new PlayerVsPlayerGUI(mainMenuScene, primaryStage));

		btComputerVsPlayer.setOnAction(new ComputerVsPlayerGUI(mainMenuScene, primaryStage));

		btRules.setOnAction(new RulesGUI(mainMenuScene, primaryStage));

	}

	public Scene playerVsPlayerNamesScene() {

		VBox container = new VBox(20);

		Button btMainMenu = new Button("<- Main Menu");
		btMainMenu.getStyleClass().add("buttonBack");

		return null;

	}

	public Scene playerVsPlayerScene(String player1, String player2) {

		VBox container = new VBox(10);

		ConnectFourBoard gameBoard = new ConnectFourBoard();

		container.getChildren().add(gameBoard);

		return null;

	}

}

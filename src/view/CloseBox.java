package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CloseBox {

	static boolean exit;
	
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		
		// Create buttons
		Button exitButton = new Button("Yes, exit!");
		Button noExitButton = new Button("No, cancel!");
		
		exitButton.setOnAction(e -> {
			exit = true;
			window.close();
		});
		
		noExitButton.setOnAction(e -> {
			exit = false;
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, exitButton, noExitButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return exit;
	}
	
}

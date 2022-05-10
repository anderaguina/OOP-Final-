package view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;

public class TabbedView {
	private TabPane tabPane;
	private TextArea textArea;
	private Tab1 fillTab1;
	private Tab2 fillTab2;
	private Tab3 fillTab3;
	private Tab4 fillTab4;
	private Tab5 fillTab5;
	
	public void startView(Stage primaryStage, ArrayList<Student> students) {		
		
		try {
			
			/*
			 * Create view with all the tabs
			 */
			BorderPane root = new BorderPane();
			
			tabPane = new TabPane();
			
			Tab tab1 = new Tab("Add/Remove/List Students");
			Tab tab2 = new Tab("Register Module");
			Tab tab3 = new Tab("List All modules");
			Tab tab4 = new Tab("Student Details");
			Tab tab5 = new Tab("Update Student");
			
			tab1.setClosable(false);
			tab2.setClosable(false);
			tab3.setClosable(false);
			tab4.setClosable(false);
			tab5.setClosable(false);
			
			// Fill tab 2
			fillTab2 = new Tab2(tab2, students, primaryStage);

						
			// Fill tab 3
			fillTab3 = new Tab3(tab3, students, primaryStage);
			
			// Fill tab 4
			fillTab4 = new Tab4(tab4, students, primaryStage);
			
			// Fill tab 5
			fillTab5 = new Tab5(tab5, students, primaryStage);
			
			// Fill tab 1 (Goes third so I can use fillTab2 ,fillTab3, fillTab4 and fillTab5 to update the checkboxes)
			fillTab1 = new Tab1(tab1, fillTab2, fillTab3, fillTab4, fillTab5, primaryStage);
			
			
			// Add tabs to tab pane
			tabPane.getTabs().add(tab1);
			tabPane.getTabs().add(tab2);
			tabPane.getTabs().add(tab3);
			tabPane.getTabs().add(tab4);
			tabPane.getTabs().add(tab5);
			
			
			// Add tabPane to scene
			root.setCenter(tabPane);
			
			// Create scene and set size
			Scene scene = new Scene(root, 600, 600);
			
			// Get stylesheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Set and show the created scene
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

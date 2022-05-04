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
	
	public void startView(Stage primaryStage, ArrayList<Student> students) {		
		
		try {
			BorderPane root = new BorderPane();
			
			tabPane = new TabPane();
			
			Tab tab1 = new Tab("Tab 1");
			Tab tab2 = new Tab("Tab 2");
			Tab tab3 = new Tab("Tab 3");
			
			tab1.setClosable(false);
			tab2.setClosable(false);
			tab3.setClosable(false);
			
			// Fill tab 2
			fillTab2 = new Tab2(tab2, students, primaryStage);

						
			// Fill tab 3
			fillTab3 = new Tab3(tab3, students, primaryStage);
			
			// Fill tab 1 (Goes third so I can use fillTab2 and fillTab3)
			fillTab1 = new Tab1(tab1, fillTab2, fillTab3, primaryStage);			
			
			
			// Add tabs to tab pane
			tabPane.getTabs().add(tab1);
			tabPane.getTabs().add(tab2);
			tabPane.getTabs().add(tab3);
			
			
			// Add tabPane to scene
			root.setCenter(tabPane);
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

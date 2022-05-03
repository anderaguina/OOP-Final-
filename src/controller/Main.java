package controller;
	
import java.sql.Connection;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Student;
import view.TabbedView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Main extends Application {
	
	private TabbedView tabbedView;
	private Controller controller = new Controller();
	private DBConn derby = new DBConn();
	
	
	@Override
	public void start(Stage primaryStage) {
		
		// Init students list from db
		ArrayList<Student> students = derby.select_students();
			
		// Init view
		tabbedView = new TabbedView();
		tabbedView.startView(primaryStage, students);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

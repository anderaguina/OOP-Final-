package view;

import java.util.ArrayList;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;
import model.StudentModule;

public class Tab3 {
	private Controller controller = new Controller();
	protected ChoiceBox<String> choiceBox = new ChoiceBox<>();
	
	public Tab3(Tab tab3, ArrayList<Student> students, Stage primaryStage) {
		
		// Create vBox
		VBox tab3VBox = new VBox();		
		
		/*
		 * Create table
		 */
		TableView table = new TableView<StudentModule>();
		
		TableColumn nameColumn = new TableColumn<StudentModule, String>("Module Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
		
		TableColumn gradeColumn = new TableColumn<StudentModule, Integer>("Grade");
		gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
		
		table.getColumns().add(nameColumn);
		table.getColumns().add(gradeColumn);
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		
		// Add action button and functionality
		Button requestRecordsButton = new Button("Request Records");
		
		requestRecordsButton.setOnAction(e -> {
			// Get inputs from view
			String studentId = getChoice(choiceBox);
			
			// Get modules from db for given student
            ArrayList<StudentModule> modules = controller.findModulesForUser(studentId, false);
            
            // Render the record results in the view table
            renderRecordsInTable(table, modules);
		});
		
		// Set combobox default value
		if (students.size() > 0) {
			choiceBox.setValue(students.get(0).getId());	
		}
		
		// Fill combobox list
		for (int i = 0; i < students.size(); i++) {
			choiceBox.getItems().add(students.get(i).getId());
		}
		
		// Add elements to vBox
	    tab3VBox.getChildren().addAll(
	    	choiceBox,
	    	requestRecordsButton,
	    	table
	    );
		
		tab3.setContent(tab3VBox);
		
	}
	
	/**
	 * Get choice from combobox
	 * @param choiceBox
	 * @return
	 */
	private String getChoice(ChoiceBox<String> choiceBox) {
		String studentId = choiceBox.getValue();
		return studentId;
	}

	/**
	 * Update comboBox when new student is added
	 */
	public void updateChoiceBox() {
		ArrayList<Student> students = controller.getAllStudents();
		choiceBox.getItems().clear();
		for (int i = 0; i < students.size(); i++) {
			choiceBox.getItems().add(students.get(i).getId());
		}
		if (students.size() > 0) {
			choiceBox.setValue(students.get(0).getId());	
		}
	}
	
	/**
	 * Update content of view table
	 * @param table
	 * @param modules
	 */
	private void renderRecordsInTable(TableView table, ArrayList<StudentModule> modules) {
		table.getItems().clear();
		for (int i = 0; i < modules.size(); i++) {
			table.getItems().add(modules.get(i));
		}
	}
}

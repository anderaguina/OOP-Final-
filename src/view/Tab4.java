package view;

import java.util.ArrayList;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;
import model.StudentModule;

public class Tab4 {
	private Controller controller = new Controller();
	protected ChoiceBox<String> choiceBox = new ChoiceBox<>();
		
	public Tab4(Tab tab4, ArrayList<Student> students, Stage primaryStage) {
		
		// Create vBox
		VBox tab4VBox = new VBox();
		
		// Set default option in combobox
		if (students.size() > 0) {
			choiceBox.setValue(students.get(0).getId());	
		}
		
		// Create comboBox
		CheckBox honorsCheckBox = new CheckBox("First Class Honor");
		
		/*
		 * Create table to display the modules
		 */
		TableView table = new TableView<StudentModule>();
		// TableColumn idColumn = new TableColumn<Student, String>("Id");
		TableColumn nameColumn = new TableColumn<StudentModule, String>("Module Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
				
		TableColumn gradeColumn = new TableColumn<StudentModule, Integer>("Grade");
		gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
				
		table.getColumns().add(nameColumn);
		table.getColumns().add(gradeColumn);
				
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		/*
		 * Create the text fields to display student details
		 */
		Label studentIdLabel = new Label("Student Id:");
		TextField studentIdTextField = new TextField();
		
		Label fNameLabel = new Label("First Name:");
		TextField fNameTextField = new TextField();
		
		Label lNameLabel = new Label("Last Name:");
		TextField lNameTextField = new TextField();
		
		// Select first entry in the dropdown as default option
		for (int i = 0; i < students.size(); i++) {
			choiceBox.getItems().add(students.get(i).getId());
		}
		
		
		/*
		 * Button to request the details and the action to request them and display them
		 */
		Button requestDetailsButton = new Button("Request Student Details");
		
		requestDetailsButton.setOnAction(e -> {
			// Get input from combobox
			String studentId = getChoice(choiceBox);
			
			// Request student details
			Student student = controller.selectStudent(studentId);
			
			// Display details on the text inputs
			studentIdTextField.setText(student.getId());
			fNameTextField.setText(student.getName().getName());
			lNameTextField.setText(student.getName().getLastName());
			
			// Read checkBox
			boolean honors = honorsCheckBox.isSelected();
			
			// Fill the table with the module list
			ArrayList<StudentModule> modules = controller.findModulesForUser(studentId, honors);
            renderRecordsInTable(table, modules);
			
		});
	    
		// Add elements to vBox
	    tab4VBox.getChildren().addAll(
	    	choiceBox,
	    	honorsCheckBox,
	    	requestDetailsButton,
	    	studentIdLabel,
	    	studentIdTextField,
	    	fNameLabel,
	    	fNameTextField,
	    	lNameLabel,
	    	lNameTextField,
	    	table
	    	
	    );
		
		tab4.setContent(tab4VBox);
		
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
}

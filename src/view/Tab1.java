package view;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;

public class Tab1 {
	
	private Controller controller = new Controller();
	protected ChoiceBox<String> choiceBox = new ChoiceBox<>();
	private ArrayList<Student> students = controller.getAllStudents();
	
	// I get tab2, tab3 and tab4 object in order to update the choice box in those classes when
	// a new student is added from this view.
	public Tab1(Tab tab1, Tab2 tab2, Tab3 tab3, Tab4 tab4, Tab5 tab5, Stage primaryStage) {
		
		/*
		 * Section Tittles
		 */
		Label addSection = new Label("Add Student");
		Label removeSection = new Label("Remove Student");
		Label listSection = new Label("List Students");
		
		/*
		 * Text field + labels
		 */
		Label nameLabel = new Label("Name:");
		TextField nameTextField = new TextField();
		
		Label middleILabel = new Label("Middle Name Initial:");
		TextField middleITextField = new TextField();
		
		Label lastNameLabel = new Label("Last Name:");
		TextField lastNameTextField = new TextField();
		
		Label idLabel = new Label("Student id:");
		TextField idTextField = new TextField();
		
		// create a date picker
		Label dobLabel = new Label("Date Of Birth:");
        DatePicker datePicker = new DatePicker();
		
		/*
		 * Set default value for comboBox and fill the list
		 */
		if (students.size() > 0) {
			choiceBox.setValue(students.get(0).getId());	
		}
		
		for (int i = 0; i < students.size(); i++) {
			choiceBox.getItems().add(students.get(i).getId());
		}
		
		
		/*
		 * Create buttons for the actions
		 */
		Button addButton = new Button("Add");
		Button removeButton = new Button("Remove");
		Button refreshListButton = new Button("Refresh");
		Button exitButton = new Button("Exit");
		
		// Create text area to display entries
		TextArea textArea = new TextArea();
		
		// Add events to buttons: ADD
		refreshListButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {			
				
				// Update student list in text area
				updateTextArea(textArea, controller.getAllStudents());
			}
			
		});
				
		// Add events to buttons: ADD
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				// Get inputs from view
				String id = idTextField.getText();
				String firstName = nameTextField.getText();
				String middleI = middleITextField.getText();
				String lastName = lastNameTextField.getText();
				
				// Read datepicker value
				LocalDate localDate = datePicker.getValue();
								
				// Convert from LocalDate to sql Date by adding the timezone
				Date date = Date.valueOf(localDate);
								
				// Add student to db
				controller.addStudent(id, firstName, middleI, lastName, date);
				
				
				// Clear text Fields
				idTextField.clear();
				nameTextField.clear();
				middleITextField.clear();
				lastNameTextField.clear();
				
				
				// Auto update student list in text area when the student is added
				updateTextArea(textArea, controller.getAllStudents());
				
				// Reset choiceBox values in the whole app
				updateChoiceBox();
				tab2.updateChoiceBox();
				tab3.updateChoiceBox();
				tab4.updateChoiceBox();
				tab5.updateChoiceBox();
			}
			
		});
		
		// Add events to buttons: REMOVE
		removeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				// Get inputs from view
				String studentId = getChoice(choiceBox);
				controller.removeStudent(studentId);
				
				// Reset choiceBox values in the whole app
				updateChoiceBox();
				tab2.updateChoiceBox();
				tab3.updateChoiceBox();
				tab4.updateChoiceBox();
				
				// Auto update student list in text area when the student is removed
				updateTextArea(textArea, controller.getAllStudents());
			}
			
		});
		
		// Add events to buttons: EXIT
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				boolean exit = CloseBox.display("Exit", "Are you sure you want to quit?");
				
				if (exit) {
					primaryStage.close();
				}
			}
			
		});
		
		
		// Fill the text area from db
		updateTextArea(textArea, students);
		
		/*
		 * Create vBox and add the children to it
		 */
	    VBox tab1VBox = new VBox();
	    
	    tab1VBox.getChildren().addAll(
    		addSection,
	    	nameLabel,
    		nameTextField,
    		middleILabel,
    		middleITextField,
    		lastNameLabel,
    		lastNameTextField,
    		idLabel,
    		idTextField,
    		dobLabel,
    		datePicker,
    		addButton,
    		removeSection,
    		choiceBox,
    		removeButton,
    		listSection,
    		refreshListButton,
    		textArea,
    		exitButton
	    );
		
		tab1.setContent(tab1VBox);
	}


	/**
	 * Update content of text area
	 * @param textArea
	 * @param students
	 */
	public void updateTextArea(TextArea textArea,  ArrayList<Student> students) {
		StringBuilder fieldContent = new StringBuilder(""); 
		for (int i = 0; i < students.size(); i++) {
			fieldContent.append("Index: " + i + " | Student id: " + students.get(i).getId() + " | Student Name: "
				+ students.get(i).getName().getName() + " | DOB: " + students.get(i).getDob() + "\n");
		}
		textArea.setText(fieldContent.toString());
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
	
}

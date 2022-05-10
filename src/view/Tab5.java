package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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

public class Tab5 {
	private Controller controller = new Controller();
	protected ChoiceBox<String> choiceBox = new ChoiceBox<>();
		
	public Tab5(Tab tab5, ArrayList<Student> students, Stage primaryStage) {
		
		// Create vBox
		VBox tab4VBox = new VBox();
		
		// Set default option in combobox
		if (students.size() > 0) {
			choiceBox.setValue(students.get(0).getId());	
		}
				
		/*
		 * Create the text fields to display student details
		 */
		Label studentIdLabel = new Label("Student Id:");
		TextField studentIdTextField = new TextField();;
		studentIdTextField.setDisable(true);
		
		Label fNameLabel = new Label("First Name:");
		TextField fNameTextField = new TextField();
		
		Label lNameLabel = new Label("Last Name:");
		TextField lNameTextField = new TextField();
		
		Label mNameLabel = new Label("Middle Name Initial:");
		TextField mNameTextField = new TextField();
		
		// create a date picker
		Label dobLabel = new Label("Date Of Birth:");
        DatePicker datePicker = new DatePicker();
		
		// Select first entry in the dropdown as default option
		for (int i = 0; i < students.size(); i++) {
			choiceBox.getItems().add(students.get(i).getId());
		}
		
		
		/*
		 * Button to request the details and the action to request them and display them
		 */
		Button searchButton = new Button("Search");
		
		searchButton.setOnAction(e -> {
			// Get input from combobox
			String studentId = getChoice(choiceBox);
			
			// Request student details
			Student student = controller.selectStudent(studentId);
			
			// Display details on the text inputs
			studentIdTextField.setText(student.getId());
			fNameTextField.setText(student.getName().getName());
			lNameTextField.setText(student.getName().getLastName());
			mNameTextField.setText(student.getName().getMiddleI());	
			datePicker.setValue(student.getDob().toLocalDate());
		});
		
		/*
		 * Button to trigger update
		 */
		Button updateStudentButton = new Button("Update");
		
		// Add events to buttons: ADD
		updateStudentButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				// Get inputs from view
				String id = studentIdTextField.getText();
				String firstName = fNameTextField.getText();
				String middleI = mNameTextField.getText();
				String lastName = lNameTextField.getText();
				
				// Read datepicker value
				LocalDate localDate = datePicker.getValue();
								
				// Convert from LocalDate to sql Date by adding the timezone
				Date date = Date.valueOf(localDate);
								
				// Update student call
				controller.updateStudent(id, firstName, middleI, lastName, date);
				
				
				// Clear text Fields
				studentIdTextField.clear();
				fNameTextField.clear();
				mNameTextField.clear();
				lNameTextField.clear();
			}
			
		});
	    
		// Add elements to vBox
	    tab4VBox.getChildren().addAll(
	    	choiceBox,
	    	searchButton,
	    	studentIdLabel,
	    	studentIdTextField,
	    	fNameLabel,
	    	fNameTextField,
	    	mNameLabel,
	    	mNameTextField,
	    	lNameLabel,
	    	lNameTextField,
	    	dobLabel,
	    	datePicker,
	    	updateStudentButton
	    );
		
		tab5.setContent(tab4VBox);
		
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

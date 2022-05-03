package view;

import java.util.ArrayList;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;

public class Tab1 {
	
	private Controller controller = new Controller();
	
	// I get tab2 and tab3 object in order to update the choice box in those classes when
	// a new student is added from this view.
	public Tab1(Tab tab1, Tab2 tab2, Tab3 tab3, ArrayList<Student> students, Stage primaryStage) {
		
		// Text field + labels
		Label nameLabel = new Label("Name:");
		TextField nameTextField = new TextField();
		
		Label middleILabel = new Label("Middle Name Initial:");
		TextField middleITextField = new TextField();
		
		Label lastNameLabel = new Label("Last Name:");
		TextField lastNameTextField = new TextField();
		
		Label idLabel = new Label("Student id:");
		TextField idTextField = new TextField();
		
		Label dobLabel = new Label("Date Of Birth:");
		TextField dobTextField = new TextField();
		
		Label indexOfUserToRemoveLabel = new Label("Index of User To Remove:");
		TextField indexOfUserToRemoveTextField = new TextField();
		
		Button addButton = new Button("Add");
		Button removeButton = new Button("Remove");
		Button listButton = new Button("List");
		Button saveButton = new Button("Save");
		Button loadButton = new Button("Load");
		Button exitButton = new Button("Exit");
		
		// Create text area to display entries
		TextArea textArea = new TextArea();
				
		// Add events to buttons: ADD
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				System.out.println("LENGTH BEFORE => "+ students.size());
				String id = idTextField.getText();
				String firstName = nameTextField.getText();
				String middleI = middleITextField.getText();
				String lastName = lastNameTextField.getText();
				String dob = dobTextField.getText();
				
				// Add student to db
				controller.addStudent(students, id, firstName, middleI, lastName, dob);
				
				System.out.println("LENGTH AFTER => "+ students.size());
				
				// Clear text Fields
				idTextField.clear();
				nameTextField.clear();
				dobTextField.clear();
				
				// Auto update when the student is added
				updateTextArea(textArea, students);
				
				// Reset choiceBox values
				tab2.updateChoiceBox(students);
				tab3.updateChoiceBox(students);
			}
			
		});
		
		// Add events to buttons: LIST
		listButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				updateTextArea(textArea, students);
			}
			
		});
		
		// Add events to buttons: REMOVE
		removeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("LENGTH BEFORE => "+ students.size());
				controller.removeStudent(students, indexOfUserToRemoveTextField.getText());
				updateTextArea(textArea, students);
				System.out.println("LENGTH AFTER => "+ students.size());
			}
			
		});
		
		// Add events to buttons: REMOVE
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				controller.save(students);
			}
			
		});
		
		// Add events to buttons: EXIT
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				boolean save = CloseBox.display("Exit", "Do you want to save before exiting?");
				
				if (save) {
					controller.save(students);
					primaryStage.close();
				} else {
					primaryStage.close();
				}
			}
			
		});
		
		
		// Fill the text area from db
		updateTextArea(textArea, students);
		
	    VBox tab1VBox = new VBox();
	    
	    tab1VBox.getChildren().addAll(
	    	nameLabel,
    		nameTextField,
    		middleILabel,
    		middleITextField,
    		lastNameLabel,
    		lastNameTextField,
    		idLabel,
    		idTextField,
    		dobLabel,
    		dobTextField,
    		addButton,
    		indexOfUserToRemoveLabel,
    		indexOfUserToRemoveTextField,
    		removeButton,
    		listButton,
    		textArea,
    		saveButton,
    		exitButton
	    );
		
		tab1.setContent(tab1VBox);
	}

	
	public void updateTextArea(TextArea textArea,  ArrayList<Student> students) {
		StringBuilder fieldContent = new StringBuilder(""); 
		for (int i = 0; i < students.size(); i++) {
			fieldContent.append("Index: " + i + " | Student id: " + students.get(i).getId() + " | Student Name: "
				+ students.get(i).getName().getName() + "\n");
		}
		textArea.setText(fieldContent.toString());
	}
}

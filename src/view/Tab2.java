package view;

import java.util.ArrayList;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;

public class Tab2 {
	private Controller controller = new Controller();
	protected ChoiceBox<String> choiceBox = new ChoiceBox<>();
		
	public Tab2(Tab tab2, ArrayList<Student> students, Stage primaryStage) {
		
		// Create vBox
		VBox tab2VBox = new VBox();
				
		// Set default option in comboBox
		if (students.size() > 0) {
			choiceBox.setValue(students.get(0).getId());	
		}
		
		/*
		 * Text field + labels
		 */
		Label moduleNameLabel = new Label("Module Name:");
		TextField moduleNameTextField = new TextField();
		
		Label gradeLabel = new Label("Grade:");
		TextField gradeTextField = new TextField();
		
		
		// Fill the comboBox
		for (int i = 0; i < students.size(); i++) {
			choiceBox.getItems().add(students.get(i).getId());
		}
		
		// Add action button and functionality
		Button registerMarkButton = new Button("Register Mark");
		
		registerMarkButton.setOnAction(e -> {
			// Get inputs from view
			String studentId = getChoice(choiceBox);
			String module = moduleNameTextField.getText();
			int grade = Integer.parseInt(gradeTextField.getText());
			
			// Call controlelr with inputs to add a module to the db linked to the provided studentId
			controller.registerMark(studentId, module, grade);
		});
	    
		// Add elements to vBox
	    tab2VBox.getChildren().addAll(
	    	choiceBox,
	    	moduleNameLabel,
	    	moduleNameTextField,
	    	gradeLabel,
	    	gradeTextField,
	    	registerMarkButton
	    );
		
		tab2.setContent(tab2VBox);
		
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

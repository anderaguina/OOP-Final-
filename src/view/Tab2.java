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
		
		VBox tab2VBox = new VBox();
				
		if (students.size() > 0) {
			choiceBox.setValue(students.get(0).getId());	
		}
		
		Label moduleNameLabel = new Label("Module Name:");
		TextField moduleNameTextField = new TextField();
		
		Label gradeLabel = new Label("Grade:");
		TextField gradeTextField = new TextField();
		
		
		
		for (int i = 0; i < students.size(); i++) {
			choiceBox.getItems().add(students.get(i).getId());
		}
		
		Button registerMarkButton = new Button("Register Mark");
		
		registerMarkButton.setOnAction(e -> {
			String studentId = getChoice(choiceBox);
			String module = moduleNameTextField.getText();
			int grade = Integer.parseInt(gradeTextField.getText());
			
			
			controller.registerMark(studentId, module, grade);
			System.out.println(studentId);
		});
	    
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
	
	private String getChoice(ChoiceBox<String> choiceBox) {
		String studentId = choiceBox.getValue();
		return studentId;
	}
	
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

/**
 * 
 */
package controller;


import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.FunctionValidator;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class FunctionFieldController implements ChangeListener<String> {
	
	private FunctionValidator functionValidator;
	private TextField textField;
	
	public FunctionFieldController(TextField textField) {
		
		this.textField = textField;
		functionValidator = FunctionValidator.getFunctionValidator();
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		
		if(newValue.isEmpty()) {
			textField.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			textField.getTooltip().setText("Enter function.");
			return;
		}
		
		boolean isValid;
		
		try {
			isValid = functionValidator.isValid(newValue);
		}
		catch(IllegalArgumentException e) {
			
			textField.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			textField.getTooltip().setText("Illegal argument.");;
			return;
		}
		
		if(!isValid) {
			textField.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			
			StringBuilder sb = new StringBuilder();
			List<String> errors = functionValidator.getValidationErrors();
			for (String error : errors)
				sb.append(error).append(".").append("\n");
			sb.deleteCharAt(sb.length()-1);
			
			textField.getTooltip().setText(sb.toString());
		}
		else {
			textField.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			textField.getTooltip().setText("Go ahead and draw.");
		}
	}
	
}

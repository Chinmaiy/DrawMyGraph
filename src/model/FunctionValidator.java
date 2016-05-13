/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class FunctionValidator {
	
	private final String VARIABLE = "x";
	
	private static FunctionValidator functionValidator;
	
	private Expression function;
	private List<String> validationErrors;

	private FunctionValidator() {
		
		validationErrors = new ArrayList<String>();
	}
	
	public static FunctionValidator getFunctionValidator() {
		
		if(functionValidator == null)
			functionValidator = new FunctionValidator();
		
		return functionValidator;
	}
	
	public boolean isValid(String f) throws IllegalArgumentException {
		
		validationErrors.clear();

		function = new ExpressionBuilder(f)
					.variable(VARIABLE)
					.build();
		
		
		/*Validate an expression before variable have been set
		 *i.e. skip checking if all variables have been set.
		 **/
		ValidationResult result = function.validate(false);
		
		boolean isValid = result.isValid();
		
		if(!isValid)
			validationErrors = result.getErrors();
		
		return isValid;
	}
	
	public List<String> getValidationErrors() {
		return validationErrors;
	}
}

package dev.a2.springdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	private String field;
	private String fieldMatch;
	private String message;

	public void initialize(FieldMatch constraintAnnotation) {
		this.field = constraintAnnotation.field();
	    this.fieldMatch = constraintAnnotation.fieldMatch();
	    this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        
        boolean result =  fieldValue != null 
        		&& fieldMatchValue != null 
        		&& fieldValue.equals(fieldMatchValue);
        
        if (!result){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(field)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        
        return result; 
   
	}
	
}

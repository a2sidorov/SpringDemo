package dev.a2.springdemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {
	
	String field();
    String fieldMatch();
    
	String message() default "The entered passwords do not match";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package haw.vs.middleware.common;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotationsInside
@JsonIgnore
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DoNotLookHere {
}

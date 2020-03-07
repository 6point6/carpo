package net.sixpointsix.carpo.mi.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CarpoMIConfiguration.class)
@Configuration
public @interface EnableCarpoMI {
}

package net.sixpointsix.carpo.mi.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Spring boot annotation to load the carpo MI library
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CarpoMIConfiguration.class)
@Configuration
public @interface EnableCarpoMI {
}

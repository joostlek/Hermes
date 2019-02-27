package nl.jtosti.hermes.config;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RequestMapping("/api")
public @interface V1ApiController {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

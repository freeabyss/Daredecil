package science.freeabyss.template.spa.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author abyss
 * @date 2019-01-23 11:10
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface I18nPrefix {
    /**
     * 国际化文件名
     */
    String value() default "messages";
}
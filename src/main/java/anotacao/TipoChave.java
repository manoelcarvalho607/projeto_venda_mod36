package anotacao;

import java.lang.annotation.*;

/**
 * @author manoel.carvalho
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoChave {

    String value();
}

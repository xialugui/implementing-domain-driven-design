package cn.xialugui.identityaccess.infrastructure.constraint.validator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author 夏露桂
 * @since 2021/10/16 17:19
 */
@SupportedAnnotationTypes({
        "cn.xialugui.identityaccess.infrastructure.constraint.validator.Check"
})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ExistProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement typeElement : annotations) {
            System.out.println(typeElement.toString());
        }
        return true;
    }
}

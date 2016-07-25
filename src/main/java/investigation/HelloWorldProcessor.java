package investigation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("investigation.HelloWorld")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class HelloWorldProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (!roundEnv.processingOver()) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Hello Annotation Processor!");
    }
    return true;
  }
}

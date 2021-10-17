package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import com.google.auto.service.AutoService;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author 夏露桂
 * @since 2021/10/16 17:19
 */
@SupportedAnnotationTypes({
        "cn.xialugui.sharedkernel.infrastructure.constraint.validator.CheckNotNull"
})
@AutoService(Processor.class)
public class CheckNotNullProcessor extends AbstractProcessor {
    JavacTrees trees;
    Messager messager;

    TreeMaker treeMaker;

    Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement typeElement : annotations) {
            System.out.println(typeElement);
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                JCTree jcTree = trees.getTree(element);
                if (jcTree.getKind() == Tree.Kind.METHOD) {
                    addAnnotation(jcTree);
                }
            }

        }
        return true;
    }

    private void addAnnotation(JCTree jcTree) {
        JCTree.JCMethodDecl jcMethodDecl = (JCTree.JCMethodDecl) jcTree;
        List<JCTree.JCVariableDecl> params = jcMethodDecl.getParameters();
        System.out.println("params:" + params);
        for (JCTree.JCVariableDecl param : params) {

            param.accept(new TreeTranslator() {
                @Override
                public void visitVarDef(JCTree.JCVariableDecl tree) {
                    List<JCTree.JCAnnotation> annotations =  treeMaker.Annotations(new Attribute.Compound(
                            Type.noType, null

                    ));
                    List<JCTree.JCAnnotation> annotations = treeMaker.Annotations(new Attribute.Compound(
                            Type.noType, null

                    ));
                    List<JCTree.JCAnnotation> annotations = new List<JCTree.JCAnnotation>();
                    treeMaker.VarDef(
                            treeMaker.Modifiers(null, )
                    );

                    System.out.println("sym:" + tree.sym.setAttributes(););
                }
            });
        }

    }
}


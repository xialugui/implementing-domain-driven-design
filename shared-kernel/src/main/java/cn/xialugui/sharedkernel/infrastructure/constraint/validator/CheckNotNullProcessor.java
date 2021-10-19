package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import com.google.auto.service.AutoService;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
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

    private final static String TARGET = "target";

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
                importPackage(element, Exist.class);
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
        for (JCTree.JCVariableDecl param : params) {

            param.accept(new TreeTranslator() {
                @Override
                public void visitVarDef(JCTree.JCVariableDecl tree) {
                    Type type = tree.vartype.type;
                    for (JCTree.JCAnnotation annotation : tree.mods.annotations) {
                        if (annotation.type.toString().equals(Exist.class.getName())) {
                            annotation.args = List.of(
                                    treeMaker.Assign(
                                            treeMaker.Ident(names.fromString(TARGET)),
                                            treeMaker.ClassLiteral(type)
                                    )
                            );
                            return;
                        }
                    }

                    tree.mods.annotations = tree.mods.annotations.append(treeMaker.Annotation(
                            treeMaker.Ident(names.fromString(Exist.class.getSimpleName())),
                            List.of(
                                    treeMaker.Assign(
                                            treeMaker.Ident(names.fromString(TARGET)),
                                            treeMaker.ClassLiteral(type)
                                    )
                            )
                    ));
                }
            });
        }

    }

    private void importPackage(Element element, Class<?> importClass) {

        JCTree.JCCompilationUnit imports = (JCTree.JCCompilationUnit) trees.getPath(element).getCompilationUnit();
        ListBuffer<JCTree> importsResult = new ListBuffer<>();
        int size = imports.defs.size();
        if (size > 0) {
            importsResult.append(imports.defs.get(0));
            importsResult.append(treeMaker.Import(
                    treeMaker.Select(
                            treeMaker.Ident(names.fromString(importClass.getPackage().getName())),
                            names.fromString(importClass.getSimpleName())
                    ),
                    false
            ));
            for (int i = 1; i < size; i++) {
                importsResult.add(imports.defs.get(i));
            }
            imports.defs = importsResult.toList();
        }


    }
}


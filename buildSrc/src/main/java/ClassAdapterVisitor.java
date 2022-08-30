import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author ryan
 * 字节码处理相关类
 * https://blog.csdn.net/qq_23992393/article/details/103677719
 * https://blog.csdn.net/qq_23992393/article/details/103696976
 */
public class ClassAdapterVisitor extends ClassVisitor {
    public ClassAdapterVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        super.visitInnerClass(name, outerName, innerName, access);
//        System.out.println("name==" + name + " outerName==" + outerName + " innerName==" + innerName + " access==" + access);

    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor methodVisitor = cv.visitMethod(i, s, s1, s2, strings);
        if ("onClick".equals(s)) {
            System.out.println("i==" + i + " s==" + s + " s1==" + s1 + " s2==" + s2);

            methodVisitor = new AdviceAdapter(Opcodes.ASM5, methodVisitor, i, s, s1) {
                //                @Override
//                public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
//                    super.visitMethodInsn(opcode, owner, name, desc, itf);
//                    System.out.println("opcode==" + opcode + " owner==" + owner + " name==" + name + " desc==" + desc + " itf==" + itf);
//                }
//
//                @Override
//                public void visitVarInsn(int opcode, int var) {
//                    super.visitVarInsn(opcode, var);
//                    System.out.println("opcode==" + opcode + " var==" + var);
//                }
//
//                @Override
//                public void visitLdcInsn(Object cst) {
//                    super.visitLdcInsn(cst);
//                    System.out.println("cst==" + cst);
//                }
//
//                @Override
//                public void visitInsn(int opcode) {
//                    super.visitInsn(opcode);
//                    System.out.println("opcode==" + opcode);
//                }
//
                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                    super.visitFieldInsn(opcode, owner, name, desc);
                    System.out.println("opcode==" + opcode + " owner==" + owner + " name==" + name + " desc==" + desc);
                }

                @Override
                protected void onMethodEnter() {
                    mv.visitFieldInsn(GETSTATIC, "com/ouyang/lib_common/ApplicationBase", "Companion", "Lcom/ouyang/lib_common/ApplicationBase$Companion;");
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/ouyang/lib_common/ApplicationBase$Companion", "getInstance", "()Lcom/ouyang/lib_common/ApplicationBase;", false);
                    mv.visitTypeInsn(CHECKCAST, "android/content/Context");
                    mv.visitLdcInsn("hello world");
                    mv.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
                    mv.visitInsn(ICONST_1);
                    mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
                }
            };
        }
        return methodVisitor;
//        methodVisitor = new AdviceAdapter(Opcodes.ASM5, methodVisitor, i, s, s1) {
//            boolean inject;
//
//
//            @Override
//            public AnnotationVisitor visitAnnotation(String s, boolean b) {
//                if (Type.getDescriptor(TimeTotal.class).equals(s)) {
//                    inject = true;
//                }
//                return super.visitAnnotation(s, b);
//            }
//
//            @Override
//            protected void onMethodEnter() {
//                //方法进入时期
//                if (inject) {
//                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//                    mv.visitLdcInsn("this is asm input");
//                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//
//                    mv.visitTypeInsn(NEW, "java/lang/Throwable");
//                    mv.visitInsn(DUP);
//                    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Throwable", "<init>", "()V", false);
//                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Throwable", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
//                    mv.visitInsn(ICONST_0);
//                    mv.visitInsn(AALOAD);
//                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StackTraceElement", "getMethodName", "()Ljava/lang/String;", false);
//                    mv.visitVarInsn(ASTORE, 1);
//
//                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//                    mv.visitVarInsn(ALOAD, 1);
//                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//
//                    mv.visitVarInsn(ALOAD, 1);
//                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
//                    mv.visitMethodInsn(INVOKESTATIC, "com/example/asmdemo/TimeManager", "addStartTime", "(Ljava/lang/String;J)V", false);
//                }
//            }
//
//            @Override
//            protected void onMethodExit(int i) {
//                //方法结束时期
//                if (inject) {
//                    //计算方法耗时
//                    mv.visitVarInsn(ALOAD, 1);
//                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
//                    mv.visitMethodInsn(INVOKESTATIC, "com/example/asmdemo/TimeManager", "addEndTime", "(Ljava/lang/String;J)V", false);
//
//                    mv.visitVarInsn(ALOAD, 1);
//                    mv.visitMethodInsn(INVOKESTATIC, "com/example/asmdemo/TimeManager", "calcuteTime", "(Ljava/lang/String;)V", false);
//                }
//            }
//        };
//        return methodVisitor;
    }
}

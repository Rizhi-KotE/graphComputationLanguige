package graph;

import graph.core.Vertex;
import org.junit.Test;
import org.mdkt.compiler.InMemoryJavaCompiler;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;

public class InvokeTest {
//
//    Callable copile(String source) throws Exception {
//        String s = GraphTranslator.translateGraphToJava("(#a)");
//        InMemoryJavaCompiler inMemoryJavaCompiler = new InMemoryJavaCompiler();
//        String so =
//                "import graph.core.Vertex;" +
//                        "import java.util.concurrent.Callable;" +
//                        "public class Runnable implements Callable{" +
//                        "public Object call(){" +
//                        "return " +
//                        s +
//                        "}}";
//        Class<?> runnable = inMemoryJavaCompiler.compile("Runnable", so);
//        return (Callable) runnable.newInstance();
//    }
//
//    @Test
//    public void invoke() throws Exception {
//        Callable copile = copile("(#a)");
//        Object call = copile.call();
//        assertEquals(new Vertex("a"), call);
//    }
}

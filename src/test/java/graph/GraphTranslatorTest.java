package graph;

import antlr.SemanticException;
import graph.GraphTranslator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(runner.Runner.class)
public class GraphTranslatorTest {
    private GraphTranslator translator;
    private Visitor visitor;

    private String source;
    private String expected;

    @Before
    public void setUp() throws Exception {
        translator = new GraphTranslator();
        visitor = new Visitor();
    }

    @Test
    public void simpleForm() throws Exception {
        String source = "(plus a b)";
        String expect = "plus(a, b)";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.FormContext form = parser.form();
        assertEquals(expect, visitor.visitForm(form));
    }

    @Test
    public void vertexLiteral() throws Exception {
        String source = "(#a)";
        String expect = "Vertex(\"a\")";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.VertexContext vertex = parser.vertex();
        assertEquals(expect, visitor.visitVertex(vertex));
    }

    @Test(expected = SemanticException.class)
    public void first() throws Exception {
        String source = "(#a)";
        String expect = "Vertex(\"a\")";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.FormContext form = parser.form();
        assertEquals(expect, visitor.visitForm(form));
    }

    @Test
    public void simpleTestForm() throws Exception {
        String source = "(if true () ())";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.TestContext test = parser.test();
        assertEquals(expected, visitor.visitTest(test));
    }

    @Test
    public void letForm() throws Exception {
        String source = "(let [] (true))";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.LetContext let = parser.let();
        assertEquals(expected, visitor.visitLet(let));
    }

    @Test
    public void letFormTwoExpressions() throws Exception {
        String source = "(let [] (println true) (true))";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.LetContext let = parser.let();
        assertEquals(expected, visitor.visitLet(let));
    }

    @Test
    public void letFormParameters() throws Exception {
        String source = "(let [a true] (println a) (a))";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.LetContext let = parser.let();
        assertEquals(expected, visitor.visitLet(let));
    }

    @Test
    public void letForm4() throws Exception {
        String source = "(let [] (println true))";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.LetContext let = parser.let();
        assertEquals(expected, visitor.visitLet(let));
        assertTrue(false);
    }

    @Test
    public void testCheckStateShouldBeBoolean() throws Exception {
        assertTrue(false);
    }

}


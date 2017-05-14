package graph;

import graph.compiler.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(runner.Runner.class)
public class IfFormTest {
    private GraphTranslator translator;
    private Visitor visitor;

    private String source;
    private String expected;
    private GraphParser.TestContext test;

    @Before
    public void setUp() throws Exception {
        translator = new GraphTranslator();
        visitor = new Visitor(new scala.collection.immutable.HashMap<>());
        GraphParser parser = translator.translateGraphToJava(source);
        test = parser.test();
    }

    @Test
    public void simpleTestForm() throws Exception {
        assertEquals(expected, visitor.visitTest(test));
    }

    @Test
    public void multipleTestForm() throws Exception {
        assertEquals(expected, visitor.visitTest(test));
    }
}

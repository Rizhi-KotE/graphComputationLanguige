package graph;

import graph.compiler.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(runner.Runner.class)
public class LetFormTest {

    private GraphTranslator translator;
    private Visitor visitor;

    private String source;
    private String expected;
    private GraphParser.LetContext let;


    @Before
    public void setUp() throws Exception {
        translator = new GraphTranslator();
        visitor = new Visitor(new scala.collection.immutable.HashMap<>());
        GraphParser parser = translator.translateGraphToJava(source);
        let = parser.let();
    }

    @Test
    public void letForm() throws Exception {
        assertEquals(expected, visitor.visitLet(let));
    }

    @Test
    public void letFormTwoExpressions() throws Exception {
        assertEquals(expected, visitor.visitLet(let));
    }

    @Test
    public void letFormParameters() throws Exception {
        assertEquals(expected, visitor.visitLet(let));
    }

    @Test
    public void letForm4() throws Exception {
        assertEquals(expected, visitor.visitLet(let));
    }
}

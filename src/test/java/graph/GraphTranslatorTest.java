package graph;

import antlr.SemanticException;
import graph.compiler.Visitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphTranslatorTest {
    private GraphTranslator translator;
    private Visitor visitor;

    @Before
    public void setUp() throws Exception {
        translator = new GraphTranslator();
        visitor = new Visitor(new scala.collection.immutable.HashMap<>());
    }

    @Test
    public void vertexLiteral() throws Exception {
        String source = "#a";
        String expect = "new Vertex(\"a\")";
        GraphParser parser = translator.translateGraphToJava(source);
        GraphParser.VertexContext vertex = parser.vertex();
        assertEquals(expect, visitor.visitVertex(vertex));
    }


}


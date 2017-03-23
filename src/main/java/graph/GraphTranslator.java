package graph;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.StringReader;

public class GraphTranslator {
    public GraphParser translateGraphToJava(String source) throws IOException {
        StringReader stringReader = new StringReader(source);
        ANTLRInputStream input = new ANTLRInputStream(stringReader);
        GraphLexer lexer = new GraphLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GraphParser parser = new GraphParser(tokens);
        parser.setBuildParseTree(true);
        return parser;
    }

    public String translate(String s) {
        return null;
    }
}

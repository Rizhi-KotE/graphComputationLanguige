package graph;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.copyOfRange;

public class Visitor extends GraphBaseVisitor<String> implements GraphVisitor<String> {

    @Override
    public String visitVertex(GraphParser.VertexContext ctx) {
        return String.format("Vertex(\"%s\")", ctx.character().getText());
    }

    @Override
    public String visitTest(GraphParser.TestContext ctx) {
        String check = visitCheck(ctx.check());
        String than = visitThen(ctx.then());
        String otherwise = visitOtherwise(ctx.otherwise());
        return String.format("if(%s) {\n%s\n} else {\n%s\n}", check, than, otherwise);
    }

    @Override
    public String visitLogical(GraphParser.LogicalContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitCharacter(GraphParser.CharacterContext ctx) {
        return String.format("%s", ctx.getText());
    }

    @Override
    public String visitAction(GraphParser.ActionContext ctx) {
        String[] array = ctx.form()
                .stream()
                .map(this::visitForm)
                .toArray(String[]::new);
//            findFunction(array[0], copyOfRange(array, 1, array.length));
        if (array.length > 0) {
            String function = array[0];
            String[] args = copyOfRange(array, 1, array.length);
            return String.format("%s(%s)", function, String.join(", ", args));
        }
        return "";
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (nextResult == null) nextResult = "";
        if (aggregate == null) aggregate = "";
        return aggregate + nextResult;
    }
}

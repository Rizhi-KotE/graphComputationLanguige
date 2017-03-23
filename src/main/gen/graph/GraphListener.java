// Generated from /home/rizhi-kote/Student/labs/graphComputationLanguige/src/main/antlr/graph/Graph.g4 by ANTLR 4.6
package graph;

  package graph;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GraphParser}.
 */
public interface GraphListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GraphParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(GraphParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(GraphParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(GraphParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(GraphParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(GraphParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(GraphParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(GraphParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(GraphParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#vertex}.
	 * @param ctx the parse tree
	 */
	void enterVertex(GraphParser.VertexContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#vertex}.
	 * @param ctx the parse tree
	 */
	void exitVertex(GraphParser.VertexContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#charachter}.
	 * @param ctx the parse tree
	 */
	void enterCharachter(GraphParser.CharachterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#charachter}.
	 * @param ctx the parse tree
	 */
	void exitCharachter(GraphParser.CharachterContext ctx);
}
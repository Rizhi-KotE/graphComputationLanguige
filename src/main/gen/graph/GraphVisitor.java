// Generated from /home/rizhi-kote/Student/labs/graphComputationLanguige/src/main/antlr/graph/Graph.g4 by ANTLR 4.6
package graph;

  package graph;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GraphParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GraphVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GraphParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(GraphParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(GraphParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(GraphParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(GraphParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#vertex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVertex(GraphParser.VertexContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#charachter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharachter(GraphParser.CharachterContext ctx);
}
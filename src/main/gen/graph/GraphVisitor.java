// Generated from /home/rizhi-kote/Student/labs/graphComputationLanguige/src/main/antlr/graph/Graph.g4 by ANTLR 4.7
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
	 * Visit a parse tree produced by {@link GraphParser#recur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecur(GraphParser.RecurContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(GraphParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#let}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(GraphParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(GraphParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(GraphParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#fn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFn(GraphParser.FnContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(GraphParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GraphParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#vector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVector(GraphParser.VectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#binding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinding(GraphParser.BindingContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#idtf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdtf(GraphParser.IdtfContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(GraphParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#edge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdge(GraphParser.EdgeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(GraphParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(GraphParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#character}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter(GraphParser.CharacterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#vertex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVertex(GraphParser.VertexContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(GraphParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphParser#logical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical(GraphParser.LogicalContext ctx);
}
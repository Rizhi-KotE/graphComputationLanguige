// Generated from /home/rizhi-kote/Student/labs/graphComputationLanguige/src/main/antlr/graph/Graph.g4 by ANTLR 4.7
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
	 * Enter a parse tree produced by {@link GraphParser#recur}.
	 * @param ctx the parse tree
	 */
	void enterRecur(GraphParser.RecurContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#recur}.
	 * @param ctx the parse tree
	 */
	void exitRecur(GraphParser.RecurContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(GraphParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(GraphParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#let}.
	 * @param ctx the parse tree
	 */
	void enterLet(GraphParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#let}.
	 * @param ctx the parse tree
	 */
	void exitLet(GraphParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(GraphParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(GraphParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(GraphParser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(GraphParser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#fn}.
	 * @param ctx the parse tree
	 */
	void enterFn(GraphParser.FnContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#fn}.
	 * @param ctx the parse tree
	 */
	void exitFn(GraphParser.FnContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(GraphParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(GraphParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GraphParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GraphParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#vector}.
	 * @param ctx the parse tree
	 */
	void enterVector(GraphParser.VectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#vector}.
	 * @param ctx the parse tree
	 */
	void exitVector(GraphParser.VectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#binding}.
	 * @param ctx the parse tree
	 */
	void enterBinding(GraphParser.BindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#binding}.
	 * @param ctx the parse tree
	 */
	void exitBinding(GraphParser.BindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#idtf}.
	 * @param ctx the parse tree
	 */
	void enterIdtf(GraphParser.IdtfContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#idtf}.
	 * @param ctx the parse tree
	 */
	void exitIdtf(GraphParser.IdtfContext ctx);
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
	 * Enter a parse tree produced by {@link GraphParser#edge}.
	 * @param ctx the parse tree
	 */
	void enterEdge(GraphParser.EdgeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#edge}.
	 * @param ctx the parse tree
	 */
	void exitEdge(GraphParser.EdgeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(GraphParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(GraphParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(GraphParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(GraphParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#character}.
	 * @param ctx the parse tree
	 */
	void enterCharacter(GraphParser.CharacterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#character}.
	 * @param ctx the parse tree
	 */
	void exitCharacter(GraphParser.CharacterContext ctx);
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
	 * Enter a parse tree produced by {@link GraphParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(GraphParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(GraphParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphParser#logical}.
	 * @param ctx the parse tree
	 */
	void enterLogical(GraphParser.LogicalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphParser#logical}.
	 * @param ctx the parse tree
	 */
	void exitLogical(GraphParser.LogicalContext ctx);
}
// Generated from C:/Users/almei/Desktop/Universidade 2º ano/2º Semestre/Engenharia de Aplicações/lei21_22_s4_2di_03/eapli.base/base.core/src/main/java/eapli/base/antlr/surveyvalidation\survey_grammar.g4 by ANTLR 4.10.1
package eapli.base.antlr.surveyvalidation;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link survey_grammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface survey_grammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link survey_grammarParser#questionary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionary(survey_grammarParser.QuestionaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link survey_grammarParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(survey_grammarParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link survey_grammarParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(survey_grammarParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link survey_grammarParser#welcome_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWelcome_message(survey_grammarParser.Welcome_messageContext ctx);
	/**
	 * Visit a parse tree produced by {@link survey_grammarParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(survey_grammarParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link survey_grammarParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(survey_grammarParser.QuestionContext ctx);
}
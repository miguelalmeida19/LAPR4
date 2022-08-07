// Generated from C:/Users/almei/Desktop/Universidade 2º ano/2º Semestre/Engenharia de Aplicações/lei21_22_s4_2di_03/eapli.base/base.core/src/main/java/eapli/base/antlr/surveyvalidation\survey_grammar.g4 by ANTLR 4.10.1
package eapli.base.antlr.surveyvalidation;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link survey_grammarParser}.
 */
public interface survey_grammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link survey_grammarParser#questionary}.
	 * @param ctx the parse tree
	 */
	void enterQuestionary(survey_grammarParser.QuestionaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link survey_grammarParser#questionary}.
	 * @param ctx the parse tree
	 */
	void exitQuestionary(survey_grammarParser.QuestionaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link survey_grammarParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(survey_grammarParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link survey_grammarParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(survey_grammarParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link survey_grammarParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(survey_grammarParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link survey_grammarParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(survey_grammarParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link survey_grammarParser#welcome_message}.
	 * @param ctx the parse tree
	 */
	void enterWelcome_message(survey_grammarParser.Welcome_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link survey_grammarParser#welcome_message}.
	 * @param ctx the parse tree
	 */
	void exitWelcome_message(survey_grammarParser.Welcome_messageContext ctx);
	/**
	 * Enter a parse tree produced by {@link survey_grammarParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(survey_grammarParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link survey_grammarParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(survey_grammarParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link survey_grammarParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(survey_grammarParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link survey_grammarParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(survey_grammarParser.QuestionContext ctx);
}
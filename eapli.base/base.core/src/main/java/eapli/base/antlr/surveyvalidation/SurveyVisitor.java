package eapli.base.antlr.surveyvalidation;

public class SurveyVisitor extends survey_grammarBaseVisitor<String>{

    @Override
    public String visitQuestionary(survey_grammarParser.QuestionaryContext ctx) {
        return super.visitQuestionary(ctx);
    }
}

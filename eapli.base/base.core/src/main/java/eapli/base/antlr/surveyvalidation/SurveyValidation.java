package eapli.base.antlr.surveyvalidation;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SurveyValidation {

    public static void validate(String text){
        survey_grammarLexer lexer = new survey_grammarLexer(CharStreams.fromString(text));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        survey_grammarParser parser = new survey_grammarParser(tokens);
        survey_grammarParser.QuestionaryContext tree = parser.questionary(); // parse
        SurveyVisitor surveyVisitor = new SurveyVisitor();
        surveyVisitor.visit(tree);
    }

    public static void validate(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        survey_grammarLexer lexer = new survey_grammarLexer(CharStreams.fromStream(fileInputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        survey_grammarParser parser = new survey_grammarParser(tokens);
        survey_grammarParser.QuestionaryContext tree = parser.questionary(); // parse
        SurveyVisitor surveyVisitor = new SurveyVisitor();
        surveyVisitor.visit(tree);
    }
}

package eapli.base.antlr.surveyvalidation;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Maino {
    public static void main(String[] args) {
        String text = "COSM22-01\n" +
                "\n" +
                "Cosmetics Questionnaire\n" +
                "\n" +
                "Hello,\n" +
                "This questionnaire aims to solve some problems\n" +
                "It takes approximately 12 minutes\n" +
                "Thank you very much for your time and support.\n" +
                "\n" +
                "1\n" +
                "Cosmetics\n" +
                "Pedro cheira mal, mas eu nao julgo, porque pronto, cada um sabe de si. Claro que sim, tudo funciona!\n" +
                "mandatory\n" +
                "yes\n" +
                "\n" +
                "1\n" +
                "O Pedro cheira mal?\n" +
                "[Ele cheira mal, mas shhh]\n" +
                "Numeric\n" +
                "mandatory\n" +
                "(que pivete)\n" +
                "\n" +
                "2\n" +
                "O Miguel fede?\n" +
                "[cuidado, que esta e facil!]\n" +
                "Free-Text\n" +
                "optional\n" +
                "(que cheirinho)\n" +
                "\n" +
                "3\n" +
                "Ta tudo mal?\n" +
                "[provavelmente, sim]\n" +
                "Single-Choice\n" +
                "mandatory\n" +
                "(cuidado com o que respondem)\n" +
                "[] Sim;\n" +
                "[] Nao;\n" +
                "\n" +
                "1\n" +
                "Cosmetics\n" +
                "Pedro cheira mal, mas eu nao julgo, porque pronto, cada um sabe de si. Claro que sim, tudo funciona!\n" +
                "mandatory\n" +
                "yes\n" +
                "\n" +
                "1\n" +
                "O Pedro cheira mal?\n" +
                "[Ele cheira mal, mas shhh]\n" +
                "Numeric\n" +
                "mandatory\n" +
                "(que pivete)\n" +
                "\n" +
                "2\n" +
                "O Miguel fede?\n" +
                "[cuidado, que esta e facil!]\n" +
                "Free-Text\n" +
                "optional\n" +
                "(que cheirinho)\n" +
                "\n" +
                "3\n" +
                "Ta tudo mal?\n" +
                "[provavelmente, sim]\n" +
                "Single-Choice\n" +
                "mandatory\n" +
                "(cuidado com o que respondem)\n" +
                "[] Sim;\n" +
                "[] Nao;\n" +
                "\n";

        SurveyValidation.validate(text);
    }
}

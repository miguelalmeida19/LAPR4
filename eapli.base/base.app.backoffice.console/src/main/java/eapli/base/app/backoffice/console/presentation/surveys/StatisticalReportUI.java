package eapli.base.app.backoffice.console.presentation.surveys;

import eapli.base.surveymanagement.application.ShowSurveyController;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswer;
import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticalReportUI extends AbstractUI {

    private ShowSurveyController theController = new ShowSurveyController();


    @Override
    protected boolean doShow() {

        List<Survey> surveys = (List<Survey>) theController.allSurveys();
        final SelectWidget<Survey> selector = new SelectWidget<>("Select one survey to see the statistical report", surveys, new SurveysPrinter());
        selector.show();
        Survey survey = selector.selectedElement();
        while (survey==null){
            selector.show();
            survey = selector.selectedElement();
        }

        int numCustomers = survey.customers().size();

        List<SurveyAnswer> surveyAnswers = survey.surveyAnswers();
        if(surveyAnswers.isEmpty()) {
            System.out.println("No one answered this survey.");
        }
        else {
            List<String> perguntas= new ArrayList<>();
            List<List<String>> totalanswers= new ArrayList<>();
            List<String> options =new ArrayList<>();

            for (SurveyAnswer answer : surveyAnswers) {
                String s = answer.answer();
                String s1 = trimstring(s,"\n\n",2);
                s1 = trimstring(s1,"\n\n",2);
                s1 = trimstring(s1,"\n\n",2);
                s1 = trimstring(s1,"\n\n",2);
                s1= s1.substring(0,s1.lastIndexOf("\n\n"));
                s1= s1.substring(0,s1.lastIndexOf("\n\n")+2);
                System.out.println(StringUtils.countMatches(s1,"\n\n"));
                List<String> respostaa= Arrays.asList(s1.split("\n\n"));
                List<String> respostas= new ArrayList<>(respostaa);
                for (int i = 0; i < respostas.size(); i++) {
                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                    perguntas.add(respostas.get(i).substring(0, respostas.get(i).indexOf("\n")));
                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                    int j;
                    do {
                        j = 1;
                        try {
                            switch (respostas.get(i).substring(0, respostas.get(i).indexOf("\n"))) {
                                case "Single-Choice":
                                    // e.g: A = 30%, B = 27%, C = 40%, Outras = 3%)    (nota que o número de opções varia de pergunta para pergunta)
                                case "Single-Choice with input value":

                                case "Numeric":

                                case "Multiple Choice with input value":
                                    // Distribuição (em %) das respostas por cada alternativa (e.g: A = 30%, B = 27%, C = 40%, Outras = 3%)
                                    // Distribuição (em %) das respostas combinadas (e.g.: A+B = 15%, A+C = 100%, B+C=100%, A+B+C=10%)
                                case "Free-Text":
                                    options.add(respostas.get(i).substring(0, respostas.get(i).indexOf("\n")));
                                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                                    break;

                                case "Scaling Options":
                                    options.add(respostas.get(i).substring(0, respostas.get(i).indexOf("\n")));
                                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                                    while(respostas.get(i).charAt(0) != '[')
                                    {
                                        respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                                    }

                                    // Distribuição das várias hipóteses por cada posição (e.g.: 1º Lugar = A(40%) B (60%) C(0%); 2º Lugar = A(50%) B(40%) C(10%); 3ºLugar=A(10%) C(90%))
                                    break;
                                default:

                                    respostas.set(i, trimstring(respostas.get(i), "\n", 1));
                                    j = 0;

                            }
                        }
                        catch (Exception e)
                        {
                            respostas.remove(i);
                            perguntas.remove(i);
                            i--;
                        }
                    } while (j == 0);
                }
                totalanswers.add(respostas);
            }

            System.out.println("Statistics: ");
            System.out.println("Universe Size : " + numCustomers);
            int respostasObtidas = 0;

            List<String> statistics= new ArrayList<>();
            for (int i = 0; i < totalanswers.get(0).size(); i++) {
                List<String> a = new ArrayList<>();
                List<Integer> cont = new ArrayList<>();
                for (List<String> respostas: totalanswers) {
                    if(!respostas.get(i).equals("[?] no answer")){
                        respostasObtidas++;
                    }
                    if(options.get(i).equals("Single-Choice") || options.get(i).equals("Single-Choice with input value")|| options.get(i).equals("Numeric")|| options.get(i).equals("Free-Text")|| options.get(i).equals("Scaling Options")){
                        if(a.contains(respostas.get(i))){
                            cont.set(a.indexOf(respostas.get(i)), cont.get(a.indexOf(respostas.get(i))) +1);
                        } else{
                            a.add(respostas.get(i));
                            cont.add(1);
                        }
                    }
                    else if(options.get(i).equals("Multiple Choice with input value"))
                    {
                        List<String> splitted = Arrays.asList(respostas.get(i).split("\n"));
                        for (String str: splitted) {
                            if(a.contains(str.substring(0,str.indexOf(" ->")))){
                                cont.set(a.indexOf(str.substring(0,str.indexOf(" ->"))), cont.get(a.indexOf(str)) +1);
                            } else{
                                a.add(str);
                                cont.add(1);
                            }
                        }
                    }
                }
                //statistics
                String stat="Question "+(i+1)+": \n";
                if(options.get(i).equals("Single-Choice")|| options.get(i).equals("Single-Choice with input value")|| options.get(i).equals("Numeric")|| options.get(i).equals("Free-Text")|| options.get(i).equals("Multiple Choice with input value") ||options.get(i).equals("Scaling Options")) {
                    for (int j = 0; j < a.size(); j++) {
                        stat+=cont.get(j)+" answered with: "+a.get(j)+" ("+((double)cont.get(j)/totalanswers.size()*100)+"%)\n";
                    }
                }

            }
            for (String st: statistics) {
                System.out.println(st);
            }
            System.out.println("Number of Answered Questions : " + respostasObtidas);
            double calc = (( (double)respostasObtidas/ (totalanswers.size()*totalanswers.get(0).size())) * 100);
            System.out.println("% of Answered Questions : " + calc);

        }
        return false;
    }

    @Override
    public String headline() {
        return "Surveys Statistical Report";
    }

    public String trimstring(String s, String trimwhere, int n)
    {
        String s1 = s.substring(s.indexOf(trimwhere)+n);
        s1.trim();
        return s1;
    }
}

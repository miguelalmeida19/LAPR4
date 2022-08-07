package eapli.base.app.backoffice.console.presentation.surveys;

import eapli.base.Application;
import eapli.base.antlr.surveyvalidation.SurveyValidation;
import eapli.base.surveymanagement.application.CreateSurveyController;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateSurveyUI extends AbstractUI {

    private final CreateSurveyController theController = new CreateSurveyController();

    @Override
    protected boolean doShow() {

        String code = Console.readLine("Type the code of AGV");
        String description = Console.readLine("Type the description of AGV");
        int period = Console.readInteger("Type the period (in days) it will be performed starting from now");
        List<String> rules = new ArrayList<>();

        int option = 1;
        rules.add(Console.readLine("Type one rule"));
        do {
            try {
                option = Console.readInteger("Do you want to type more rules?\n[1] Yes\n[2] No");
                if (option == 2) {
                    option = 0;
                } else if (option == 1) {
                    rules.add(Console.readLine("Type the rule"));
                }
            } catch (Exception e) {
                option = Console.readInteger("Do you want to type more rules?\n[1] Yes\n[2] No\n");
            }
        } while (option != 0);

        StringBuilder surveyBuilder = new StringBuilder();
        final String optional = " [optional]";

        System.out.println("\nThere are some fields that are optional, if you don't want to type leave them empty\n");

        addHeader(surveyBuilder);
        addSection(surveyBuilder);
        addQuestion(surveyBuilder);

        do {
            try {
                option = Console.readInteger("Select what you want to do:" + "\n[1] Add new Section\n[2] Add new question to this section\n[3] Finish AGV");
                if (option == 1) {
                    addSection(surveyBuilder);
                    addQuestion(surveyBuilder);
                } else if (option == 2) {
                    addQuestion(surveyBuilder);
                } else if (option == 3) {
                    surveyBuilder.append("You have successfully completed the questionnaire.Thank you for your help.");
                    option = 0;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);

        try {
            SurveyValidation.validate(surveyBuilder.toString());
            theController.addSurvey(code, description, period, rules, surveyBuilder.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    private void addHeader(StringBuilder surveyBuilder) {
        int option;
        final String optional = " [optional]";
        surveyBuilder.append(Console.readLine("Type the Id of the Questionnaire")).append("\n\n");
        surveyBuilder.append(Console.readLine("Type the Title of the Questionnaire")).append("\n\n");
        option = 1;
        do {
            try {
                option = Console.readInteger("Do you want to type the Welcome Message?" + optional + "\n[1] Yes\n[2] No");
                if (option == 1) {
                    surveyBuilder.append("Hello,").append("\n");
                    System.out.println("Hello,");
                    surveyBuilder.append("This questionnaire aims to ").append(Console.readLine("This questionnaire aims to ")).append("\n");
                    surveyBuilder.append("It takes approximately ").append(Console.readLine("It takes approximately ... minutes")).append("minutes").append("\n").append("Thank you very much for your time and support.").append("\n");
                    option = 0;
                } else if (option == 2) {
                    option = 0;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
    }

    private void addSection(StringBuilder surveyBuilder) {
        int option;
        final String optional = " [optional]";
        surveyBuilder.append(Console.readLine("Type the Section ID")).append("\n");
        surveyBuilder.append(Console.readLine("Type the Section Title")).append("\n");
        option = 1;
        do {
            try {
                option = Console.readInteger("Do you want to type the Section Description?" + optional + "\n[1] Yes\n[2] No");
                if (option == 1) {
                    surveyBuilder.append(Console.readLine("Type the Section Description" + optional)).append("\n");
                    option = 0;
                } else if (option == 2) {
                    option = 0;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
        selectObrigatoriness(surveyBuilder);
        surveyBuilder.append(Console.readLine("Type the Section Repeatability")).append("\n\n");
    }

    private void addQuestion(StringBuilder surveyBuilder) {
        int option;
        final String optional = " [optional]";
        surveyBuilder.append(Console.readLine("Type the Question ID")).append("\n");
        surveyBuilder.append(Console.readLine("Type the Question")).append("\n");
        option = 1;
        do {
            try {
                option = Console.readInteger("Do you want to type the Question Instruction?" + optional + "\n[1] Yes\n[2] No");
                if (option == 1) {
                    surveyBuilder.append(Console.readLine("Type the Question Instruction" + optional)).append("\n");
                    option = 0;
                } else if (option == 2) {
                    option = 0;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
        String selected = selectTypeOfQuestion(surveyBuilder);
        selectObrigatoriness(surveyBuilder);
        surveyBuilder.append(Console.readLine("Type the extra info of the Question")).append("\n");
        if (selected.equals("Free-Text")||selected.equals("Numeric")){}
        else if (selected.equals("Single-Choice") || selected.equals("Multiple Choice") || selected.equals("Sorting Options") || selected.equals("Multiple Choice with input value") || selected.equals("Single-Choice with input value")){
            addChoices(surveyBuilder);
            if(selected.equals("Multiple Choice with input value") || selected.equals("Single-Choice with input value")){
                surveyBuilder.append("[] Other;").append("\n");
            }
        }
        else {
            addScales(surveyBuilder);
            addOptions(surveyBuilder);
        }
        surveyBuilder.append("\n");
    }

    public String selectTypeOfQuestion(StringBuilder surveyBuilder){
        int option = 1;
        String selected = "";
        do {
            try {
                option = Console.readInteger("Select the type of question" + "\n[1] Free-Text\n[2] Numeric\n[3] Single-Choice\n[4] Multiple Choice\n[5] Sorting Options\n[6] Multiple Choice with input value\n[7] Single-Choice with input value\n[8] Scaling Options");
                if (option == 1) {
                    selected = "Free-Text";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                } else if (option == 2) {
                    selected = "Numeric";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                } else if (option == 3){
                    selected = "Single-Choice";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                }
                else if (option == 4){
                    selected = "Multiple Choice";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                }
                else if (option == 5){
                    selected = "Sorting Options";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                }
                else if (option == 6){
                    selected = "Multiple Choice with input value";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                }
                else if (option == 7){
                    selected = "Single-Choice with input value";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                }
                else if (option == 8){
                    selected = "Scaling Options";
                    surveyBuilder.append(selected).append("\n");
                    option = 0;
                }
                else {
                    option = 1;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
        return selected;
    }

    public void addScales(StringBuilder surveyBuilder){
        int option = 1;
        surveyBuilder.append(Console.readLine("Type a Scale"));
        do {
            try {
                option = Console.readInteger("Continue to add scales?" + "\n[1] Yes\n[2] No");
                if (option == 1) {
                    surveyBuilder.append("|").append(Console.readLine("Type a Scale"));
                } else if (option == 2) {
                    surveyBuilder.append("\n");
                    option = 0;
                }else {
                    option = 1;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
    }

    public void addOptions(StringBuilder surveyBuilder){
        int option = 1;
        surveyBuilder.append("- ").append(Console.readLine("Type one option")).append(";\n");
        do {
            try {
                option = Console.readInteger("Continue to add options?" + "\n[1] Yes\n[2] No");
                if (option == 1) {
                    surveyBuilder.append("- ").append(Console.readLine("Type one option")).append(";\n");
                } else if (option == 2) {
                    option = 0;
                }else {
                    option = 1;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
    }

    public void addChoices(StringBuilder surveyBuilder){
        int option = 1;
        surveyBuilder.append("[] ").append(Console.readLine("Type one option")).append(";\n");
        do {
            try {
                option = Console.readInteger("Continue to add options?" + "\n[1] Yes\n[2] No");
                if (option == 1) {
                    surveyBuilder.append("[] ").append(Console.readLine("Type one option")).append(";\n");
                } else if (option == 2) {
                    option = 0;
                }else {
                    option = 1;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
    }

    public void selectObrigatoriness(StringBuilder surveyBuilder){
        int option = 1;
        do {
            try {
                option = Console.readInteger("Select the Obligatoriness" + "\n[1] mandatory\n[2] optional\n[3] condition dependent");
                if (option == 1) {
                    surveyBuilder.append("[mandatory]").append("\n");
                    option = 0;
                } else if (option == 2) {
                    surveyBuilder.append("[optional]").append("\n");
                    option = 0;
                } else if (option == 3){
                    surveyBuilder.append("[condition dependent]").append("\n");
                    option = 0;
                }else {
                    option = 1;
                }
            } catch (Exception e) {
                option = 1;
            }
        } while (option != 0);
    }

    @Override
    public String headline() {
        return "Create AGV";
    }
}

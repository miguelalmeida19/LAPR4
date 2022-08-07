package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.AGVBootstrapperBase;
import eapli.base.infrastructure.bootstrapers.SurveyBootstrapperBase;
import eapli.base.warehousemanagement.application.ListAGVDocksController;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.framework.actions.Action;

import java.util.Arrays;
import java.util.List;

public class BackofficeSurveyBootstrapper extends SurveyBootstrapperBase implements Action {

    private final ListAGVDocksController listAGVDocksController= new ListAGVDocksController();

    @Override
    public boolean execute() {
        try{
            registerSurvey("00001", "Survey about products", 8, Arrays.asList("has ordered product:tissot seastar","age higher than 18", "gender is male"), "PRPP22-01\n" +
                    "\n" +
                    "Product Questionnaire\n" +
                    "\n" +
                    "Hello,\n" +
                    "This questionnaire aims to better understand people perception of the price of a product\n" +
                    "It takes approximately 15 minutes\n" +
                    "Thank you very much for your time and support.\n" +
                    "\n" +
                    "1\n" +
                    "Product Price\n" +
                    "Questions about product price perception\n" +
                    "[mandatory]\n" +
                    "yes\n" +
                    "\n" +
                    "1\n" +
                    "How familiar are you with product?\n" +
                    "Single-Choice\n" +
                    "[mandatory]\n" +
                    "(answer honestly)\n" +
                    "[] I use daily;\n" +
                    "[] I have bought several times;\n" +
                    "[] In general I am aware of the product;\n" +
                    "[] I was not aware of such a product;\n" +
                    "\n" +
                    "2\n" +
                    "If you knew that the product was priced, would you pay more or less to buy it?\n" +
                    "Single-Choice with input value\n" +
                    "[mandatory]\n" +
                    "(no information to provide)\n" +
                    "[] 10 to 20 more;\n" +
                    "[] 5 to 10 more;\n" +
                    "[] No more no less;\n" +
                    "[] 5 to 10 less;\n" +
                    "[] 0 to 20 less;\n" +
                    "[] Other;\n" +
                    "\n" +
                    "3\n" +
                    "About how many units of product more or less would you buy?\n" +
                    "[answer honestly]\n" +
                    "Scaling Options\n" +
                    "[mandatory]\n" +
                    "(consider next year, at each point of the listed price.)\n" +
                    "4 less|1 to 3 less|1 to 3 more|4 more\n" +
                    "- 20;\n" +
                    "- 10;\n" +
                    "- 5;\n" +
                    "- 3;\n" +
                    "\n" +
                    "4\n" +
                    "What would you expect to pay for product?\n" +
                    "Numeric\n" +
                    "[optional]\n" +
                    "(type the number that you expect)\n" +
                    "\n" +
                    "5\n" +
                    "At what price would product start being so cheap that you would feel that the quality cant be good?\n" +
                    "Single-Choice\n" +
                    "[mandatory]\n" +
                    "(these are only price range examples)\n" +
                    "[] 10;\n" +
                    "[] 25;\n" +
                    "[] 7;\n" +
                    "[] 12;\n" +
                    "[] 50;\n" +
                    "\n" +
                    "2\n" +
                    "Product Review\n" +
                    "Questions about some products review\n" +
                    "[optional]\n" +
                    "yes\n" +
                    "\n" +
                    "1\n" +
                    "Based on your experience, can you apply the following attributes to product?\n" +
                    "Scaling Options\n" +
                    "[optional]\n" +
                    "(select one of the scales available)\n" +
                    "Definitely|Probably|Probably not|Definitely not|Not sure\n" +
                    "- High quality;\n" +
                    "- Fair price;\n" +
                    "- A brand I trust;\n" +
                    "- Well built;\n" +
                    "- Good packaging;\n" +
                    "- High value;\n" +
                    "\n" +
                    "2\n" +
                    "Tell us another reason why you would buy the product, other than the product itself?\n" +
                    "Multiple Choice with input value\n" +
                    "[optional]\n" +
                    "(you can select more than one option)\n" +
                    "[] Experience as a customer;\n" +
                    "[] Sales or Service Representative;\n" +
                    "[] Advertising;\n" +
                    "[] Warranty;\n" +
                    "[] Packaging;\n" +
                    "[] Trends;\n" +
                    "[] Other;\n" +
                    "\n" +
                    "3\n" +
                    "What do you like least about the product?\n" +
                    "Free-Text\n" +
                    "[optional]\n" +
                    "(write whatever you think)\n" +
                    "\n" +
                    "You have successfully completed the questionnaire.Thank you for your help.");
            registerSurvey("00002", "Survey about children", 15, Arrays.asList("gender is female"), "CHPA22-01\n" +
                    "\n" +
                    "Children Questionnaire\n" +
                    "\n" +
                    "Hello,\n" +
                    "This questionnaire aims to children\n" +
                    "It takes approximately 10 minutes\n" +
                    "Thank you very much for your time and support.\n" +
                    "\n" +
                    "1\n" +
                    "Children 5 to 6\n" +
                    "Questions about physical activity\n" +
                    "[mandatory]\n" +
                    "yes\n" +
                    "\n" +
                    "1\n" +
                    "On a typical weekday, how much time does your child spend doing the following activities?\n" +
                    "Scaling Options\n" +
                    "[mandatory]\n" +
                    "(from waking up to going to bed)\n" +
                    "None|less than 15min|30min|1h|2h|3h|4h|5h|less than 6h\n" +
                    "- Watch TV;\n" +
                    "- Playing computer or console games;\n" +
                    "\n" +
                    "2\n" +
                    "How many hours does the average child sleep a day?\n" +
                    "Numeric\n" +
                    "[mandatory]\n" +
                    "(type the time in hours)\n" +
                    "\n" +
                    "2\n" +
                    "Children 10 to 19\n" +
                    "Questions about physical activity\n" +
                    "[mandatory]\n" +
                    "yes\n" +
                    "\n" +
                    "1\n" +
                    "Do you usually practice any kind of programmed and regular sports activity excluding physical education classes?\n" +
                    "Single-Choice\n" +
                    "[mandatory]\n" +
                    "(select one of the options below)\n" +
                    "[] Yes;\n" +
                    "[] No;\n" +
                    "\n" +
                    "2\n" +
                    "What is your current fitness level?\n" +
                    "Single-Choice\n" +
                    "[mandatory]\n" +
                    "(select one of the options below)\n" +
                    "[] Perfect;\n" +
                    "[] Good;\n" +
                    "[] Average;\n" +
                    "[] Weak;\n" +
                    "[] Very weak;\n" +
                    "\n" +
                    "3\n" +
                    "Why do you practice sports?\n" +
                    "Single-Choice with input value\n" +
                    "[optional]\n" +
                    "(select one of the options below)\n" +
                    "[] It is part of my job and I make sport my life;\n" +
                    "[] I enjoy doing sports;\n" +
                    "[] I want to lose weight;\n" +
                    "[] I want to keep fit;\n" +
                    "[] Other;\n" +
                    "\n" +
                    "4\n" +
                    "Have you ever followed an exercise plan?\n" +
                    "Single-Choice\n" +
                    "[mandatory]\n" +
                    "(select one of the options below)\n" +
                    "[] Yes;\n" +
                    "[] No;\n" +
                    "\n" +
                    "5\n" +
                    "Have you ever practiced any of these sports?\n" +
                    "Scaling Options\n" +
                    "[mandatory]\n" +
                    "(from waking up to going to bed)\n" +
                    "Yes|No|Not regularly\n" +
                    "- Running;\n" +
                    "- Swimming;\n" +
                    "- Bicycling;\n" +
                    "- Skating;\n" +
                    "- Extreme sports;\n" +
                    "- Martial arts;\n" +
                    "\n" +
                    "You have successfully completed the questionnaire.Thank you for your help.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}

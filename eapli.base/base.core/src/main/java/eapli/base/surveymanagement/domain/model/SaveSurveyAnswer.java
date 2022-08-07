package eapli.base.surveymanagement.domain.model;

import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.OrdersService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class SaveSurveyAnswer extends Thread{
    boolean exists = false;
    private final OrdersService ordersService = new OrdersService();

    private SurveyDto surveyDto;

    public SaveSurveyAnswer(SurveyDto surveyDto) {
        this.surveyDto = surveyDto;
    }


    public void run(){
        try {
            exists = fileExists();
            while (!exists){
                exists = fileExists();
            }

            // if the code reaches here, it means that the file of the survey answers was found, so let's save it to the database
            String response = "";
            String str = new String(surveyDto.getQuestionary());
            String questionary = new String(Base64.getDecoder().decode(str));
            response = (String) ordersService.sendMessageToServer(SPOMSP.Code.SAVE_SURVEY_ANSWER.code, questionary);


        }catch (Exception e){

        }
    }

    private boolean fileExists(){
        Path path = Paths.get("survey_answered.txt");
        if (Files.exists(path)) {
            return true;
        }
        return false;
    }
}

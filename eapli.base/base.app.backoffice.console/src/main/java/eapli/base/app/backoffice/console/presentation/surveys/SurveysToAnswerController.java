package eapli.base.app.backoffice.console.presentation.surveys;

import eapli.base.ordermanagement.domain.domain.model.OrderDto;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.OrdersService;
import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SurveysToAnswerController {

    private final OrdersService ordersService = new OrdersService();
    private final static AuthorizationService authz = AuthzRegistry.authorizationService();

    public Object getSurveysToAnswer(){
        List<SurveyDto> result = new ArrayList<>();
        try {
            String username = authz.session().get().authenticatedUser().username().toString();
            result = (List<SurveyDto>) ordersService.sendMessageToServer(SPOMSP.Code.SURVEYS_TO_ANSWER.code, username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void updateQuestionnary(String questionary){
        Path filePath = Paths.get("survey.js");
        Charset charset = StandardCharsets.UTF_8;
        try {
            String content = Files.readString(filePath, charset);
            questionary = questionary.replace("\n", "\\n");
            StringBuilder stringBuilder = new StringBuilder();
            for(String line: content.split("\n")) {
                if (line.contains("const questionnaire = ")){
                    line = "const questionnaire = \"" + questionary + "\"";
                }
                stringBuilder.append(line + "\n");
            }
            Files.writeString(filePath, stringBuilder.toString(), charset);

        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }
}

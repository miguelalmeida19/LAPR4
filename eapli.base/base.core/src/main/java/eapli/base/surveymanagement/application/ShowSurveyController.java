package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswerServer;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;

import java.awt.*;
import java.net.*;

public class ShowSurveyController extends Thread {

    private final SurveyManagementService surveyManagementService = SurveyRegistry.surveyService() ;

    public void run(){
        showSurvey();
    }

    public static void showSurvey() {
        try {
            String str = "php -S localhost:5000";

            ServerSocket serverSocket = new ServerSocket(5000, 2, InetAddress.getByName("127.0.0.1"));
            final Process process = Runtime.getRuntime().exec(str);


            while (true) {
                Socket connected = serverSocket.accept();
                (new SurveyAnswerServer(connected)).start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Iterable<Survey> allSurveys() {
        return surveyManagementService.allSurveys();
    }
}

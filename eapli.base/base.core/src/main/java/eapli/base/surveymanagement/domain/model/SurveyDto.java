package eapli.base.surveymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyDto {
    private String description;
    private byte[] questionary;

    public SurveyDto(String description, byte[] questionary) {
        this.description = description;
        this.questionary = questionary;
    }

    public byte[] getQuestionary() {
        return questionary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestionary(byte[] questionary) {
        this.questionary = questionary;
    }

    @Override
    public String toString() {
        return "SurveyDto{" +
                "description='" + description + '\'' +
                ", questionary='" + questionary + '\'' +
                '}';
    }
}

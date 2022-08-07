package eapli.base.surveymanagement.domain.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SurveyRule {
    AGE_HIGHER("age higher than"),
    AGE_LOWER("age lower than"),
    GENDER("gender is"),
    ORDERED_PRODUCT_ID("has ordered product"),
    ;

    private final String rule;

    SurveyRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return rule;
    }

    private static final Map<String, SurveyRule> ENUM_MAP = Stream.of(SurveyRule.values())
            .collect(Collectors.toMap(Enum::toString, Function.identity()));

    public static SurveyRule of(final String name) {
        return ENUM_MAP.get(name);
    }
}

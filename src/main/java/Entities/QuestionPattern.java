package Entities;

import Interfaces.Parsable;

import java.time.LocalDate;
import java.util.Objects;

public class QuestionPattern implements Parsable {
    private Service service;
    private QuestionType questionType;
    private ResponseType responseType;
    private LocalDate date;

    public QuestionPattern(Service service, QuestionType questionType, ResponseType responseType, LocalDate date) {
        this.service = service;
        this.questionType = questionType;
        this.responseType = responseType;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || ((getClass() != Question.class && getClass() != QuestionPattern.class) || (o.getClass() != Question.class && o.getClass() != QuestionPattern.class))) {
            return false;
        }
        QuestionPattern question = (QuestionPattern) o;

        return service.equals(question.service) &&
                questionType.equals(question.questionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, questionType);
    }

    //Getters & Setters
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

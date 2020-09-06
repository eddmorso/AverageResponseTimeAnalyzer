package Entities;

import java.time.LocalDate;

public class Question extends QuestionPattern {
    private int responseTime;

    public Question(Service service, QuestionType questionType, ResponseType responseType, LocalDate date, int responseTime) {
        super(service, questionType, responseType, date);
        this.responseTime = responseTime;
    }

    public Question(QuestionPattern questionPattern, int responseTime) {
        super(questionPattern.getService(), questionPattern.getQuestionType(), questionPattern.getResponseType(), questionPattern.getDate());
        this.responseTime = responseTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}

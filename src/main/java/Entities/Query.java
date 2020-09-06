package Entities;

import Interfaces.Parsable;

import java.time.LocalDate;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Query implements Parsable {
    private QuestionPattern questionPattern;
    private LocalDate dateTo;
    private List<Question> questionList;

    public Query(QuestionPattern questionPattern, LocalDate dateTo, List<Question> questionList) {
        this.questionPattern = questionPattern;
        this.dateTo = dateTo;
        this.questionList = questionList;
    }

    public Query(QuestionPattern questionPattern, List<Question> questionList) {
        this.questionPattern = questionPattern;
        this.questionList = questionList;
    }

    private boolean compareQuestion(Question question) {
        boolean isPattern = question.equals(questionPattern);
        boolean isReponse = question.getResponseType().equals(questionPattern.getResponseType());
        boolean isSuitable = isPattern && isReponse;
        LocalDate questionDate = question.getDate();

        if (dateTo != null) {
            isSuitable = isSuitable && !(questionDate.isBefore(questionPattern.getDate()) || questionDate.isAfter(dateTo));
        } else
            isSuitable = isSuitable && questionDate.isEqual(questionPattern.getDate());

        return isSuitable;
    }

    private List<Question> findMatches() {
        return questionList.stream()
                .filter(this::compareQuestion)
                .collect(Collectors.toList());
    }

    public double countAverageResponseTime() {
        IntSummaryStatistics statistics = findMatches().stream()
                .mapToInt(Question::getResponseTime)
                .summaryStatistics();

        return statistics.getAverage();
    }

    //Getters & Setters
    public QuestionPattern getQuestionPattern() {
        return questionPattern;
    }

    public void setQuestionPattern(QuestionPattern questionPattern) {
        this.questionPattern = questionPattern;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}

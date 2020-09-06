package Model;

import Entities.*;
import Exceptions.InsufficientNumberOfArgumentsException;
import Exceptions.SyntaxException;
import Exceptions.UndefinedTypeException;
import Interfaces.Parsable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<Parsable> parse(List<String> input) throws SyntaxException {
        String line;
        String [] arguments;
        ServiceType serviceType;
        QuestionType questionType;
        ResponseType responseType;
        LocalDate [] dateRange;
        Service service;
        QuestionPattern questionPattern;
        List<Parsable> parsableList = new ArrayList<>();

        for (int i = 1; i < input.size(); i++) {
            line = input.get(i);
            arguments = line.split("\\s");
            serviceType = makeServiceType(arguments[1]);
            questionType = makeQuestionType(arguments[2]);
            responseType = makeResponseType(arguments[3]);
            dateRange = makeDate(arguments[4]);
            service = makeService(serviceType);
            questionPattern = makeQuestionPattern(service, questionType, responseType, dateRange[0]);

            switch (arguments[0]) {
                case "C" : parsableList.add(makeQuestion(questionPattern, makeResponseTime(arguments[5])));
                break;
                case "D" : parsableList.add(makeQuery(questionPattern, dateRange, new ArrayList<>()));
                break;
                default: throw new UndefinedTypeException(arguments[0]);
            }
        }
        return parsableList;
    }

    private ServiceType makeServiceType (String typeString) throws InsufficientNumberOfArgumentsException {
        ServiceType serviceType;

        if (typeString.equals("*")) {
            serviceType = new ServiceType();
        } else {
            String [] serviceTypeArguments = typeString.split("\\.");

            switch (serviceTypeArguments.length) {
                case 1 : serviceType = new ServiceType(Integer.parseInt(serviceTypeArguments[0]));
                break;
                case 2 : serviceType = new ServiceType(Integer.parseInt(serviceTypeArguments[0]), Integer.parseInt(serviceTypeArguments[1]));
                break;
                default : throw new InsufficientNumberOfArgumentsException(typeString);
            }
        }
        return serviceType;
    }

    private QuestionType makeQuestionType (String typeString) throws InsufficientNumberOfArgumentsException {
        QuestionType questionType;

        if (typeString.equals("*")) {
            questionType = new QuestionType();
        } else {
            String [] questionTypeArguments = typeString.split("\\.");

            switch (questionTypeArguments.length) {
                case 1 : questionType = new QuestionType(Integer.parseInt(questionTypeArguments[0]));
                break;
                case 2 : questionType = new QuestionType(Integer.parseInt(questionTypeArguments[0]), Integer.parseInt(questionTypeArguments[1]));
                break;
                case 3 : questionType = new QuestionType(Integer.parseInt(questionTypeArguments[0]),
                        Integer.parseInt(questionTypeArguments[1]), Integer.parseInt(questionTypeArguments[2]));
                break;
                default : throw new InsufficientNumberOfArgumentsException(typeString);
            }
        }
        return questionType;
    }

    private ResponseType makeResponseType (String typeString) throws UndefinedTypeException {
        switch (typeString) {
            case "P" : return ResponseType.P;
            case "N" : return ResponseType.N;
            default: throw new UndefinedTypeException(typeString);
        }
    }

    private LocalDate [] makeDate(String dateRangeString) {
        LocalDate [] dateRange = new LocalDate[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (dateRangeString.contains("-")) {
            String [] dates = dateRangeString.split("\\-");
            dateRange[0] = LocalDate.parse(dates[0], formatter);
            dateRange[1] = LocalDate.parse(dates[1], formatter);

            return dateRange;
        }
        dateRange[0] = LocalDate.parse(dateRangeString, formatter);

        return dateRange;
    }

    private int makeResponseTime (String timeString) {
        return Integer.parseInt(timeString);
    }

    private Service makeService (ServiceType serviceType) {
        return new Service(serviceType);
    }

    private QuestionPattern makeQuestionPattern(Service service, QuestionType questionType, ResponseType responseType, LocalDate localDate) {
        return new QuestionPattern(service, questionType, responseType, localDate);
    }

    private Question makeQuestion(QuestionPattern questionPattern, int responseTime) {
        return new Question(questionPattern, responseTime);
    }

    private Query makeQuery(QuestionPattern questionPattern, LocalDate [] dateRange, List<Question> questions) {
        if (dateRange[1] == null) {
            return new Query(questionPattern, questions);
        }
        return new Query(questionPattern, dateRange[1], questions);
    }

}

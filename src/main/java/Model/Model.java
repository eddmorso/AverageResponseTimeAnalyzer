package Model;

import Entities.Query;
import Entities.Question;
import Exceptions.SyntaxException;
import Interfaces.Parsable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private List<Parsable> parsableList;
    private Parser parser;

    public Model(Parser parser) {
        this.parser = parser;
    }

    public List<Double> process(List<String> inputLines) throws SyntaxException {
       parsableList = parser.parse(inputLines);

       assignQuestionsToQueries();

       return parsableList.stream()
               .filter(parsable -> parsable instanceof Query)
               .map(parsable -> {
                   Query query = (Query) parsable;
                   double avg = query.countAverageResponseTime();
                   return avg;
               })
               .collect(Collectors.toList());
    }

    private void assignQuestionsToQueries () {
        List<Question> questions = new ArrayList<>();
        Object parsable;
        Question question;
        Query query;
        for (int i = 0; i < parsableList.size(); i++) {
            parsable = parsableList.get(i);

            if (parsable instanceof Question) {
                question = (Question) parsable;

                questions.add(question);
            } else if (parsable instanceof Query) {
                query = (Query) parsable;

                query.getQuestionList().addAll(questions);
            }
        }
    }
}

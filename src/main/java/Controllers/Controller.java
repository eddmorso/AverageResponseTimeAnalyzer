package Controllers;

import Exceptions.AnalyzerException;
import Exceptions.SyntaxException;
import Exceptions.UnsupportedElementException;
import Exceptions.WrongNumberOfInputException;
import Model.Model;
import View.View;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.List;

public class Controller {
    private View view;
    private Model model;
    private final String SERVICE_TYPE_PATTERN = "\\d+(\\.\\d+)?|\\*";
    private final String QUESTION_TYPE_PATTERN = "\\d+(\\.\\d+(\\.\\d+)?)?|\\*";
    private final String START_DATE_PATTERN = "[0123][0-9]\\.[01][0-9]\\.20[012][0-9]";
    private final String END_DATE_PATTERN = "[0123][0-9]\\.[01][0-9]\\.20[012][0-9]";
    private final String DATE_PATTERN = START_DATE_PATTERN + END_DATE_PATTERN;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void start() throws AnalyzerException {
        List<String> inputList = view.getInput();
        List<Double> listOfAverage;

        check(inputList);

        listOfAverage = model.process(inputList);
        view.printAverageResponseTime(listOfAverage);
    }

    private void checkInputForUnsupportedCharacters(String input) throws UnsupportedElementException {
        if (!input.matches("[0-9NPCD/\\.\\*\\-\\s]+"))
            throw new UnsupportedElementException(input);
    }

    private void checkCorrectnessOfTheInput(String input) throws SyntaxException {
        if (input.matches("^[\\d].*")) {
            checkNumberOfLines(input);
        } else if (input.matches("^C(.*)")) {
            checkQuestion(input);
        } else if (input.matches("^D(.*)")) {
            checkQuery(input);
        } else {
            throw new SyntaxException(input);
        }
    }

    private void checkQuery(String input) throws SyntaxException {
        String [] inputArgs = input.split("\\s");

        if (!inputArgs[0].matches("D")) throw new SyntaxException(input + " " + inputArgs[0]);
        if (!inputArgs[1].matches(SERVICE_TYPE_PATTERN)) throw new SyntaxException(input + " " + inputArgs[1]);
        if (!inputArgs[2].matches(QUESTION_TYPE_PATTERN)) throw new SyntaxException(input + " " + inputArgs[2]);
        if (!inputArgs[3].matches("[PN]")) throw new SyntaxException(input + " " + inputArgs[3]);
        if (inputArgs[4].contains("-")) {
            String [] date = inputArgs[4].split("\\-");
            if (!date[0].matches(START_DATE_PATTERN)) throw new SyntaxException(input + " " + date[0]);
            if (!date[1].matches(END_DATE_PATTERN)) throw new SyntaxException(input + " " + date[1]);
        } else {
            inputArgs[4].matches(START_DATE_PATTERN);
        }
    }

    private void checkQuestion(String input) throws SyntaxException{
        String [] inputArgs = input.split("\\s");

        if (!inputArgs[0].matches("C")) throw new SyntaxException(input + " " + inputArgs[0]);
        if (!inputArgs[1].matches(SERVICE_TYPE_PATTERN)) throw new SyntaxException(input + " " + inputArgs[1]);
        if (!inputArgs[2].matches(QUESTION_TYPE_PATTERN)) throw new SyntaxException(input + " " + inputArgs[2]);
        if (!inputArgs[3].matches("[PN]")) throw new SyntaxException(input + " " + inputArgs[3]);
        if (!inputArgs[4].matches(START_DATE_PATTERN)) throw new SyntaxException(input + " " + inputArgs[4]);
        if (!inputArgs[5].matches("\\d+")) throw new SyntaxException(input + " " + inputArgs[5]);
    }

    private void checkNumberOfLines(String input) throws SyntaxException{
        if (!input.matches("\\d+"))
            throw new SyntaxException(input);
    }

    private void checkQuantityOfItems(List<String> inputList) throws WrongNumberOfInputException {
        if (Integer.parseInt(inputList.get(0)) != inputList.size() - 1)
            throw new WrongNumberOfInputException();
    }

    public void check(List<String> inputList) throws AnalyzerException {
        for (String s : inputList) {
            checkInputForUnsupportedCharacters(s);
            checkCorrectnessOfTheInput(s);
        }
        checkQuantityOfItems(inputList);
    }
}
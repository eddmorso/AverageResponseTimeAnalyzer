import Controllers.Controller;
import Entities.*;
import Exceptions.AnalyzerException;
import Model.*;
import View.View;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View(), new Model(new Parser()));

        try {
            controller.start();
        } catch (AnalyzerException exception) {
            exception.printStackTrace();
        }
    }
}

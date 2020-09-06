package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner SCANNER = new Scanner(System.in);

    public void printAverageResponseTime(List<Double> avgs) {
        avgs.forEach(d -> {
            if (d != 0.0) {
                System.out.println(d);
            } else System.out.println("-");
        });
    }

    public List<String> getInput() {
        List<String> inputList = new ArrayList<>();
        String inputLine;

        showWelcomeMessage();

        while (SCANNER.hasNext()) {
            inputLine = SCANNER.nextLine();
            if (inputLine.equals("q")) {
                break;
            }
            inputList.add(inputLine);
        }
        return inputList;
    }

    private void showWelcomeMessage() {
        System.out.println("Enter your query. Press 'q' to exit");
    }
}

import Controllers.Controller;
import Entities.Service;
import Exceptions.AnalyzerException;
import Exceptions.SyntaxException;
import Model.*;
import View.View;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ControllerTest {
    Controller controller;
    View view;
    Model model;

    private List<String> prepareCorrectListForCheckMethod () {
        List<String> list = new ArrayList<>();

        list.add("7");
        list.add("C 1.1 8.15.1 P 15.10.2012 83");
        list.add("C 1 10.1 P 01.12.2012 65");
        list.add("C 1.1 5.5.1 P 01.11.2012 117");
        list.add("D 1.1 8 P 01.01.2012-01.12.2012");
        list.add("C 3 10.2 N 02.10.2012 100");
        list.add("C 3 10.2 N 02.10.2012 100");
        list.add("D 3 10 P 01.12.2012");

        return list;
    }

    private List<String> prepareIncorrectListForCheckMethod () {
        List<String> list = new ArrayList<>();

        list.add("7");
        list.add("SA 1.1 8.15.1 P 15.10.2012 83");
        list.add("C 1 10.1 P 01.12.2012 65");
        list.add("C 1.1 1111 P 11.2012 asd");
        list.add("1.1 8 P 01.01.2012-01.12.2012");
        list.add("C ;;3 10.2 N 02.10.2012 100");
        list.add("C.2 C 02.10.2012 100");
        list.add("D 3 10 P 01.12.2012");

        return list;
    }

    @Before
    public void prepare() throws SyntaxException {
        view = mock(View.class);
        model = mock(Model.class);
        controller = new Controller(view, model);

        when(view.getInput()).thenReturn(null);
        doNothing().when(view).printAverageResponseTime(null);
        when(model.process(anyList())).thenReturn(null);
    }

    @Test
    public void check_inputOfCorrectData_successfulCheck() throws AnalyzerException {
        List<String> input = prepareCorrectListForCheckMethod();

        controller.check(input);
    }

    @Test(expected = AnalyzerException.class)
    public void check_inputOfCorrectData_failedCheck() throws AnalyzerException {
        List<String> input = prepareIncorrectListForCheckMethod();

        controller.check(input);
    }

    @Test
    public void start_getInputMethodInvocation() throws AnalyzerException {
        Controller controller1 = spy(controller);
        doNothing().when(controller1).check(anyList());

        controller1.start();

        verify(view, times(1)).getInput();
    }

    @Test
    public void start_processMethodInvocation() throws AnalyzerException {
        Controller controller1 = spy(controller);
        doNothing().when(controller1).check(anyList());

        controller1.start();

        verify(model, times(1)).process(anyList());
    }

    @Test
    public void start_printAverageResponseTimeMethodInvocation() throws AnalyzerException {
        Controller controller1 = spy(controller);
        doNothing().when(controller1).check(anyList());

        controller1.start();

        verify(view, times(1)).printAverageResponseTime(anyList());
    }
}

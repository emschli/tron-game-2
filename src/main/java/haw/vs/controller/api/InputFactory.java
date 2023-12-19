package haw.vs.controller.api;

import haw.vs.controller.mock.MockInput;

public class InputFactory {
    public static IInput getInput(){
        return new MockInput();
    }
}

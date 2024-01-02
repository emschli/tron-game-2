package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.nameService.api.INameService;

public class NameServiceThread implements Runnable {
    private final INameService nameService;

    public NameServiceThread(INameService nameService) {
        this.nameService = nameService;
    }

    @Override
    public void run() {

    }
}

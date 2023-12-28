package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.nameService.api.INameService;

public class NameServiceFactory {
    private INameService nameService;


    public NameServiceFactory(INameService nameService) {
        this.nameService = nameService;
    }

    public  INameService getNameService() {
        return nameService;
    }
}

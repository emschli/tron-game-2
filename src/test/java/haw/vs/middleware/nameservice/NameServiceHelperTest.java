package haw.vs.middleware.nameservice;

import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;
import haw.vs.middleware.nameService.api.INameServiceHelper;
import haw.vs.middleware.nameService.api.NameServiceHelperFactory;
import haw.vs.middleware.nameService.impl.NameServiceThread;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

import java.util.ArrayList;
import java.util.List;

import static haw.vs.middleware.nameService.impl.NameServiceData.*;

public class NameServiceHelperTest {
    private static String METHOD_NAME_1 = "methodName1";
    private static String METHOD_NAME_2 = "methodName2";
    private static String METHOD_NAME_3 = "methodName3";
    private static String METHOD_NAME_4 = "methodName4";
    public static void main(String[] args) throws NameServiceException, MiddlewarePropertiesException {
        MiddlewarePropertiesHelper.setPropertiesFile("middleware/nameservice/middleware.properties");

        //Start NameService
        NameServiceThread nameServiceThread = new NameServiceThread();
        Thread nameService = new Thread(nameServiceThread);
        nameService.start();

        INameServiceHelper nameServiceHelper = NameServiceHelperFactory.getNameServiceHelper();

        //Stateless
        System.out.println("------------");
        System.out.println("Stateless");
        List<String> statelessMethods = new ArrayList<>();
        statelessMethods.add(METHOD_NAME_1);
        nameServiceHelper.bind(statelessMethods, STATELESS);
        String response;
        response= nameServiceHelper.lookup(METHOD_NAME_1);
        System.out.println(response);

        //Stateful
        System.out.println("------------");
        System.out.println("Stateful");
        List<String> statefulMethods = new ArrayList<>();
        statefulMethods.add(METHOD_NAME_2);
        statefulMethods.add(METHOD_NAME_3);
        nameServiceHelper.bind(statefulMethods, STATEFUL);
        response = nameServiceHelper.lookup(METHOD_NAME_2, 1);
        System.out.println(response);
        response = nameServiceHelper.lookup(METHOD_NAME_3, 1);
        System.out.println(response);

        //Specific
        System.out.println("------------");
        System.out.println("Specific");
        List<String> specificMethods = new ArrayList<>();
        specificMethods.add(METHOD_NAME_4);
        nameServiceHelper.bind(specificMethods, SPECIFIC);
        response = nameServiceHelper.lookupSpecific(METHOD_NAME_4, 1);
        System.out.println(response);
    }
}

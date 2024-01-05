package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.common.properties.MiddlewarePropertiesException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static haw.vs.middleware.nameService.common.NameServiceConstants.*;

public class NameServiceThread implements Runnable {
    private final INameService nameService;

    public NameServiceThread() {
        this.nameService = NameServiceFactory.getNameService();
    }

    @Override
    public void run() {
        //Get Port number
        int port;
        try {
           port = MiddlewarePropertiesHelper.getNameServicePort();
        } catch (MiddlewarePropertiesException e) {
            throw new RuntimeException(e);
        }

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String request = in.readLine();
                String response = "error";
                try {
                    String[] parameters = request.split(":");
                    int nameServiceMethodType = Integer.parseInt(parameters[0]);
                    if (nameServiceMethodType == BIND) {
                        List<String> methodNames = parseMethodNames(parameters[1]);
                        int methodType = Integer.parseInt(parameters[2]);
                        String ip = clientSocket.getInetAddress().toString().replace("/","");
                        response = String.valueOf(nameService.bind(methodNames, methodType, ip));
                    } else if (nameServiceMethodType == LOOKUP_STATELESS) {
                        String methodName = parameters[1];
                        response = nameService.lookup(methodName);
                    } else if (nameServiceMethodType == LOOKUP_STATEFUL) {
                        String methodName = parameters[1];
                        Long stateId = Long.parseLong(parameters[2]);
                        response = nameService.lookup(methodName, stateId);
                    } else if (nameServiceMethodType == LOOKUP_SPECIFIC) {
                        String methodName = parameters[1];
                        Long specificId = Long.parseLong(parameters[2]);
                        response = nameService.lookupSpecific(methodName, specificId);
                    } else {
                        response = "error";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response = "error";
                } finally {
                    out.println(response);
                    out.close();
                    in.close();
                    clientSocket.close();
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    // Beispiel: [aaa, bbb, ccc]
    private List<String> parseMethodNames(String methodNamesString) {
        String[] items = methodNamesString.substring(1, methodNamesString.length() - 1).split(", ");
        return Arrays.asList(items);
    }
}

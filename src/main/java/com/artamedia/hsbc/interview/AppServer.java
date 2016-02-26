package com.artamedia.hsbc.interview;

import com.artamedia.hsbc.interview.rest.TradeProcessor;
import com.artamedia.hsbc.interview.rest.TradeService;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppServer extends AbstractHandler {

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
    }

    public static void main(String[] args) throws Exception {

        Server server1 = createServer(8080, "1", TradeService.class);
        Server server2 = createServer(8081, "2", TradeProcessor.class);

        try {
            server1.start();
            server2.start();
            server1.join();
            server2.join();
        } finally {
            server1.destroy();
            server2.destroy();
        }
    }

    private static Server createServer(int port, String name, Class... resources) {
        ResourceConfig resourceConfig = new ResourceConfig();
        for (Class resource : resources) {
            resourceConfig.register(resource);
        }

        ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));
        Server server = new Server();

        ServerConnector conn = new ServerConnector(server);
        conn.setPort(port);
        conn.setName(name);
        server.addConnector(conn);

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servletHolder, "/*");

        return server;
    }
}

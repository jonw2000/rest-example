package com.artamedia.hsbc.interview;

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
        response.getWriter().println("<h1>Hello World</h1>");
        baseRequest.setHandled(true);
    }

    public static void main(String[] args) throws Exception {

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("com.artamedia.hsbc.interview.rest");

        ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));

        Server server = new Server();
        ServerConnector conn1 = new ServerConnector(server);
        conn1.setPort(8080);
        conn1.setName("1");
        server.addConnector(conn1);

        ServerConnector conn2 = new ServerConnector(server);
        conn2.setPort(8081);
        conn2.setName("2");
        server.addConnector(conn2);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servletHolder, "/*");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }

//        ResourceConfig config = new ResourceConfig();
//        config.packages("com.artamedia.hsbc.interview.rest");
//
//        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
//        Server server = new Server(8080);
//        ServletContextHandler context = new ServletContextHandler(server, "/*");
//        context.addServlet(servlet, "/*");
//
//        try {
//            server.start();
//            server.join();
//        } finally {
//            server.destroy();
//        }
    }
}

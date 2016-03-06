package com.ifusion;

import com.ifusion.configuration.ApplicationConfiguration;
import io.swagger.jersey.config.JerseyJaxrsConfig;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationRunner {
    private final static Logger log = LoggerFactory.getLogger(ApplicationRunner.class);
    private static int PORT;
    private static Server server;

    public static void main(String[] args) throws Exception {
        new ApplicationRunner().run();
    }

    public ApplicationRunner() {
        PORT = ApplicationConfiguration.appPort();
    }

    private void run() throws Exception {
        if(server == null) {
            log.info("Starting User Management");

            // Main
            HandlerCollection handlers = new HandlerCollection();
            WebAppContext mainHandler = new WebAppContext();
            mainHandler.setResourceBase("src/main/webapp/");
            mainHandler.setParentLoaderPriority(true);
            mainHandler.setClassLoader(Thread.currentThread().getContextClassLoader());
            handlers.addHandler(mainHandler);

            // Jersey
            ServletHolder restServlet = new ServletHolder(ServletContainer.class);
            restServlet.setInitOrder(1);
            restServlet.setInitParameter("javax.ws.rs.Application", "com.ifusion.ResourceConfiguration");
            mainHandler.addServlet(restServlet, "/rest/*");

            // Swagger
            ServletHolder swaggerServlet = new ServletHolder(JerseyJaxrsConfig.class);
            swaggerServlet.setInitOrder(2);
            swaggerServlet.setInitParameter("api.version", "1.0");
            swaggerServlet.setInitParameter("swagger.api.basepath", "/rest");
            mainHandler.addServlet(swaggerServlet, "/swagger-io/*");

            // Static Files
            ServletHolder assetServlet = new ServletHolder(DefaultServlet.class);
            assetServlet.setInitOrder(1);
            assetServlet.setInitParameter("useFileMappedBuffer", "false");
            mainHandler.addServlet(assetServlet, "/*");

            // Logger
            RequestLogHandler requestLogHandler = new RequestLogHandler();
            NCSARequestLog requestLog = new NCSARequestLog();
            requestLog.setFilename("request.log");
            requestLog.setRetainDays(5);
            requestLog.setAppend(true);
            requestLog.setExtended(true);
            requestLog.setLogCookies(false);
            requestLog.setLogTimeZone("GMT");
            requestLogHandler.setRequestLog(requestLog);
            handlers.addHandler(requestLogHandler);

            // Server
            log.info("Starting Embedded Server on port " + PORT);

            server = new Server(PORT);
            server.setHandler(handlers);
            server.start();
            log.info("Application ready at http://localhost:" + PORT);
        }
    }
}

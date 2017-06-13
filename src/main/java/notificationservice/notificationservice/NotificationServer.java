package notificationservice.notificationservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class NotificationServer {
	private static final int SERVLET_PORT = 8282;
	private static Server server = null;
	public static void main( String[] args ){
		
		startServer();
	}

	
	/**Necessary method to start the jetty server.*/
	public static void startServer(){
		ResourceConfig config = new ResourceConfig();
		config.packages("notificationservice.notificationservice");
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));

		server = new Server(SERVLET_PORT);
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/*");


		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally 
		{
			if(server != null && server.isRunning()){
				server.destroy();
			}
		}    
	
	}
	/**
	 * Necessary method to terminate the jetty web server
	 * */
	public static void shutDownServer(){
		if(server != null){
			try{
				server.stop();
			}catch(Exception e){
				e.printStackTrace();
			}

		}
	}


	/**
	 * For situations like initializing a test case it is necessary to check whether the server is started completely before running the test cases.
	 * */
	public static boolean isRunning(){
		return server != null && server.isStarted();
	}
}

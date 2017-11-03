package com.rudra.aks;

public class SpringWebInit //implements WebApplicationInitializer
{
/*
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext	appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(AppConfiguration.class);
		//servletContext.addListener(new ContextLoaderListener(appContext));
		appContext.setServletContext(servletContext);
		
		ServletRegistration.Dynamic registration = servletContext.addServlet("appServlet", new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
	}
*/
	/* public void onStartup(ServletContext container) {
		 *      // Create the 'root' Spring application context
		 *      AnnotationConfigWebApplicationContext rootContext =
		 *        new AnnotationConfigWebApplicationContext();
		 *      rootContext.register(AppConfig.class);
		 *
		 *      // Manage the lifecycle of the root application context
		 *      container.addListener(new ContextLoaderListener(rootContext));
		 *
		 *      // Create the dispatcher servlet's Spring application context
		 *      AnnotationConfigWebApplicationContext dispatcherContext =
		 *        new AnnotationConfigWebApplicationContext();
		 *      dispatcherContext.register(DispatcherConfig.class);
		 *
		 *      // Register and map the dispatcher servlet
		 *      ServletRegistration.Dynamic dispatcher =
		 *        container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		 *      dispatcher.setLoadOnStartup(1);
		 *      dispatcher.addMapping("/");
		 *    }*/
}

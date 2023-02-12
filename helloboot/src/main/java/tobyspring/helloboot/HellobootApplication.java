package tobyspring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Configuration
public class HellobootApplication {

    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(HellobootApplication.class);
        applicationContext.refresh();


        // DispatcherServlet 으로 대체
//        new HttpServlet() {
//            @Override
//            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                // 인증, 보안, 다국어 등 공통 기능
//                if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
//                    String name = req.getParameter("name");
//
//                    HelloController helloController = applicationContext.getBean(HelloController.class);
//                    String ret = helloController.hello(name);
//
//                    resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
//                    resp.getWriter().println(ret);
//                } else {
//                    resp.setStatus(HttpStatus.NOT_FOUND.value());
//                }
//            }
//        }

    }
}

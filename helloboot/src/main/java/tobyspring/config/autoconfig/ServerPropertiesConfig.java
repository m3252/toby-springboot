package tobyspring.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;

import java.util.Objects;

@MyAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    public ServerProperties serverProperties(Environment environment) {
        return Binder.get(environment).bind("", ServerProperties.class).get();
//        ServerProperties serverProperties = new ServerProperties();
//        serverProperties.setContextPath(environment.getProperty("contextPath"));
//        serverProperties.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("port"))));
//        return serverProperties;
    }
}

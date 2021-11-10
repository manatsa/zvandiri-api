package zw.org.zvandiri;

import org.springdoc.data.rest.SpringDocDataRestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;


@SpringBootApplication
@Import(SpringDocDataRestConfiguration.class)
public class BiDataRestApiApplication extends SpringBootServletInitializer implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;


    public static void main(String[] args) {
        SpringApplication.run(BiDataRestApiApplication.class, args);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.setBasePath("/rest");

        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(e -> e.getJavaType()).collect(Collectors.toList()).toArray(new Class[0]));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

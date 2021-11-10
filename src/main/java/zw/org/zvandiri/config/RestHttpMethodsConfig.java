package zw.org.zvandiri.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import zw.org.zvandiri.business.domain.BaseEntity;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Configuration
public class RestHttpMethodsConfig implements RepositoryRestConfigurer {



    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig,
                                                     CorsRegistry cors) {
        ExposureConfiguration config = restConfig.getExposureConfiguration();
        config.forDomainType(BaseEntity.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH));
        config.forDomainType(BaseEntity.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PUT));
        config.forDomainType(BaseEntity.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.POST));
        config.forDomainType(BaseEntity.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.DELETE));
    }
}
package zw.org.zvandiri.config;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import zw.org.zvandiri.business.domain.projections.BaseProjection;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Component
public class ProjectionLinksProcessor implements RepresentationModelProcessor<EntityModel<BaseProjection>> {

    @Override
    public EntityModel<BaseProjection> process(EntityModel<BaseProjection> model) {
        model.removeLinks();
        return EntityModel.of(model.getContent());
    }

}
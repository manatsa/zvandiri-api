package zw.org.zvandiri.config;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import zw.org.zvandiri.business.domain.BaseEntity;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Component
public class EntityLinksProcessor implements RepresentationModelProcessor<EntityModel<BaseEntity>> {

    @Override
    public EntityModel<BaseEntity> process(EntityModel<BaseEntity> model) {
        model.removeLinks();
        return EntityModel.of(model.getContent());
    }

}
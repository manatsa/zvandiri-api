package zw.org.zvandiri.business.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.Cadre;

import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */

@Repository
@RepositoryRestResource
public interface CadreRepo extends CrudRepository<Cadre, String> {

    @Query(value = "from Cadre  t")
    List<Cadre> findAll( Pageable pageable);

    @Query(value = "from Cadre t  left join  fetch  t.primaryClinic f  inner join fetch " +
            "t.district d left join fetch t.province p where p.name=:province ")
    List<Cadre> findAllByProvince(@Param("province") String province, Pageable pageable);

    @Query(value = "from Cadre t  left join  fetch  t.primaryClinic f  inner join fetch" +
            " t.district d where d.name=:district  ")
    List<Cadre> findAllByDistrict(@Param("district") String district, Pageable pageable);

    @Query(value = "from Cadre t left join  fetch  t.primaryClinic f  " +
            "where f.name=:facility ")
    List<Cadre> findAllByFacility(@Param("facility") String facility, Pageable pageable);
}

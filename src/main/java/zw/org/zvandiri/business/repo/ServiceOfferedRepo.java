/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zw.org.zvandiri.business.domain.ServiceOffered;

import java.util.List;


/**
 *
 * @author manatsachinyeruse@gmail.com
 */

public interface ServiceOfferedRepo  extends CrudRepository<ServiceOffered, String> {

    @Query(value = "from ServiceOffered  t")
    List<ServiceOffered> findAll(Pageable pageable);

}
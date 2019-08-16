package io.leasingninja.sales.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractDatabaseEntityRepository extends CrudRepository<ContractDatabaseEntity, String>{

}

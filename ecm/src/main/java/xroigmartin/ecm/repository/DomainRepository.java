package xroigmartin.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xroigmartin.ecm.model.domain.Domain;

public interface DomainRepository extends JpaRepository<Domain, Long>{

}

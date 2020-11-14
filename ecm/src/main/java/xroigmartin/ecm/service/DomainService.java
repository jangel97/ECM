package xroigmartin.ecm.service;

import java.util.List;

import xroigmartin.ecm.model.domain.Domain;

public interface DomainService {
	List<Domain> findAllDomains();
	Domain getDomainById(Long id);
	Domain storeDomain(Domain domain);
	Domain changeStateDomain(Long id);	
}

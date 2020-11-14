package xroigmartin.ecm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.repository.DomainRepository;
import xroigmartin.ecm.service.DomainService;

@Service
public final class DomainServiceImpl implements DomainService {

	@Autowired
	private DomainRepository domainRepository; 
	
	@Override
	public List<Domain> findAllDomains() {
		return domainRepository.findAll();
	}

	@Override
	public Domain getDomainById(Long id) {
		return domainRepository.findById(id).orElse(null);
	}

	@Override
	public Domain storeDomain(Domain domain) {
		return domainRepository.save(domain);
	}

	@Override
	public Domain changeStateDomain(Long id) {
		Domain domain = this.getDomainById(id);
		if(domain != null) {
			domain.changeEnable();
		}
		return domain;
	}

}

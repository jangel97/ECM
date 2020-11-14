package xroigmartin.ecm.service.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.repository.DomainRepository;
import xroigmartin.ecm.service.impl.DomainServiceImpl;

class DomainServiceTest {
	
	@Mock
	private DomainRepository domainRepositoryMock;
	
	@InjectMocks
	private DomainServiceImpl domainServiceMock;
	
	private Domain domain1, domain2;
	private List<Domain> domainListMock;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		domain1 = new Domain(1L, "test1", "test1", true);
		domain2 = new Domain(2L, "test2", "test2", false);
		domainListMock = new ArrayList<>();
		domainListMock.add(domain1);
		domainListMock.add(domain2);
	}

	@Test
	void if_not_exists_domains_return_empty_list() {
		
		List<Domain> domainListMock = new ArrayList<>();
		
		when(domainRepositoryMock.findAll()).thenReturn(domainListMock);
		
		List<Domain> domainList = domainServiceMock.findAllDomains();
		
		assertTrue(domainList.isEmpty());
	}
	
	@Test
	void if_exists_domains_not_return_empty_list() {
		
		List<Domain> domainListMock = new ArrayList<>();
		
		domainListMock.add(domain1);
		
		when(domainRepositoryMock.findAll()).thenReturn(domainListMock);
		
		List<Domain> domainList = domainServiceMock.findAllDomains();
		
		assertFalse(domainList.isEmpty());
	}
	
	@Test
	void when_save_domain_call_method_save() {

		when(domainRepositoryMock.save(domain1)).thenReturn(domain1);
		
		domainServiceMock.storeDomain(domain1);
		
		verify(domainRepositoryMock, times(1)).save(domain1);
		
	}
	
	@Test
	void if_search_domain_with_id_and_not_exists_domain_return_null() {
		when(domainRepositoryMock.findById(1L)).thenReturn(Optional.empty());
		
		Domain domainResult = domainServiceMock.getDomainById(1L);
		
		assertNull(domainResult);
	}
	
	@Test
	void if_search_domain_with_id_and_exists_domain_return_domain() {

		when(domainRepositoryMock.findById(1L)).thenReturn(Optional.of(domain1));
		
		Domain domainResult = domainServiceMock.getDomainById(1L);
		
		assertTrue(domainResult.equals(domain1));
	}
	
	@Test
	void if_damain_is_enable_change_to_disabled() {
	
		when(domainRepositoryMock.findById(1L)).thenReturn(Optional.of(domain1));
		when(domainRepositoryMock.save(domain1)).thenReturn(domain1);
		
		domainServiceMock.changeStateDomain(1L);
		
		assertFalse(domain1.getEnable());
	}

	@Test
	void if_damain_is_disabled_change_to_enabled() {
	
		when(domainRepositoryMock.findById(2L)).thenReturn(Optional.of(domain2));
		when(domainRepositoryMock.save(domain2)).thenReturn(domain2);
		
		
		domainServiceMock.changeStateDomain(2L);
		
		assertTrue(domain1.getEnable());
	}
}

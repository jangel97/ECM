package xroigmartin.ecm.service.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.repository.domain.DomainRepository;

class DomainServiceTest {
	
	@Mock
	private DomainRepository domainRepositoryMock;
	
	@InjectMocks
	private DomainService domainServiceMock;
	
	private Domain domain1, domain2;
	private List<Domain> domainListMock;
	
	void setUp() {
		domain1 = new Domain(1L, "test1", "test1", true);
		domain2 = new Domain(2L, "test2", "test2", true);
		domainListMock = new ArrayList<>();
		domainListMock.add(domain1);
		domainListMock.add(domain2);
	}

	@Test
	void it_should_return_empty_list_test() {
		
		when(domainRepositoryMock.findAll()).thenReturn(new ArrayList<>());
		
		List<Domain> domainList = domainServiceMock.findAll();
		
		assertTrue(domainList.isEmpty());
	}
	
	@Test
	void it_should_not_return_empty_list_test() {
		
		when(domainRepositoryMock.findAll()).thenReturn(domainListMock);
		
		List<Domain> domainList = domainServiceMock.findAll();
		
		assertFalse(domainList.isEmpty());
	}
	
	@Test
	void it_should_insert_new_domain_test() {
		when(domainRepositoryMock.save(domain1)).thenReturn(null);
		
		domainServiceMock.save();
	}
	
	@Test
	void it_should_not_found_domain_test() {
		when(domainRepositoryMock.findById(1L)).thenReturn(Optional.empty());
		
		Domain domainResult = domainServiceMock.findById(1L);
		
		assertNull(domainResult);
	}
	
	@Test
	void it_should_found_domain_test() {

		when(domainRepositoryMock.findById(1L)).thenReturn(Optional.of(domain1));
		
		Domain domainResult = domainServiceMock.findById(1L);
		
		assertTrue(domainResult.equals(domain1));
	}
	
	@Test
	void it_should_change_state_enable_test() {		
		when(domainRepositoryMock.findById(1L)).thenReturn(Optional.of(domain1));
		when(domainRepositoryMock.save(domain1)).thenReturn(null);
		
		domainServiceMock.changeState();
	}

}

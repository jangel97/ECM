package xroigmartin.ecm.repository;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import xroigmartin.ecm.model.domain.Domain;

@DataJpaTest
@DirtiesContext
class DomainRepositoryTest {
	
	@Autowired
	private DomainRepository domainRepository;
	
	private Domain domain;
	private Domain anotherDomain;

	@BeforeEach
	void setUp() throws Exception {
		domain = new Domain("test1", "test1", true);
		anotherDomain = new Domain("test2", "test2", false);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void searchAllDomainsAndReturnList() {
		Domain domainSave = domainRepository.save(domain);
		Domain anotherDomainSave = domainRepository.save(anotherDomain);
		
		assertThat(Arrays.asList(domainSave, anotherDomainSave), 
					containsInAnyOrder(domainRepository.findAll().toArray()));
	}
	
	@Test
	void searchAllDomainsButTheseNotExistsAndReturnEmptyList() {
		assertTrue(domainRepository.findAll().isEmpty());
	}
	
	@Test
	void searchDomainById() {
		Domain domainSave =  domainRepository.save(domain);

		assertTrue(domainRepository.findById(domainSave.getId()).isPresent());
	}
	
	@Test
	void searchDomainByIdAndNotFound() {				
		assertFalse(domainRepository.findById(1L).isPresent());
	}
	
	@Test
	void storeDomain() {
		Domain domainSave = domainRepository.save(domain);
		assertTrue(domainSave.getId() > 0);
	}
	
	@Test
	void deleteDomainByEntity() {
		Domain domainSave = domainRepository.save(domain);
		domainRepository.delete(domainSave);
		assertFalse(domainRepository.findById(domainSave.getId()).isPresent());
	}
	
	@Test
	void deleteDomainById() {
		Domain domainSave = domainRepository.save(domain);
		domainRepository.deleteById(domainSave.getId());;
		assertFalse(domainRepository.findById(domainSave.getId()).isPresent());
	}

}

}

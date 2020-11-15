package xroigmartin.ecm.web.controller.domain;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.service.DomainService;

@Controller
@RequestMapping("/domain")
public class DomainServiceController {

	@Autowired
	private DomainService domainService;
	
	@GetMapping("/")
	public String getAllDomains(Model model) {
		List<Domain> domainList = domainService.findAllDomains();
		model.addAttribute("domainList", domainList);
		return "domain/domain_list";
	}
	
	@GetMapping("/{id}")
	public String getDomainById(@PathVariable Long id, Model model) {
		Domain domain = domainService.getDomainById(id);
		model.addAttribute("domain", domain);
		return "domain/domain";
	}
	
	
	@GetMapping("/new")
	public String newDomain(Model model) {
		model.addAttribute("domain", new Domain());
		return "domain/form_domain";
	}
	
	@PostMapping("/")
	public String storeDomain(@Valid Domain domain, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "domain/form_domain";
		}
		else {
			Domain domainStore = null;
			if(domain.getId() == null) {
				domainStore = new Domain(domain.getCodeDomain(), domain.getDescription());
			}
			else {
				domainStore = domainService.getDomainById(domain.getId());
				domainStore.changeCodeDomain(domain.getCodeDomain());
				domainStore.changeDescription(domain.getDescription());
			}
		}
		
		return "redirect:/domain/";
	}
	
	@GetMapping({"/disable/{id}", "/enable/{id"})
	public String changeDomainState(@PathVariable Long id, Model model) {
		domainService.changeStateDomain(id);
		return "redirect:/domain/";
	}
}

package xroigmartin.ecm.model.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
final public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Size(min = 3, max = 5)
    private String codeDomain;
	
    private String description;
    
    @Column(columnDefinition = "boolean default true")
    private Boolean enable = true;

    public Domain() {
    }

    public Domain(String codeDomain) {
        this.codeDomain = codeDomain;
    }

    public Domain(String codeDomain, String description) {
        this.codeDomain = codeDomain;
        this.description = description;
    }

    public Domain(String codeDomain, String description, Boolean enable) {
        this.codeDomain = codeDomain;
        this.description = description;
        this.enable = enable;
    }

    public Domain(Long id, String codeDomain, String description, Boolean enable) {
        this.id = id;
        this.codeDomain = codeDomain;
        this.description = description;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeDomain() {
        return codeDomain;
    }
    
    public void changeCodeDomain(String newCodeDomain) {
    	this.codeDomain = newCodeDomain;
    }

    public String getDescription() {
        return description;
    }
    
    public void changeDescription(String newDescription) {
    	this.description = newDescription;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void changeEnable() {
    	this.enable = !this.enable;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, codeDomain);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Domain other = (Domain) obj;
        if (codeDomain == null) {
            if (other.codeDomain != null)
                return false;
        } else if (!codeDomain.equals(other.codeDomain))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

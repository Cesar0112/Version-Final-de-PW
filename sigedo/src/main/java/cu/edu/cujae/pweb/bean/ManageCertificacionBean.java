package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CertificacionDto;
import cu.edu.cujae.pweb.service.CertificacionService;

@Component 
@ManagedBean
@ViewScoped // Este es el alcance para trabajar con Ajax
public class ManageCertificacionBean {
     
	private List<CertificacionDto> lista;

	@Autowired
	private CertificacionService certificacionService;

	public ManageCertificacionBean() {

	}
	public ManageCertificacionBean(List<CertificacionDto> lista, CertificacionService certificacionService) {
		super();
		this.lista = lista;
		this.certificacionService = certificacionService;
	}

	@PostConstruct
	public void init() {
		lista = lista == null ? certificacionService.getCertificaciones() : lista;

	}

	public List<CertificacionDto> getLista() {
		return lista;
	}

	public void setLista(List<CertificacionDto> lista) {
		this.lista = lista;
	}
	
}

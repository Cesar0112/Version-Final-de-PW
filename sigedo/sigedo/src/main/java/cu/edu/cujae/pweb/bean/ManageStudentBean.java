package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.MunicipalityDTO;
import cu.edu.cujae.pweb.dto.StudentDto;

import cu.edu.cujae.pweb.service.*;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component // Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped // Este es el alcance utilizado para trabajar con Ajax
public class ManageStudentBean {

	private StudentDto studentDto;
	private StudentDto selectedStudent;
	private List<StudentDto> students;
	private List<MunicipalityDTO> municipalities;

//	puede ser que necesite implementar los municipios a los q pertence el estudiante

	/*
	 * @Autowired es la manera para inyectar una dependencia/clase anotada
	 * con @service en spring Tener en cuenta que lo que se inyecta siempre es la
	 * interfaz y no la clase
	 */
	@Autowired
	private StudentService studentService;

	@Autowired
	private MunicipalityService municipalityService;

	public ManageStudentBean() {

	}

	// Esta anotacioon permite que se ejecute code luego de haberse ejecuta el
	// constructor de la clase.
	@PostConstruct
	public void init() {
		students = students == null ? studentService.getStudents() : students;
		municipalities = municipalityService.getMunicipalities();
	}

	// Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedStudent = new StudentDto();
	}

	// Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
//		List<RoleDto> roles = this.selectedUser.getRoles(); puede que implemente esto mismo pero para los municipios
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}

	// Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar
	// al usuario
	public void saveStudent() {
		if (this.selectedStudent.getId() == null) {
			this.selectedStudent.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			this.selectedStudent.setNewRecord(true);
			// TODO hacer lo mismo pero pa los municipios
//            List<RoleDto> rolesToAdd = new ArrayList<RoleDto>();
//            for(int i = 0; i < this.selectedRoles.length; i++) {
//            	rolesToAdd.add(roleService.getRolesById(selectedRoles[i]));
//            }

			this.students.add(this.selectedStudent);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_added"); // Este code
																										// permite
																										// mostrar un
																										// mensaje
																										// exitoso
																										// (FacesMessage.SEVERITY_INFO)
																										// obteniendo el
																										// mensage desde
																										// el fichero de
																										// recursos, con
																										// la llave
																										// message_student_added
		} else {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_edited");
		}

		PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");// Este code permite cerrar el dialog
																				// cuyo id es manageStudentDialog. Este
																				// identificador es el widgetVar
		PrimeFaces.current().ajax().update("form:dt-students");// Este code es para refrescar el componente con id
																// dt-students que se encuentra dentro del formulario
																// con id form
	}

	// Permite eliminar un estudiante
	public void deleteStudent() {
		try {
			this.students.remove(this.selectedStudent);
			this.selectedStudent = null;
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_removed");
			PrimeFaces.current().ajax().update("form:dt-students");// Este code es para refrescar el componente con id
																	// dt-students que se encuentra dentro del
																	// formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public StudentDto getStudentDto() {
		return studentDto;
	}

	public void setStudentDto(StudentDto studentDto) {
		this.studentDto = studentDto;
	}

	public StudentDto getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(StudentDto selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public List<StudentDto> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDto> students) {
		this.students = students;
	}

	public List<MunicipalityDTO> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<MunicipalityDTO> municipalities) {
		this.municipalities = municipalities;
	}
}

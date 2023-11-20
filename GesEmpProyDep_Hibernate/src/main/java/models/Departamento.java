package models;

import java.util.List;
import java.util.Set;


import models.Empleado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departamento")
@NamedQueries({
	@NamedQuery(name="Departamento.findAll", 
			query="SELECT d FROM Departamento d"),
})
public class Departamento {
	

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Departamento;
	
	private String nombreDepartamento;
	
	@OneToOne(cascade= {CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.EAGER)
	@JoinColumn(nullable=true,name="jefe")
	private Empleado jefe;
	
	@OneToMany(mappedBy = "departamento",cascade= {CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<Empleado> empleados;
	

	public Departamento (String nombre,Empleado jefe) {
		this.nombreDepartamento=nombre;
		this.jefe=jefe;
	}
	
	
}

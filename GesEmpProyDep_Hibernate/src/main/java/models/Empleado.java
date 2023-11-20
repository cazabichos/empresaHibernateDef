package models;

import java.util.List;
import java.util.Set;


import models.Empleado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleado;
	
	private String nombreEmpleado;
	
	private int salario;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(nullable=true,name="departamento")
	private Departamento departamento;
	
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	private List<Proyecto> proyectos;
	
	
	
}

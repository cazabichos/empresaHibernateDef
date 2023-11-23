package controllers;
import java.util.List;
import java.util.Optional;

import models.Departamento;
import models.Empleado;
import models.Proyecto;
import repositories.*;
import repositories.departamentos.DepartamentosRepositoryImpl;
import repositories.empleados.EmpleadosRepositoryImpl;
import repositories.proyectos.ProyectosRepositoryImpl;


public class EmpresaController {
	
	// Repositorios para interactuar con la base de datos
	private DepartamentosRepositoryImpl departamentoCont;
	private EmpleadosRepositoryImpl empleadosCont;
	private ProyectosRepositoryImpl proyectosCont;
	
	// Constructor que inicializa los repositorios
	public EmpresaController (DepartamentosRepositoryImpl departamentoCont,EmpleadosRepositoryImpl empleadosCont,ProyectosRepositoryImpl proyectosCont) {
		this.departamentoCont=departamentoCont;
		this.empleadosCont=empleadosCont;
		this.proyectosCont=proyectosCont;
	}
	
	/*
	 * Métodos para operaciones relacionadas con Departamentos
	 */
	// Mostrar todos los departamentos
	public List<Departamento> buscarDepartamentos() {
		return departamentoCont.findAll();
	}

	// Buscar departamentos por ID
	public Optional<Departamento> findByIdDepartamento(Integer id) {
		return departamentoCont.findById(id);
	}
	
	// Agregar un nuevo departamento
	public boolean saveDepartamento(Departamento departamento) {
		return departamentoCont.save(departamento);
	}
	
	// Eliminar un departamento existente
	public boolean deleteDepartamento(Departamento departamento) {
		return departamentoCont.delete(departamento);
	}
	
	
	/*
	 * Métodos para operaciones relacionadas con Empleados
	 */
	// Mostrar todos los empleados
	public List<Empleado> buscarEmpleados() {
		return empleadosCont.findAll();
		
	}
	
	// Buscar empleados por ID
	public Optional<Empleado> findByIdEmpleado(Integer id) {
		return empleadosCont.findById(id);
	}
	
	// Agregar un nuevo empleados
	public boolean saveEmpleado(Empleado empleado) {
		return empleadosCont.save(empleado);
	}
	
	// Eliminar un empleados existente
	public boolean deleteEmpleado(Empleado empleado) {
		return empleadosCont.delete(empleado);
	}
	

	/*
	 * Métodos para operaciones relacionadas con Proyectos
	 */
	// Mostrar todos los proyectos
	public List<Proyecto> buscarProyectos() {
		return proyectosCont.findAll();
		
	}
	
	// Buscar proyectos por ID
	public Optional <Proyecto> findByIdProyecto(Integer id) {
		return proyectosCont.findById(id) ;
	}
	
	// Agregar un nuevo proyectos
	public boolean saveProyecto(Proyecto proyecto) {
		return proyectosCont.save(proyecto);
	}
	
	// Eliminar un proyectos existente
	public boolean deleteProyecto(Proyecto proyecto) {
		return proyectosCont.delete(proyecto);
	}
	
}

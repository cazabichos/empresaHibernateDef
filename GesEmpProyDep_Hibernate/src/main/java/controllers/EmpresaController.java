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
	
	private DepartamentosRepositoryImpl departamentoCont;
	private EmpleadosRepositoryImpl empleadosCont;
	private ProyectosRepositoryImpl proyectosCont;
	
	public EmpresaController (DepartamentosRepositoryImpl departamentoCont,EmpleadosRepositoryImpl empleadosCont,ProyectosRepositoryImpl proyectosCont) {
		this.departamentoCont=departamentoCont;
		this.empleadosCont=empleadosCont;
		this.proyectosCont=proyectosCont;
	}
	//Departamentos
	
	public List<Departamento> buscarDepartamentos() {
		return departamentoCont.findAll();
	}
	
	public Optional<Departamento> findByIdDepartamento(Integer id) {
		return departamentoCont.findById(id);
	}
	
	public boolean saveDepartamento(Departamento departamento) {
		return departamentoCont.save(departamento);
	}
	
	public boolean deleteDepartamento(Departamento departamento) {
		return departamentoCont.delete(departamento);
	}
	
	
	//Empleados
	
	public List<Empleado> buscarEmpleados() {
		return empleadosCont.findAll();
		
	}
	
	public Optional<Empleado> findByIdEmpleado(Integer id) {
		return empleadosCont.findById(id);
	}
	
	public boolean saveEmpleado(Empleado empleado) {
		return empleadosCont.save(empleado);
	}
	
	public boolean deleteEmpleado(Empleado empleado) {
		return empleadosCont.delete(empleado);
	}
	
	//Proyectos
	
	public List<Proyecto> buscarProyectos() {
		return proyectosCont.findAll();
		
	}
	
	public Optional <Proyecto> findByIdProyecto(Integer id) {
		return proyectosCont.findById(id) ;
	}
	
	public boolean saveProyecto(Proyecto proyecto) {
		return proyectosCont.save(proyecto);
	}
	
	public boolean deleteProyecto(Proyecto proyecto) {
		return proyectosCont.delete(proyecto);
	}
	
}

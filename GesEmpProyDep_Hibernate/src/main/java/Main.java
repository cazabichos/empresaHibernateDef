import controllers.EmpresaController;
import db.HibernateManager;
import models.Departamento;
import models.Empleado;
import models.Proyecto;
import repositories.departamentos.DepartamentosRepositoryImpl;
import repositories.empleados.EmpleadosRepositoryImpl;
import repositories.proyectos.ProyectosRepositoryImpl;

import java.util.Optional;

import IO.IO;

public class Main {

	public static void main(String[] args) {

		// Creación de instancias para interactuar con la base de datos y el controlador principal
		DepartamentosRepositoryImpl departamento = new DepartamentosRepositoryImpl();
		EmpleadosRepositoryImpl empleados = new EmpleadosRepositoryImpl();
		ProyectosRepositoryImpl proyectos = new ProyectosRepositoryImpl();
		EmpresaController controlador = new EmpresaController(departamento, empleados, proyectos);

		// Manejo de las opciones del menú
		while (true) {
			System.out.println("Selecciona una opción  :\n" + "1. Mostrar departamentos\n" + "2. Buscar departamento\n"
					+ "3. Agregar departamentos\n" + "4. Eliminar departamento\n" + "5. Mostar empleados\n"
					+ "6. Buscar empleado \n" + "7. Añadir empleado \n" + "8. Eliminar empleado \n"
					+ "9.Mostrar proyectos \n" + "10. Buscar proyecto\n" + "11. Agregar proyecto \n"
					+ "12. Eliminar proyecto");

			switch (IO.readInt()) {
			case 1:
				mostrarDepartamentos(controlador);
				break;

			case 2:
				buscarDepartamento(controlador);
				break;

			case 3:
				agregarDepartamento(controlador);
				break;
			case 4:
				eliminarDepartamento(controlador);
				break;
			case 5:
				mostrarEmpleados(controlador);
				break;
			case 6:
				buscarEmpleado(controlador);
				break;
			case 7:
				agregarEmpleado(controlador);
				break;
			case 8:
				eliminarEmpleado(controlador);
				break;
			case 9:
				mostrarProyectos(controlador);
				break;
			case 10:
				buscarProyecto(controlador);
				break;
			case 11:
				agregarProyecto(controlador);
				break;
			case 12:
				eliminarProyecto(controlador);
				break;

			default:
				System.out.println("Opción no válida. Inténtalo de nuevo.");

			}

		}

	}

	/*
	 * Métodos para operaciones relacionadas con Departamentos
	 */
	// Mostrar todos los departamentos
	public static void mostrarDepartamentos(EmpresaController controlador) {
		controlador.buscarDepartamentos().stream().forEach(System.out::println);
	}

	// Buscar departamentos por ID
	public static void buscarDepartamento(EmpresaController controlador) {
		IO.print("Introduce el id del departamento");
		Integer id = IO.readInt();

		Optional<Departamento> departamento = controlador.findByIdDepartamento(id);

		if (departamento.isPresent()) {
			IO.print(departamento.toString());
		} else {
			IO.print("No se ha encontrado el departamento");
		}

	}

	// Agregar un nuevo departamento
	public static void agregarDepartamento(EmpresaController controlador) {

		IO.print("Introduce el nombre del departamento");
		String depar = IO.readString();

		IO.print("Introduce el jefe del partamento");
		Integer idJefe = IO.readInt();

		if (controlador.findByIdEmpleado(idJefe) == null && idJefe != -1) {
			IO.print("No existe el jefe que quieres insertar");
			return;
		}
		Empleado e;
		e = null;
		if (idJefe != -1) {
			e = controlador.findByIdEmpleado(idJefe).get();
		}
		Departamento d = new Departamento(depar, e);

		boolean guardado = controlador.saveDepartamento(d);

		if (guardado) {
			IO.print("Se ha guardado correctamente");
		} else {
			IO.print("No se ha podido guardar");
		}
	}

	// Eliminar un departamento existente
	public static void eliminarDepartamento(EmpresaController controlador) {
		IO.print("Introduce el id del departamento a eliminar");
		Integer idDepartamento = IO.readInt();

		Optional<Departamento> departamento = controlador.findByIdDepartamento(idDepartamento);

		if (controlador.deleteDepartamento(departamento.get())) {
			IO.print("Se ha eliminado correctamente");
		} else {
			IO.print("No se ha podido eliminar el departamento");
		}

	}

	/*
	 * Métodos para operaciones relacionadas con Empleados
	 */
	// Mostrar todos los empleados
	public static void mostrarEmpleados(EmpresaController controlador) {
		controlador.buscarEmpleados().stream().forEach(System.out::println);
	}

	// Buscar empleados por ID
	public static void buscarEmpleado(EmpresaController controlador) {
		IO.print("Introduce el id del empleado");
		Integer id = IO.readInt();

		Optional<Empleado> empleado = controlador.findByIdEmpleado(id);

		if (empleado.isPresent()) {
			IO.print(empleado.toString());
		} else {
			IO.print("No se ha encontrado el empleado");
		}

	}

	// Agregar un nuevo empleado
	public static void agregarEmpleado(EmpresaController controlador) {

		IO.print("Introduce el nombre del empleado");
		String nombre = IO.readString();

		IO.print("Introduce el salario");
		int salario = IO.readInt();

		IO.print("Introduce el departamento (Introduce -1 si no quieres nigun departamento)");
		Integer idDepartamento = IO.readInt();

		if (controlador.findByIdDepartamento(idDepartamento) == null && idDepartamento != -1) {
			IO.print("No existe el departamento");
			return;
		}
		Departamento d;
		d = null;

		if (idDepartamento != -1) {
			d = controlador.findByIdDepartamento(idDepartamento).get();
		}
		Empleado e = new Empleado(nombre, salario, d);

		boolean guardado = controlador.saveEmpleado(e);
		if (guardado) {

			IO.print("Se ha guardado correctamente");
		} else {
			IO.print("No se ha podido guardar");
		}
	}

	// Eliminar un empleado existente
	public static void eliminarEmpleado(EmpresaController controlador) {
		IO.print("Introduce el id del empleado a eliminar");
		Integer idEmpleado = IO.readInt();

		Optional<Empleado> empleado = controlador.findByIdEmpleado(idEmpleado);

		if (controlador.deleteEmpleado(empleado.get())) {
			IO.print("Se ha eliminado correctamente");
		} else {
			IO.print("No se ha podido eliminar el empleado");
		}

	}

	/*
	 * Métodos para operaciones relacionadas con Proyectos
	 */
	// Mostrar todos los proyectos
	public static void mostrarProyectos(EmpresaController controlador) {
		controlador.buscarProyectos().stream().forEach(System.out::println);
	}

	// Buscar proyectos por ID
	public static void buscarProyecto(EmpresaController controlador) {
		IO.print("Introduce el id del proyecto");
		Integer id = IO.readInt();

		Optional<Proyecto> proyecto = controlador.findByIdProyecto(id);

		if (proyecto.isPresent()) {
			IO.print(proyecto.toString());
		} else {
			IO.print("No se ha encontrado el proyecto");
		}

	}

	// Agregar un nuevo proyecto
	public static void agregarProyecto(EmpresaController controlador) {

		IO.print("Introduce el nombre del proyecto");
		String nombreProyecto = IO.readString();

		Proyecto proyecto = new Proyecto(nombreProyecto);
		if (controlador.saveProyecto(proyecto)) {
			IO.print("Se ha guardado el proyecto correctamente");
		} else {
			IO.print("No se ha podido guardar el proyecto");
		}
	}

	// Eliminar un proyecto existente
	public static void eliminarProyecto(EmpresaController controlador) {
		IO.print("Introduce el id del proyecto a eliminar");
		Integer idProyecto = IO.readInt();

		Optional<Proyecto> proyecto = controlador.findByIdProyecto(idProyecto);

		if (controlador.deleteProyecto(proyecto.get())) {
			IO.print("Se ha eliminado correctamente");
		} else {
			IO.print("No se ha podido eliminar el proyecto");
		}

	}
}

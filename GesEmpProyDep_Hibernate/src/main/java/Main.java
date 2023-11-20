import controllers.EmpresaController;
import db.HibernateManager;
import models.Departamento;
import models.Empleado;
import repositories.departamentos.DepartamentosRepositoryImpl;
import repositories.empleados.EmpleadosRepositoryImpl;
import repositories.proyectos.ProyectosRepositoryImpl;
import IO.IO;

public class Main {

	public static void main(String[] args) {
		
		DepartamentosRepositoryImpl departamento=new DepartamentosRepositoryImpl();
		EmpleadosRepositoryImpl empleados=new EmpleadosRepositoryImpl();
		ProyectosRepositoryImpl proyectos=new ProyectosRepositoryImpl();
		
		EmpresaController controlador=new EmpresaController(departamento,empleados,proyectos);

		while (true) {
			System.out.println("Selecciona una opción  :\n" + "1. Mostrar departamentos\n"
					+ "2. Buscar departamento\n" + "3. Agregar departamentos\n" + "4. Eliminar departamento\n"
					+ "5. Mostar empleados\n" + "6. Buscar empleado \n" + "7. Añadir empleado" + "8. Eliminar empleado"
					+ "9.Mostrar proyectos" + "10. Buscar proyecto" + "11. Agregar proyecto" + "12. Eliminar proyecto"
					);

			switch (IO.readInt()) {
			case 1:
				mostrarDepartamentos(controlador);
				break;
				
//			case 2:
//				buscarDepartamento(controlador);
				
			case 3:
				agregarDepartamento(controlador);
				break;
//			case 4:
//				eliminarDepartamento(controlador);
//				break;
//			case 5:
//				mostrarEmpleados(controlador);
//				break;
//			case 6:
//				buscarEmpleado(controlador);
//				break;
//			case 7:
//				añadirEmpleado(controlador);
//				break;
//			case 8:
//				eliminarEmpleado(controlador);
//				break;
//			case 9:
//				mostrarProyectos(controlador);
//				break
//			case 10: 
//				buscarProyecto(controlador);
//				break;
//			case 11:
//				añadirProyecto(controlador);
//				break;
//			case 12:
//				eliminarProyecto(controlador);
//				break;
				
			default:
				System.out.println("Opción no válida. Inténtalo de nuevo.");
				
			}
			
		
		}
		
		
	}
	
	public static void mostrarDepartamentos(EmpresaController controlador) {
		controlador.buscarDepartamentos().stream().forEach(System.out::println);
	}
	
	
	
	public static void agregarDepartamento(EmpresaController controlador) {
		
	IO.print("Introduce el nombre del departamento");
	String depar=IO.readString();
	
	IO.print("Introduce el jefe del partamento");
	Integer idJefe=IO.readInt();
	
	if (controlador.findByIdEmpleado(idJefe)==null) {
		IO.print("No existe el jefe que quieres insertar");
		return;
	}
		Empleado e= controlador.findByIdEmpleado(idJefe).get();
		Departamento d =new Departamento(depar,e);
		
		boolean guardado=controlador.saveDepartamento(d);
		if (guardado) {
			IO.print("Se ha guardado correctamente");
		}
		else {
			IO.print("No se ha podido guardar");
		}
	}
	
	

	
	

}

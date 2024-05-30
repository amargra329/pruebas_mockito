package org.iesvdm.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test doubles that are "fakes" must be tested
 *
 *
 */
public class EmployeeInMemoryRepositoryTest {

	private EmployeeInMemoryRepository employeeRepository;

	private List<Employee> employees;

	@BeforeEach
	public void setup() {
		employees = new ArrayList<>();
		employeeRepository = new EmployeeInMemoryRepository(employees);
	}

	/**
	 * Descripcion del test:
	 * crea 2 Employee diferentes
	 * aniadelos a la coleccion de employees
	 * comprueba que cuando llamas a employeeRepository.findAll
	 * obtienes los empleados aniadidos en el paso anterior
	 */
	@Test
	public void testEmployeeRepositoryFindAll() {
		Employee employee1 = new Employee("1", 342);
		Employee employee2 = new Employee("2", 234);

		employees.add(employee1);
		employees.add(employee2);

		assertThat(employeeRepository.findAll()).isEqualTo(employees);
	}

	/**
	 * Descripcion del test:
	 * salva un Employee mediante el metodo
	 * employeeRepository.save y comprueba que la coleccion
	 * employees contiene solo ese Employee
	 */
	@Test
	public void testEmployeeRepositorySaveNewEmployee() {
		Employee employee1 = new Employee("1", 342);

		ArrayList<Employee> expected = new ArrayList<>();
		expected.add(employee1);

		employeeRepository.save(employee1);

		assertThat(employees).isEqualTo(expected);
	}

	/**
	 * Descripcion del tets:
	 * crea un par de Employee diferentes
	 * aniadelos a la coleccion de employees.
	 * A continuacion, mediante employeeRepository.save
	 * salva los Employee anteriores (mismo id) con cambios
	 * en el salario y comprueba que la coleccion employees
	 * los contiene actualizados.
	 */
	@Test
	public void testEmployeeRepositorySaveExistingEmployee() {
		Employee employee1 = new Employee("1", 342);
		Employee employee2 = new Employee("2", 234);

		employees.add(employee1);
		employees.add(employee2);

		employee1.setSalary(848);
		employee2.setSalary(134);
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);

		assertThat(employees.getFirst()).isEqualTo(employee1);
		assertThat(employees.getLast()).isEqualTo(employee2);
	}
}

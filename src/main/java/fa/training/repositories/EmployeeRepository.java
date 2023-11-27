package fa.training.repositories;

import fa.training.dto.SearchParam;
import fa.training.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByPhone(String phone);

    @Query("from Employee e")
    Page<Employee> findAllEmployee(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.address LIKE %:searchValue%")
    Page<Employee> filterByAddress(@Param("searchValue") String searchValue, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName) LIKE %:searchValue%")
    Page<Employee> filterByName(@Param("searchValue") String searchValue, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.departmentName LIKE %:searchValue%")
    Page<Employee> filterByDepartment(@Param("searchValue") String searchValue, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.phone LIKE %:searchValue%")
    Page<Employee> filterByPhone(@Param("searchValue") String searchValue, Pageable pageable);


}

package fa.training.service;

import fa.training.dto.SearchParam;
import fa.training.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findByPhone(String phone);

    Employee findById(Integer id);

    Page<Employee> getListEmployee(int page, int pageSize, Sort sort);

    Page<Employee> filterListEmployee(int page, int pageSize, Sort sort,String searchValue, String filterValue);


}

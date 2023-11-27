package fa.training.service.impl;

import fa.training.dto.SearchParam;
import fa.training.entities.Employee;
import fa.training.repositories.EmployeeRepository;
import fa.training.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    final private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByPhone(String phone) {
        return employeeRepository.findByPhone(phone);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(new Employee());

    }

    @Override
    public Page<Employee> getListEmployee(int page, int pageSize, Sort sort) {
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return employeeRepository.findAllEmployee(pageable);
    }

    @Override
    public Page<Employee> filterListEmployee(int page, int pageSize, Sort sort, String searchValue, String filterValue) {
        Page<Employee> employees = employeeRepository.findAllEmployee(PageRequest.of(page, pageSize, sort));
        if (!searchValue.isEmpty()) {
            switch (filterValue) {
                case "address":
                    System.out.println("address");
                    employees = employeeRepository.filterByAddress(searchValue, PageRequest.of(page, pageSize, sort));
                    break;
                case "phoneNumber":
                    System.out.println("phoneNumber");
                    employees = employeeRepository.filterByPhone(searchValue, PageRequest.of(page, pageSize, sort));
                    break;
                case "department":
                    System.out.println("department");
                    employees = employeeRepository.filterByDepartment(searchValue, PageRequest.of(page, pageSize, sort));
                    break;
                case "name":
                    System.out.println("name");
                    employees = employeeRepository.filterByName(searchValue, PageRequest.of(page, pageSize, sort));
                    break;
                default:
            }
        }
        return employees;
    }
}
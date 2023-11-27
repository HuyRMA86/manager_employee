package fa.training.controller;

import fa.training.convertor.LocalDateEditor;
import fa.training.dto.AddEmployee;
import fa.training.dto.LoginAccount;
import fa.training.dto.SaveAccount;
import fa.training.dto.SearchParam;
import fa.training.entities.Account;
import fa.training.entities.Employee;
import fa.training.service.AccountService;
import fa.training.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("accountSession")
@RequiredArgsConstructor
//@CrossOrigin(maxAge = 3600)
public class testController {

    final private AccountService accountService;
    final private EmployeeService employeeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
    }

    @GetMapping({"/","/login"})
    public String loginHome(Model model) {
        model.addAttribute("loginAccount", new Account());
        return "login";
    }

    @PostMapping("/home")
    public String loginHome(@ModelAttribute LoginAccount loginAccount, Model model) {
        System.out.println(loginAccount);
        Account account = accountService.findByEmailAndPassword(loginAccount.getEmail(), loginAccount.getPassword());
        if(account == null) {
            model.addAttribute("mess", "You entered the wrong email or password !!!");
            return "login";
        }else {
            model.addAttribute("accountSession", account);
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String homeCMS() {
        return "home";
    }

    @GetMapping("/home/employees")
    public String listEmployee(Model model) {
        Page<Employee> employees = employeeService.getListEmployee(0, 4, Sort.by("id").ascending());
        model.addAttribute("employees", employees.getContent());
        return "employee-list";
    }

    @PostMapping("/home/employees")
    public ResponseEntity<List<Employee>> listEmployee(@RequestBody SearchParam searchParam) {
        int pageCurrent = searchParam.getNumberValue() - 1;
        Page<Employee> employees = employeeService.filterListEmployee(pageCurrent,4, Sort.by("id").ascending(), searchParam.getSearchValue(), searchParam.getFilterValue());

        return ResponseEntity.ok(employees.getContent());
    }

    @GetMapping("/home/addEmployee")
    public String addEmployee(Model model) {
        model.addAttribute("addEmployee", new AddEmployee());
        return "add-employee";
    }

    @PostMapping("/home/addEmployee")
    public String addEmployeeForm(Model model, @Validated @ModelAttribute("addEmployee") AddEmployee addEmployee, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-employee";
        }
        Account account = new Account();
        Employee employee = new Employee();
        BeanUtils.copyProperties(addEmployee, account);
        BeanUtils.copyProperties(addEmployee, employee);
        int status = addEmployee.getStatus() == null ? 0 : 1;
        account.setStatus(status);
        account.setEmployee(employee);

        SaveAccount saveAccount = accountService.save(account, employee);
        model.addAttribute("msg", saveAccount);
        if(saveAccount.getStatus().equals("success")){
            model.addAttribute("addEmployee", new AddEmployee());
        }
        return "add-employee";
    }

    @GetMapping("/home/detailEmployee/{id}")
    public String detailEmployee(@PathVariable Integer id , Model model) {
        System.out.println(id);
        Employee employee = employeeService.findById(id);
        Account account = accountService.findByEmployee_Id(id);
        model.addAttribute("employee1", employee);
        model.addAttribute("account", account);
        return "detail-employee";
    }

    @GetMapping("/logout")
    public String logoutAccount(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }
   

}

package fa.training.service.impl;

import fa.training.dto.SaveAccount;
import fa.training.entities.Account;
import fa.training.entities.Employee;
import fa.training.repositories.AccountRepository;
import fa.training.repositories.EmployeeRepository;
import fa.training.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository;
    final private EmployeeRepository employeeRepository;

    @Override
    public Account findByEmailAndPassword(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public SaveAccount save(Account account, Employee employee) {
        SaveAccount saveAccount = new SaveAccount();
        if (accountRepository.findByEmail(account.getEmail()) != null) {
            saveAccount.setStatus("fail");
            saveAccount.setMessage("Email has been registered!!!");
        } else if(employeeRepository.findByPhone(employee.getPhone()) != null) {
            saveAccount.setStatus("fail");
            saveAccount.setMessage("Phone number has been registered!!!");
        } else {
            System.out.println(accountRepository.save(account));
            saveAccount.setStatus("success");
            saveAccount.setMessage("Save employee success !!!");
        }
        return saveAccount;
    }

    @Override
    public Account findByEmployee_Id(Integer id) {
        return  accountRepository.findByEmployee_Id(id).orElse(new Account());
    }
}

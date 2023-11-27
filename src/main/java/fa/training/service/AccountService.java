package fa.training.service;

import fa.training.dto.SaveAccount;
import fa.training.entities.Account;
import fa.training.entities.Employee;

public interface AccountService {
    Account findByEmailAndPassword(String email, String password);

    Account findByEmail(String email);

    SaveAccount save(Account account, Employee employee);

    Account findByEmployee_Id(Integer id);
}

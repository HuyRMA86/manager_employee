package fa.training.repositories;

import fa.training.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);

    Account findByEmailAndPassword(String email,String password);

    Optional<Account> findByEmployee_Id(Integer id);
}

package application.repository;


import application.domain.Account;
import application.domain.enums.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    List<Account> findByAccountType(AccountType accountType);
    Page<Account> findAllBy(PageRequest pageRequest);
}

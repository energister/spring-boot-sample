package hello;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class ServiceWithTransactionalMethod {
    @Transactional
    public boolean transactional() {
        return TransactionSynchronizationManager.isActualTransactionActive();
    }

    public boolean indirectTransactional() {
        return transactional();
    }
}

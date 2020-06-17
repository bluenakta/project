package dashboard;

import org.springframework.data.repository.CrudRepository;

public interface ShowStatusRepository extends CrudRepository<ShowStatus, Long> {
        ShowStatus findByBookId(Long bookId);
        void deleteByBookId(Long bookId);
}
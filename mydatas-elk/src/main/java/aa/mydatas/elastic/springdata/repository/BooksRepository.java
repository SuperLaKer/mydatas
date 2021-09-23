package aa.mydatas.elastic.springdata.repository;

import org.springframework.data.repository.CrudRepository;
import aa.mydatas.elastic.springdata.domains.BookModel;

public interface BooksRepository extends CrudRepository<BookModel, String> {
}
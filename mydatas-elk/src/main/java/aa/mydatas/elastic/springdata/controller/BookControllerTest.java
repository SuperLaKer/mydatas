package aa.mydatas.elastic.springdata.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import aa.mydatas.elastic.EsMainApplication;
import aa.mydatas.elastic.springdata.domains.BookModel;
import aa.mydatas.elastic.springdata.repository.BooksRepository;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsMainApplication.class)
public class BookControllerTest {

    @Autowired
    BooksRepository booksRepository;

    @Test
    public void findById() {
        Optional<BookModel> repositoryById = booksRepository.findById("CKRqVXkBTNQudRzHkOT_");
        if (repositoryById.isPresent()) {
            BookModel bookModel = repositoryById.get();
            assertNotNull(bookModel);
            System.out.println(bookModel);
        }
    }
}
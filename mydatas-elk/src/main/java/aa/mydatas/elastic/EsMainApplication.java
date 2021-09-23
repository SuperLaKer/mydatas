package aa.mydatas.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import aa.mydatas.elastic.springdata.domains.BookModel;
import aa.mydatas.elastic.springdata.repository.BooksRepository;

@SpringBootApplication
@EnableElasticsearchRepositories
// @ComponentScan({"spring.elasticsearch.web", "spring.elasticsearch.learn"})
@ComponentScan({"aa.mydatas.elastic.springdata"})
public class EsMainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(EsMainApplication.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
        BooksRepository booksRepository = ac.getBean(BooksRepository.class);
        Iterable<BookModel> bookModels = booksRepository.findAll();
        for (BookModel bookModel : bookModels) {
            System.out.println(bookModel);
        }
    }
}

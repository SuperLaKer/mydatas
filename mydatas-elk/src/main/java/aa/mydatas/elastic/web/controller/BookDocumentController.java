package aa.mydatas.elastic.web.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aa.mydatas.elastic.web.model.BookModel;
import aa.mydatas.elastic.web.model.BookRequestVO;

import java.util.List;

/**
 * 文档操作
 *
 * @author ChengJianSheng
 * @date 2019-01-07
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookDocumentController {

    @Autowired
    private BookDocumentService bookDocumentService;

    /**
     * 根据条件，列表分页查询
     * 那个条件存在根据那个条件查询
     * 模拟：根据状态查询status, 直接跟?status=1就行
     */
    @GetMapping("/list")
    public BaseResult list(BookRequestVO bookRequestVO) {
        List<BookModel> bookModels = bookDocumentService.list(bookRequestVO);
        if (null == bookModels) {
            return BaseResult.error();
        }
        return BaseResult.ok(bookModels);
    }

    /**
     * 查看文档
     */
    @GetMapping("/detail/{id}")
    public BaseResult detail(@PathVariable("id") String id) {
        if (null == id) {
            return BaseResult.error("ID不能为空");
        }
        BookModel book = bookDocumentService.detail(id);
        return BaseResult.ok(book);
    }

    /**
     * 添加文档
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody BookModel bookModel) {
        bookDocumentService.save(bookModel);
        log.info("插入文档成功！请求参数: {}", JSON.toJSONString(bookModel));
        return BaseResult.ok();
    }

    /**
     * 修改文档
     */
    @PostMapping("/update")
    public BaseResult update(@RequestBody BookModel bookModel) {
        String id = bookModel.getId();
        if (null == id) {
            return BaseResult.error("ID不能为空");
        }
        BookModel book = bookDocumentService.detail(id);
        if (null == book) {
            return BaseResult.error("记录不存在");
        }
        bookDocumentService.update(bookModel);
        log.info("更新文档成功！请求参数: {}", JSON.toJSONString(bookModel));
        return BaseResult.ok();
    }

    /**
     * 删除文档
     */
    @GetMapping("/delete/{id}")
    public BaseResult delete(@PathVariable("id") String id) {
        if (null == id) {
            return BaseResult.error("ID不能为空");
        }
        bookDocumentService.delete(id);
        return BaseResult.ok();
    }

}
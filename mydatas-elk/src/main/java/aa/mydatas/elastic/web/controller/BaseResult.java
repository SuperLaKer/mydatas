package aa.mydatas.elastic.web.controller;

import lombok.Data;
import aa.mydatas.elastic.web.model.BookModel;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseResult {


    private List<BookModel> data;
    private String message;

    public BaseResult() {
    }

    public BaseResult(List<BookModel> data, String message) {
        this.data = data;
        this.message = message;
    }

    public static BaseResult error() {
        return new BaseResult();
    }

    public static BaseResult error(String err) {
        return new BaseResult(null, err);
    }

    public static BaseResult ok() {
        return new BaseResult(null, "ok");
    }

    public static BaseResult ok(List<BookModel> bookModels) {
        return new BaseResult(bookModels, "ok");
    }

    public static BaseResult ok(BookModel bookModel) {
        ArrayList<BookModel> bookModels = new ArrayList<>();
        bookModels.add(bookModel);
        return new BaseResult(bookModels, "ok");
    }
}

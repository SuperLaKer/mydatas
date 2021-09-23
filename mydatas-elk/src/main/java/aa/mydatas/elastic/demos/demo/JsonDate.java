package aa.mydatas.elastic.demos.demo;


import aa.mydatas.elastic.web.utils.RandomDate;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class JsonDate {

    Map<String, Object> jsonMap1 = new HashMap<>();
    Map<String, Object> jsonMap2 = new HashMap<>();
    Map<String, Object> jsonMap3 = new HashMap<>();
    Map<String, Object> jsonMap4 = new HashMap<>();
    Map<String, Object> jsonMap5 = new HashMap<>();
    Map<String, Object> jsonMap6 = new HashMap<>();
    Map<String, Object> jsonMap7 = new HashMap<>();
    Map<String, Object> jsonMap8 = new HashMap<>();
    Map<String, Object> jsonMap9 = new HashMap<>();
    Map<String, Object> jsonMap10 = new HashMap<>();


    public JsonDate() {

        jsonMap1.put("book_id", 1);
        jsonMap1.put("name", "围城");
        jsonMap1.put("author", "钱钟书");
        jsonMap1.put("category", "中国当代小说");
        jsonMap1.put("price", 29.5);
        jsonMap1.put("status", 1);
        jsonMap1.put("sellReason", "《围城》是钱钟书所著的长篇小说，被誉为\"新儒林外史\"");
        jsonMap1.put("sellTime", RandomDate.getDate());

        jsonMap2.put("book_id", 2);
        jsonMap2.put("name", "倚天屠龙记");
        jsonMap2.put("author", "金庸");
        jsonMap2.put("category", "武侠小说");
        jsonMap2.put("price", 70.4);
        jsonMap2.put("status", 1);
        jsonMap2.put("sellReason", "武林至尊，宝刀屠龙，号令天下，莫敢不从");
        jsonMap2.put("sellTime", RandomDate.getDate());

        jsonMap3.put("book_id", 3);
        jsonMap3.put("name", "神雕侠侣");
        jsonMap3.put("author", "金庸");
        jsonMap3.put("category", "武侠小说");
        jsonMap3.put("price", 70);
        jsonMap3.put("status", 1);
        jsonMap3.put("sellReason", "风陵渡口初相遇，一见杨过误终身");
        jsonMap3.put("sellTime", RandomDate.getDate());

        jsonMap4.put("book_id", 4);
        jsonMap4.put("name", "张爱玲全集");
        jsonMap4.put("author", "张爱玲");
        jsonMap4.put("category", "爱情小说");
        jsonMap4.put("price", 216);
        jsonMap4.put("status", 1);
        jsonMap4.put("sellReason", "《倾城之恋》《琉璃瓦》《金锁记》《连环套》");
        jsonMap4.put("sellTime", RandomDate.getDate());

        jsonMap5.put("book_id", 5);
        jsonMap5.put("name", "重要习惯一个人");
        jsonMap5.put("author", "蕊希");
        jsonMap5.put("category", "爱情小说");
        jsonMap5.put("price", 49.8);
        jsonMap5.put("status", 0);
        jsonMap5.put("sellReason", "所有的煎熬和孤独，都成了我走向你的路。世界欠我一个你，是世界欠的，不是你");
        jsonMap5.put("sellTime", RandomDate.getDate());

        jsonMap6.put("book_id", 6);
        jsonMap6.put("name", "想念");
        jsonMap6.put("author", "张小娴");
        jsonMap6.put("category", "中国当代随笔");
        jsonMap6.put("price", 28);
        jsonMap6.put("status", 0);
        jsonMap6.put("sellReason", "当你偶尔抬起头，看到一只斑斓的蝴蝶翩翩起舞，是否会怀念");
        jsonMap6.put("sellTime", RandomDate.getDate());

        jsonMap7.put("book_id", 7);
        jsonMap7.put("name", "白鹿原");
        jsonMap7.put("author", "陈忠实");
        jsonMap7.put("category", "中国当代小说");
        jsonMap7.put("price", 29.5);
        jsonMap7.put("status", 1);
        jsonMap7.put("sellReason", "茅盾文学奖扛鼎之作");
        jsonMap7.put("sellTime", RandomDate.getDate());

        jsonMap8.put("book_id", 8);
        jsonMap8.put("name", "倾城之恋");
        jsonMap8.put("author", "张爱玲");
        jsonMap8.put("category", "中国当代小说");
        jsonMap8.put("price", 20);
        jsonMap8.put("status", 1);
        jsonMap8.put("sellReason", "热播电视剧《倾城之恋》原著小说张爱玲作品授权正版");
        jsonMap8.put("sellTime", RandomDate.getDate());

        jsonMap9.put("book_id", 9);
        jsonMap9.put("name", "冰与火之歌");
        jsonMap9.put("author", "乔治·R·R·马丁");
        jsonMap9.put("category", "史诗奇幻");
        jsonMap9.put("price", 200);
        jsonMap9.put("status", 1);
        jsonMap9.put("sellReason", "热播电视剧《Game of Thrones》原著小说");
        jsonMap9.put("sellTime", "1996-01-01");

        jsonMap10.put("book_id", 10);
        jsonMap10.put("name", "冰与火之歌");
        jsonMap10.put("author", "乔治·R·R·马丁2");
        jsonMap10.put("category", "史诗奇幻");
        jsonMap10.put("price", 200);
        jsonMap10.put("status", 1);
        jsonMap10.put("sellReason", "热播电视剧《权力的游戏》改编自冰与火之歌，马丁监制");
        jsonMap10.put("sellTime", "1996-01-01");
    }

    public Map<String, Object> getJsonMap1() {
        return jsonMap1;
    }

    public Map<String, Object> getJsonMap2() {
        return jsonMap2;
    }

    public Map<String, Object> getJsonMap3() {
        return jsonMap3;
    }

    public Map<String, Object> getJsonMap4() {
        return jsonMap4;
    }

    public Map<String, Object> getJsonMap5() {
        return jsonMap5;
    }

    public Map<String, Object> getJsonMap6() {
        return jsonMap6;
    }

    public Map<String, Object> getJsonMap7() {
        return jsonMap7;
    }

    public Map<String, Object> getJsonMap8() {
        return jsonMap8;
    }

    public Map<String, Object> getJsonMap9() {
        return jsonMap9;
    }

    public Map<String, Object> getJsonMap10() {
        return jsonMap10;
    }
}

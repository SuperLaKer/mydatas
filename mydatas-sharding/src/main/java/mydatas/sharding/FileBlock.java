package mydatas.sharding;

import aa.slkenv.utils.火绒.DATA;
import aa.slkenv.utils.火绒.FileIter;
import aa.slkenv.utils.火绒.NetStruct;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class FileBlock {
    public static void main(String[] args) {
        List<DATA> tempList = new ArrayList();
        List<DATA> dataList = FileIter.getFiles("E:\\Program Files (x86)\\SogouInput", tempList);
        NetStruct netStruct = new NetStruct(dataList);
        String jsonString = JSON.toJSONString(netStruct);
        System.out.println(jsonString);
    }
}

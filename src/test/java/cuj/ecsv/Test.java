package cuj.ecsv;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author cujamin
 * @date 2020/3/21
 */
public class Test {

    @JSONField
    private String id;

    @org.junit.Test
    public void test(){
        System.out.println(JSON.toJSONString(new Test()));
    }
}

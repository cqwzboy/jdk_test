package com.code.fuqinqin.org.alibaba.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fuqinqin3
 * @date 2020-10-26
 * */
@Data
public class JSONFieldEntity {
    @JSONField(name = "编号", serialize = false, ordinal = 3)
    private long id;
    @JSONField(name = "姓名", ordinal = 4, label = "hahahhaa")
    private String name;
    @JSONField(name = "年龄", deserialize = false, ordinal = 6)
    private int age;
    @JSONField(name = "生日", format = "yyyy/MM/dd HH:mm:dd", ordinal = 2, serializeUsing = DateValueSerializer.class)
    private Date birthday;
    @JSONField(name = "兴趣爱好", deserialize = false, ordinal = 5)
    private List<String> hobies;
    @JSONField(name = "成绩", ordinal = 1)
    private Map<String, Integer> grades;
    @JSONField(jsonDirect = true)
    private String content;
}

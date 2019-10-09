package com.laurus.wmcs.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laurus.wmcs.context.UserContext;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
public class Result {

    private boolean ok;
    private Object data;
    private String message;

    public Result() {
        this.ok = false;
    }

    public Result(boolean ok, String message, Object data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }


    //通用异常处理
    public Result validateUser() {
        Result result = new Result();
        if (ObjectUtils.isEmpty(UserContext.getId())) {
            result.setOk(false);
            result.setMessage("系统错误,请联系管理员");
        } else {
            result.setOk(true);
        }
        return result;
    }


    public Object getData(Class clazz) {
        if (data instanceof JSONArray) {
            return JSONObject.parseArray(this.data.toString(), clazz);
        } else if (data instanceof JSONObject) {
            return JSONObject.parseObject(this.data.toString(), clazz);
        } else {
            return data;
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "ok=" + ok +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}

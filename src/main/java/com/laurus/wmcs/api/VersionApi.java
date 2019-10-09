package com.laurus.wmcs.api;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.laurus.wmcs.result.Result;

/**
 * @author : duyong
 * @version : 1.0
 * @date : 2019/3/14 15:27
 * Description :
 */
public interface VersionApi {
    @JsonRpcMethod("version")
    Result version();


    @JsonRpcMethod("login")
    Result login(@JsonRpcParam("name") String name,@JsonRpcParam("password") String password);
}

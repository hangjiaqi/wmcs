package com.laurus.wmcs.api;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.laurus.wmcs.apo.ServiceWrapper;
import com.laurus.wmcs.context.AopContext;
import com.laurus.wmcs.result.Result;
import org.springframework.stereotype.Service;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2019/3/14 12:27
 * Description :
 */
@Service
@AutoJsonRpcServiceImpl
public class AggregateApiImpl implements AggregateApi {

    @Override
    @ServiceWrapper("versionService")
    public Result version() {
        return AopContext.getCurrentReturn();
    }

    @Override
    @ServiceWrapper("versionService")
    public Result login(String name, String password) {
        return AopContext.getCurrentReturn();
    }

}
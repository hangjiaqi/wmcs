package com.laurus.wmcs.service.impl;

import com.laurus.wmcs.api.VersionApi;
import com.laurus.wmcs.result.Result;
import org.springframework.stereotype.Service;

@Service("versionService")
public class VersionService implements VersionApi {
    @Override
    public Result version() {
        return new Result();
    }

    @Override
    public Result login(String name, String password) {
        return new Result();
    }
}

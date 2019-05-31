package com.diploma.binpacking.service.impl;

import com.diploma.binpacking.service.GraphExampleService;
import com.diploma.binpacking.service.graphserviceimpl.Example;
import org.springframework.stereotype.Service;

@Service
public class GraphExampleServiceImpl implements GraphExampleService {

    @Override
    public String getShortPath(String start, String end) {
        return new Example().runExample(start, end);
    }
}

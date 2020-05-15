package com.bingqi.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CsvPath {
    @Value("${ZGCS.path}") public String ZGCS_PATH;
    @Value("${ZBCSGX.path}") public String ZBCSGX_PATH;

    public CsvPath(){}
}

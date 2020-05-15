package com.bingqi.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CsvPath {
    @Value("${TQYBDT.path}") public String TQYBDT_PATH;
    @Value("${TQYB15T.path}") public String TQYB15T_PATH;
    @Value("${TQYBDTZXS.path}") public String TQYBDTZXS_PATH;

    public CsvPath(){}
}

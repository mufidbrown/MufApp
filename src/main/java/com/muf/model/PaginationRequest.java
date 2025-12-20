package com.muf.model;

import java.util.List;

public class PaginationRequest {
    private Integer first;
    private Integer rows;
    private String globalFilter;
    private String customData;
    private List<TableField> cols;
}

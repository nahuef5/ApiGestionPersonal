package com.management.staff.models;
public interface QueryPageable {
    Integer getPage();
    Integer getElementByPage();
    String getOrderBy();
    String getOrder();
}

package com.example.demo.entity;

import lombok.Data;

/**
 * IP的地区、类型等信息
 */
@Data
public class IpZone {
    private String  ipRangeId;
    private String  ret;
    private String  ipStrStart;
    private Long ipStrStartNum;
    private String  ipStrEnd;
    private Long  ipStrEndNum;
    private String  provinceName;
    private String  provinceCode;
    private String  cityName;
    private String  cityCode;
    private String  districtName;
    private String  districtCode;
    private String  isp;
    private String  type;
    private String  description;
    private String  referFullCode;
    private String  updateTime;
}

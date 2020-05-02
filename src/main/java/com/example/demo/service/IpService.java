package com.example.demo.service;
import com.example.demo.entity.IpZone;
import com.example.demo.utils.IpUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IpService {
    private static TreeMap<Long,IpZone> treeMap=new TreeMap<Long,IpZone>();;//全局变量
    private static List<Long> longArrayList;
    /*
    * 利用类初始化的特性来完成treeMap的初始化
    将TreeMap中的KeySet转化为ArrayList，该ArrayList是排好序的，
    * 保存该List是因为要利用二分查找来寻找TreeMap中对应的起始IP
    * */
    static {
        IpUtils.readCsvFile(treeMap);
        Set<Long> keySet = treeMap.keySet();
        longArrayList = new ArrayList<>(keySet);//有参构造器
        Collections.sort(longArrayList);
    }

    /**
     * 用ip查询对应的区域。
     *
     * @param
     * @return
     */
    public IpZone ipSearch(String ip) {
        System.out.println(ip);
        Long result = IpUtils.ipConvertToLong(ip);
        System.out.println(result);
//二分法查找list得到key
        Long binarySearchResult = IpUtils.binarySearch(longArrayList, result);
       //再用key查找value。
        IpZone ipZone = treeMap.get(binarySearchResult);
        System.out.println(ipZone.toString());
        return ipZone;
    }


    /**
     * 使用简单认证（AppCode）方式调用API
     * @param authorization
     * @return
     */
    public boolean AppCode(String authorization) {
        if(authorization.indexOf("APPCODE ")==0){
            return true;
        }
        return false;
    }
}
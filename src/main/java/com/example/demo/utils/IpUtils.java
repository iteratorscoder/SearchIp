package com.example.demo.utils;

import com.example.demo.entity.IpZone;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IpUtils {


    /**
     * @param longArrayList
     * @param result
     * @return 二分法查找最后一个<= target的元素key
     */
    public static Long binarySearch(List<Long> longArrayList, Long result) {
        int l = 0;
        int r = longArrayList.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (longArrayList.get(m) > result) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        if (l <= 0) {  //处理异常值
            return longArrayList.get(0);
        }
        return longArrayList.get(l - 1);
    }



    /**
     * IP地址转换成Long
     * @param ipStr
     * @return
     */
    public static long ipConvertToLong(String ipStr) {
        String[] ips = ipStr.split("\\.");
        long ipNumber = 0;
        for (int i = 0; i < ips.length; i++) {
            ipNumber += (Long.valueOf(ips[i]) << 8 * (3 - i));   //valueOf是啥意思
            System.out.println("ipNumber = " + ipNumber);
        }
        return ipNumber;
    }

    /**
     *读取文件
     * @param treeMap
     */
    public static void readCsvFile(Map treeMap) {
        try {
//下面这2行可以解决读取csv文件中文乱码的问题。
            DataInputStream in=new DataInputStream(new FileInputStream(new File("src/main/resources/repo_ip_range.csv")));
            BufferedReader reader= new BufferedReader(new InputStreamReader(in,"GBK"));//这里如果csv文件编码格式是utf-8,改成utf-8即可
            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null;
            //一行一行的读csv，按照逗号分开
            while ((line = reader.readLine()) != null) {
                String[] item = line.split("，");//CSV格式文件为逗号分隔符文件，这里根据逗号切分.
                //得到的item虽然是一个数组，但是数组元素个数=1，就是一行数据啦，但是还需要分割这个数据
                String last = item[item.length - 1];
                System.out.println(Arrays.toString(item));
                System.out.println("我在读数据呀");
                //把得到的1行数据进行分割
                String[] csvContent=item[item.length-1].split(",");
                //分割完了之后再保存到类里面去。
                IpZone zone = IpUtils.lineToIpZone(item[item.length - 1].split(","));
                //把ip当成key，类对象当成value存到treeMap里面去
                treeMap.put(zone.getIpStrStartNum(), zone);
                // System.out.println(item[item.length - 1].split(",")[15]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把csv的每行数据封装成IpZone对象
     * @param item
     * @return
     */

    public static IpZone lineToIpZone(String[] item) {
        IpZone ipZone = new IpZone();
        ipZone.setIpRangeId((item[0]));
        ipZone.setRet((item[1]));
        ipZone.setIpStrStart((item[2]));
        System.out.println("item[3]:"+item[3]);
        ipZone.setIpStrStartNum(Long.parseLong((getContent(item[3]))));
        ipZone.setIpStrEnd((item[4]));
        ipZone.setIpStrEndNum(Long.parseLong((getContent(item[5]))));
        ipZone.setProvinceName((item[6]));
        ipZone.setProvinceCode((item[7]));
        ipZone.setCityName((item[8]));
        ipZone.setCityCode((item[9]));
        ipZone.setDistrictName((item[10]));
        ipZone.setDistrictCode((item[11]));
        ipZone.setIsp((item[12]));
        ipZone.setType((item[13]));
        ipZone.setDescription((item[14]));
        ipZone.setReferFullCode((item[15]));
        ipZone.setUpdateTime((item[16]));
        return ipZone;
    }

    public static String getContent(String str) {
        if (str.length() < 1)
            return "";
        return str.substring(1, str.length() - 1);
    }
}
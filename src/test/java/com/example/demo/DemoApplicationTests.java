package com.example.demo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    /**
     * ip转换成Long
     */
    @Test
    void contextLoads() {
        String ipStr="169.254.85.85";
        String[] ips = ipStr.split("\\.");
        long ipNumber = 0;
        System.out.println("ips.length = " + ips.length);  //ips.length = 4
        for (int i = ips.length-1; i >=0; i--) {
            ipNumber += (Long.valueOf(ips[i]) << 8 * (3 - i));   //valueOf是啥意思
            System.out.println("ipNumber = " + ipNumber);

            /*
            *   ipNumber = 85
                ipNumber = 21845
                ipNumber = 16667989
                ipNumber = 2852017493
            *
            * */
        }
    }
}

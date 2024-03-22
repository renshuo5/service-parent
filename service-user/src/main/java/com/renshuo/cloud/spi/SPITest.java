package com.renshuo.cloud.spi;


import com.renshuo.cloud.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Lenovo on 2024/1/8.
 */
public class SPITest {
    public static void main(String[] args) {
//        ServiceLoader<IParse> sp = ServiceLoader.load(IParse.class);
//        Iterator<IParse> iterator = sp.iterator();
//
//        while(iterator.hasNext()){
//            iterator.next().doParse();
//        }

        String querySql = "SELECT v.id AS id,v.vin AS VIN,v.license_plate AS 车牌号,fu. NAME AS 制造工厂 ,opu.NAME AS 运营单位,vm.veh_model_name AS 车辆型号,vn.name as 车辆公告, vt.name as 车辆种类, sa.NAME AS 销售城市,tmu.serial_number AS 终端,tm.term_model_name AS 终端型号,stage.`name` AS 车辆阶段 FROM sys_vehicle v LEFT JOIN sys_unit fu ON fu.id = v.manu_unit_id left join sys_unit opu on opu.id= v.oper_unit_id LEFT JOIN sys_area sa ON sa.id = v.sell_city_id LEFT JOIN sys_veh_model vm ON vm.id = v.veh_model_id left join sys_veh_notice vn on vn.id=vm.notice_id left JOIN sys_veh_type vt on vt.id=vn.veh_type_id LEFT JOIN sys_term_model_unit tmu ON tmu.id = v.term_id LEFT JOIN sys_term_model tm ON tm.id = tmu.sys_term_model_id LEFT JOIN sys_dict stage ON stage.type = 'VEHICLE_STAGE_TYPE' AND stage.val = v.stage WHERE v.is_delete = 0".toLowerCase().trim();
        int fromIndex = querySql.indexOf("from");
        String[] tmp = querySql.substring(fromIndex + 5).trim().split(" ");
        if (tmp.length >= 2) {
            System.out.println(tmp[1]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(querySql);
        System.out.println(sb.reverse().toString());

        char[] chars = querySql.toCharArray();
        char  [] tmps = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            tmps[i] = chars[chars.length-1-i];
        }
        String ss = new String(tmps);
        System.out.println(ss);



        String time = "2024-02-29";
        String begin ="";

        String etime = "2024-03-31";
        String end ="";
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)" ;
        //"(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29) ";
        if (time.matches(regex)){
            begin = time.replaceAll("-","").substring(0,6);
            end = etime.replaceAll("-","").substring(0,6);
        }
        else {
            try {
                begin = DateUtil.formatTime(DateUtil.strToDate(time), DateUtil.SHARDING_YEAR_MONTH);
                end = DateUtil.formatTime(DateUtil.strToDate(etime), DateUtil.SHARDING_YEAR_MONTH);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        System.out.println(begin);
        Integer cursorYear = Integer.valueOf(begin.substring(0, 4));
        Integer cursorMonth = Integer.valueOf(begin.substring(4, 6));
        System.out.println(cursorMonth+"=="+cursorYear);
        Integer endYear = Integer.valueOf(end.substring(0, 4));
        Integer endMonth = Integer.valueOf(end.substring(4, 6));
        System.out.println(endYear+"=="+endMonth);





    }
}

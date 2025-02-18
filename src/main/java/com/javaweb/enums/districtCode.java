package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public enum  districtCode {
    QUAN_1 ("Quận 1"),
    QUAN_2 ("Quận 2"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_8 ("Quận 8"),
    QUAN_PN("Quận Phú Nhuận"),
    QUAN_TB("Quận Tân Bình"),
    QUAN_BT("Quận Bình Thạnh"),
    QUAN_TP("Quận Tân Phú"),
    QUAN_TD("Quận Thủ Đức");

    private final String districtName;
    districtCode(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new HashMap<>();
        for(districtCode item : districtCode.values()){
            listType.put(item.toString() , item.districtName);
        }
        TreeMap<String, String> result = new TreeMap<>(listType);
        return result;
    }
}

package com.hjy.wisdommedical.util;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 数据转换 工具类
 * Created by fangs on 2017/3/22.
 */
public class TransfmtUtils {

    private TransfmtUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * double类型数字  保留 "bit"位 小数(四舍五入)
     * DecimalFormat转换最简便
     * @param doubleValue 数值
     * @param bit         保留的小数位
     * @return String
     */
    public static String doubleToKeepBitDecimalPlaces(double doubleValue, int bit) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("##0.");
        for (int i = 0; i < bit; i++) {
            stringBuilder.append("0");
        }

        DecimalFormat df = new DecimalFormat(stringBuilder.toString());
        return df.format(doubleValue);
    }

}

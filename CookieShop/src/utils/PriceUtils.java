package utils;

import java.math.BigDecimal;

/**
 * 价格工具类，用于对价格进行加法和减法运算
 * 使用 BigDecimal 类来保证浮点数运算的精确性
 */
public class PriceUtils {

    /**
     * 加法运算：两个 float 类型价格相加
     *
     * @param a 第一个价格
     * @param b 第二个价格
     * @return 两个价格的和
     */
    public static float add(float a, float b) {
        BigDecimal bigA = new BigDecimal(Float.toString(a));
        BigDecimal bigB = new BigDecimal(Float.toString(b));
        return bigA.add(bigB).floatValue();  // 返回加法结果
    }

    /**
     * 加法运算：两个 double 类型价格相加
     *
     * @param a 第一个价格
     * @param b 第二个价格
     * @return 两个价格的和
     */
    public static double add(double a, double b) {
        BigDecimal bigA = new BigDecimal(Double.toString(a));
        BigDecimal bigB = new BigDecimal(Double.toString(b));
        return bigA.add(bigB).doubleValue();  // 返回加法结果
    }

    /**
     * 减法运算：第一个 float 类型价格减去第二个价格
     *
     * @param a 被减数
     * @param b 减数
     * @return 两个价格的差
     */
    public static float subtract(float a, float b) {
        BigDecimal bigA = new BigDecimal(Float.toString(a));
        BigDecimal bigB = new BigDecimal(Float.toString(b));
        return bigA.subtract(bigB).floatValue();  // 返回减法结果
    }

    /**
     * 减法运算：第一个 double 类型价格减去第二个价格
     *
     * @param a 被减数
     * @param b 减数
     * @return 两个价格的差
     */
    public static double subtract(double a, double b) {
        BigDecimal bigA = new BigDecimal(Double.toString(a));
        BigDecimal bigB = new BigDecimal(Double.toString(b));
        return bigA.subtract(bigB).doubleValue();  // 返回减法结果
    }
}

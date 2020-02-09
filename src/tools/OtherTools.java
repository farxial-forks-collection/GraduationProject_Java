package tools;

import main.Constant;

/**
 * @author ypl
 * @date 2019/12/28
 */
public class OtherTools implements Constant {

    /**
     * <p>将一个-128~127的整数转换成一个字符串, 字符串包含两个十六进制的字符</p>
     *
     * @param num       int类型整数, 保证范围在-128~127之间
     * @param need0x    如果这个参数为真, 在返回的字符串之前会加上 0x
     * @param needComma 如果这个参数为真, 在返回的字符串之后会加上逗号
     * @return 两个十六进制字符组成的字符串(可能包括空格和0x)
     */
    public static String getHexByOneByte(int num, boolean need0x, boolean needComma) {
        StringBuilder returnString;
        // 如果需要返回 0xfe 之类的, 只需要把此处改成    returnString = "0x"
        if (need0x) {
            returnString = new StringBuilder("0x");
        } else {
            returnString = new StringBuilder();
        }

        // 整数转换成二进制的字符串
        String binaryString = Integer.toBinaryString(num);

        // 如果二进制字符串长度小于8, 高位补0, 补成8位
        if (binaryString.length() < 8) {
            String temp = "";
            for (int i = 0; i < 8 - binaryString.length(); i++) {
                temp += "0";
            }
            binaryString = temp + binaryString;
        }
        // 如果一个数为负数, 那么binaryString必定为32位, 只需要保留最后8位
        if (binaryString.length() > 8) {
            int length = binaryString.length();
            binaryString = binaryString.substring(length - 8, length);
        }

        // 将长度为8的二进制字符串, 每四位转换成十六进制
        returnString.append(Integer.toHexString(Integer.parseInt(binaryString.substring(0, 4), 2)));
        returnString.append(Integer.toHexString(Integer.parseInt(binaryString.substring(4, 8), 2)));
        if (needComma) {
            return returnString.append(",").toString();
        } else {
            return returnString.toString();
        }
    }

    /**
     * <p>得到ffd8和ffd9的位置</p>
     *
     * @param bytes
     * @return 长度为2的int数组 {point_ffd8, point_ffd9}
     */
    public static int[] getFFD8AndFFD9(byte[] bytes) {
        int point_ffd8 = -1, point_ffd9 = -1;
        for (int i = 0; i < bytes.length; i++) {
            if (point_ffd8 == -1 && bytes[i] == FF && bytes[i + 1] == D8) {
                point_ffd8 = i;
            }
            if (bytes[i] == D9 && bytes[i - 1] == FF) {
                point_ffd9 = i;
            }
        }
        System.out.println("point_ffd8: " + point_ffd8 + "   point_ffd9: " + point_ffd9);

        // 处理空图片
        if (point_ffd8 == -1) {
            throw new ArrayIndexOutOfBoundsException("point_ffd8 == -1");
        }
        if (point_ffd9 == -1) {
            throw new ArrayIndexOutOfBoundsException("point_ffd9 == -1");
        }

        return new int[]{point_ffd8, point_ffd9};
    }

}

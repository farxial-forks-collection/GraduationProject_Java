package tools;

/**
 * 格式化向终端输出信息
 *
 * @author TeLa LuoSiFen
 * @date 2020/5/10
 */
public class TerminalTools {

    public static void print(Class c, String msg) {
        String className = "[" + c.getName() + "] ";
        System.out.println(className + msg);
    }

}

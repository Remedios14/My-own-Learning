import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ClassFieldTest {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取 public 字段 "score"
        System.out.println(stdClass.getField("score"));
        // 获取继承的 public 字段 "name"
        System.out.println(stdClass.getField("name"));
        // 获取 private 字段 "grade"
        System.out.println(stdClass.getDeclaredField("grade"));

        // 获取 Field 对象的信息
        Field f = stdClass.getDeclaredField("value");
        f.getName();
        f.getType(); // class [B 表示 byte[] 类型
        int m = f.getModifiers();
        Modifier.isFinal(m); // true
    }
}

class Student extends Person {
    private final byte[] value = new byte[]{0, 1};
    public int score;
    private int grade;
}

class Person {
    public String name;
}
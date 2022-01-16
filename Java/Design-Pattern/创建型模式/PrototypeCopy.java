package factoryp;

public class PrototypeCopy {
    public static void main(String[] args) {
        
    }
}

// 比起继承自 Clonable 接口需要转型的复杂，更常用定义一个方法复制对象
// 不过原型设计本身就不太常用
class Student {
    private int id;
    private String name;
    private int score;

    Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    Student() {}

    public Student copy() {
        Student std = new Student();
        std.id = this.id;
        std.name = this.name;
        std.score = this.score;
        return std;
    }
}

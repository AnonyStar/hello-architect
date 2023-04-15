package online.icode.jvm.demo;

public class StudentData {
    private static Student[] students;
    private static int realLength;
    /**
     * 学生数据初始化.
     */
    static {
        students = new Student[10];
        realLength = 1;
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            //构建学生信息
            students[i] = student;
            realLength ++;
        }
    }
    /**
     * 获取指定学生.
     * @param index
     * @return
     */
    public static Student getStudent(int index) {
        return students[index-1];
    }
    /**
     * 获取学生总数.
     * @return
     */
    public static int getTotal() {
        return realLength;
    }

}


class Student{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
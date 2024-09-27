package com.czhhhh.demo.demos.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;

public class SerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //1.ObjectOutputStream--实现序列化
        System.out.println("----------1.ObjectOutputStream--实现序列化---------");
        objectToStream();


        //2.objectInputStream--反序列化操作
        System.out.println("----------2.objectInputStream--反序列化操作---------");
        Person person = streamToObject();
        System.out.println("反序列化后的对象信息："+person.toString()); //由于age是transient修饰，所以无法反序列化

        //3.用Kryo工具替换java原生的序列化和反序列化
        System.out.println("----------3.用Kryo工具替换java原生的序列化和反序列化---------");
        Person personByKyro = kryoText();
        System.out.println("Kyro反序列化后的对象信息："+personByKyro.toString());

        //4.用Kryo工具（模拟output不及时刷新，会出现的问题）
        System.out.println("----------4.用Kryo工具（模拟output不及时刷新，会出现的问题）---------");
        Person personByKyroNotFlust = kryoOutputNotFlush();
        System.out.println("Kyro反序列化后的对象信息："+personByKyroNotFlust.toString());


    }

    /**
     * 1、ObjectOutputStream--实现序列化
     * 对象--字节--写入文件中
     * @throws IOException
     */
    public static void objectToStream() throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\serializableText\\Person.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new Person("张三","男",18));
        } finally {
            objectOutputStream.close();
        }

    }

    /**
     * 2、objectInputStream--反序列化操作
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Person streamToObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fis = new FileInputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\serializableText\\Person.dat");
            objectInputStream = new ObjectInputStream(fis);
            //反序列化后，将对象转回Person
            Object object = objectInputStream.readObject();
            Person person = (Person) object;
            return person;

        }finally {
            objectInputStream.close();
        }

    }

    /**
     * 3、用Kryo工具替换java原生的序列化和反序列化
     * 一般不推荐使用java原生的方式；
     * @return
     * @throws FileNotFoundException
     */
    public static Person kryoText() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        //注册反序列化的类
        kryo.register(Person.class);
        Output output = null;
        Input input = null;
        try {
            // 序列化
            FileOutputStream fos = new FileOutputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\serializableText\\PersonByKryo.dat");
            output = new Output(fos);
            kryo.writeObject(output,new Person("李四","女"));
            /**
             * 序列化后，必须执行刷新一下，output中的缓存区数据，否则不会写入文件；
             * 后面序列化，Input就会抛出异常：
             * Exception in thread "main" com.esotericsoftware.kryo.io.KryoBufferUnderflowException: Buffer underflow.
             */
            output.flush();
            // 反序列化
            FileInputStream fis = new FileInputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\serializableText\\PersonByKryo.dat");
            input = new Input(fis);
            Person personByKryo = kryo.readObject(input, Person.class);
            return personByKryo;
        } finally {
            if (output != null){
                output.close();
            }
            if (input != null){
                input.close();
            }
        }
    }

    /**
     * 4、用Kryo工具（模拟output不及时刷新，会出现的问题）
     * @return
     * @throws FileNotFoundException
     */
    public static Person kryoOutputNotFlush() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        //注册反序列化的类
        kryo.register(Person.class);
        Output output = null;
        Input input = null;
        try {
            // 序列化
            FileOutputStream fos = new FileOutputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\serializableText\\PersonByKryo.dat");
            output = new Output(fos);
            kryo.writeObject(output,new Person("李四","女"));
            /**
             * Output不刷新，数据不会从缓冲区写入文件；
             * Exception in thread "main" com.esotericsoftware.kryo.io.KryoBufferUnderflowException: Buffer underflow.
             */
//            output.flush();
            // 反序列化
            FileInputStream fis = new FileInputStream("D:\\Program Files (x86)\\Study\\myproject\\demo\\src\\main\\resources\\static\\serializableText\\PersonByKryo.dat");
            input = new Input(fis);
            Person personByKryo = kryo.readObject(input, Person.class);
            return personByKryo;
        } finally {
            if (output != null){
                output.close();
            }
            if (input != null){
                input.close();
            }
        }
    }

}


/**\
 * Serializable接口--默认该对象下的所有属性和值都会序列化；
 * 实现这个接口的类，才可以进行序列化和反序列化的操作；
 * 序列化：对象<-->字节 相互转换的过程，因为对象没法直接写入文件中，需借助字节
 */
class Person implements Serializable {
    private String name;
    private String sex;
    /**
     *  transient修饰符
     *  该属性将不会被序列化
     */
    private transient int age;

    public Person() {
    }

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }


    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
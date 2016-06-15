package com.example.aidlservicetest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Person 用于跨进程通讯必须实现Parcelable接口
 * Created by aaron on 16-6-14.
 *

 1、实现Parcelable接口
 2、实现writeToParcel,方法，当前的对象将被写入一个Parcel.中。
 3、定一个静态的变量，名字为CREATOR，他是一个实现了Parcelable.Creator 接口的一个对象。
 4、最后，你需要创建一个.aidl文件来声明你的parcelable 类。

 这个Person.aidl文件很简单，就是定义了一个Parcelable类，告诉系统我们需要序列化和反序列化的类型。
 每一个实现了Parcelable的类型都需要对应的.aidl文件。AIDL编译器在编译AIDL文件时会自动查找此类文件。
 */
public class Person implements Parcelable{
    private String name;//名字
    private int sex;//性别

    public Person(){}

    //从Parcel解析出Person
    protected Person(Parcel in) {
        readFromParcel(in);
    }

    //定义一个static final 修饰的实现android.os.Parcelable.Creator<T>接口的Creator类对象CREATOR，它的名字是固定的。
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //注意读取变量和写入变量的顺序应该一致 不然得不到正确的结果
        parcel.writeString(name);
        parcel.writeInt(sex);
    }

    //注意读取变量和写入变量的顺序应该一致 不然得不到正确的结果
    public void readFromParcel(Parcel source) {
        name = source.readString();
        sex = source.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "name=" + name +", sex=" + sex;
    }
}

package com.example.aidlservicetest;
import com.example.aidlservicetest.Person;

//1、JAVA的基本数据类型(int, long, char, boolean等)。不需要import包。
//2、String 和 CharSequence。不需要import包。
//3、集合类型 java.util.List和java.util.Map，不需要import包。但是集合里面的项的数据
//   类型同样要满足这几个限制，AIDL接口和 实现Parcelable的复杂类都要import包。
//4、AIDL 接口，需要import包，即使在同一个包下。
//5、实现 android.os.Parcelable 接口的复杂类。需要import包。

//注意:AIDL定义文件中方法的参数除了基本数据类型、String类型和CharSequence类型之外，
//其它类型都需要使用方向标签，in表示由客户端设置，out表示由服务端设置，inout是两者均可设置。

interface IMyServiceInterface {
      // 加法，传递java基本数据类型
      int add(int a,int b);

      //注意，我们需要在参数上加入方向指示符in，代表参数由客户端设置，
      //我们还需要为Person提供一个import语句(虽然说在同一个包下)。
      String showPerson(in Person person);

      //获取列表，为Person提供一个import语句，但是List就不需要Import
      List<Person> getPersonList(in Person person);

      Map getMap(String key, in Person person);

      Person getPerson();
}

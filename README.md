# scala_template_typeflow
类型流是我设计的用于大规模软件工厂的**方法论**和开发工具，以云函数计算（Serverless）为目标平台、以领域模型驱动和函数式编程为指导。

当前还出于比较早期的阶段，暂时请勿用于生产，但非常欢迎开发人员尝试使用，给我反馈，让我更好的改进，给大家提供更好的开发工具。

这个给出一个Step by Step的例子供大家了解和体验类型流开发

### 环境准备
由于使用了一些Scala的语言特性，当前版本暂时只支持sbt开发Scala和Java混编的工程，以后会支持纯Java的工程。你需要安装好sbt构建工具，mac下执行`brew install sbt`即可。
其他环境的安装和使用请参考官网：https://www.scala-sbt.org/download.html

sbt安装好后请在工程目录下执行`sbt update`，sbt将会下载所需的依赖，类似Maven和Gradle。 如有条件科学上网最好，否则此过程会耗时很长。

依赖更新完成后，将工程导入你的开发工具中（我使用的是intellij+scala插件）。
在IDE里执行src/main/scala目录下的com.github.notyy.example.HelloWorld，如果成功输出`hello,world`则说明开发环境准备成功。

当前版本使用plantuml来建模（新的html的前端界面已经在开发中），图形化显示plantuml的模型文件有两种方式：
1. 参考官方文档安装：http://plantuml.com/zh/starting ，注意还需要安装dot
2. 用[在线服务器](http://www.plantuml.com/plantuml/uml/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000) 在表单里把plantuml的内容提交上去。

### 先从一个简单的例子开始 
打开`typeflow/newModel_v1.puml`，你应该看到一个这样的模型图![模型图](http://www.plantuml.com/plantuml/png/bP31QlCm48JF_pw5o3s7Fxc546ARbXhIrBUUXIKS4MbbfDr2A7ttsb6etPe4R30icc-6sOLz91c8dGM8PuEj3DA9sieOnIVyosLzRe9dL8MtFcotXNTEeZeOr2MsJ4-eMc-GTZkHSx2NT-yLYEvxvCn24sUwCsikZ2z4xKpZ6zCilkAWdnbhCXux-c2Q4-MMzRrQf0DACX-wadJg34KmBdg4Bu1qnIExQsNnw7WtDbDg9Vvs6BHxKXKCafZkUfDVsP9PJytmKssvt8bZkTcKIBhH0KRpLUjxJHvJBFHQWbP3G9e7Tl5_qOT0yYJxUi3JFC9N2U_-53rA_aMcrM6lKR6py0q0)。
如果看不到图，你可以尝试[这个链接](http://www.plantuml.com/plantuml/uml/bP31QlCm48JF_pw5o3s7Fxc546ARbXhIrBUUXIKS4MbbfDr2A7ttsb6etPe4R30icc-6sOLz91c8dGM8PuEj3DA9sieOnIVyosLzRe9dL8MtFcotXNTEeZeOr2MsJ4-eMc-GTZkHSx2NT-yLYEvxvCn24sUwCsikZ2z4xKpZ6zCilkAWdnbhCXux-c2Q4-MMzRrQf0DACX-wadJg34KmBdg4Bu1qnIExQsNnw7WtDbDg9Vvs6BHxKXKCafZkUfDVsP9PJytmKssvt8bZkTcKIBhH0KRpLUjxJHvJBFHQWbP3G9e7Tl5_qOT0yYJxUi3JFC9N2U_-53rA_aMcrM6lKR6py0q0)   

类型流程序由输入端口（InputEndpoint），输出端口（OutputEndpoint）和纯函数以及在函数间传递的标准类型和自定义类型构成。类型流的原则是**副作用剥离**，既业务逻辑应该全部在纯函数里，
副作用全部在输入输出端口里。

如下图里面最下方的AddAndPrint是违反原则的，应该避免。 ![下图](http://www.plantuml.com/plantuml/png/bP31QW8n48RFpLC4xnwwtaGMTvUMBbYxjvx39gp1PB9CPWfI-kvTZRXMY-Xjo7pVpvyHoqWAYRqJSMkXLwYyR9sAKNwBfrArsmu3Www22xXOLC5x1NfbmTJGCxs0xeC5odDPChhTPmZwjbPW5nzH2sTt36z4_IFM1zFzSCDeNXbQiW46T6Mx3PMEpRzjo20eib-cKRGPw0gjZm74Zn3fR6lsXwRorhMERA5r9YyBZBi2T8C3Boy_T8BUsP6TNKU8KulfD9VhUZwlWKndK9pD-zsXmjLZYXNxNOAoCI3D8pl99kTTHN9Jp6pKnlceFV_LbWOqUoT-0G00)

回到前面的正确模型，这个模型很容易理解，这个应用让用户通过命令行输入整数，这个整数分别经过加2（Add2）和乘3（Multi3）的运算，然后再求和，最后打印出来。

理解模型后，我们用类型流提供的代码生成工具生成代码：
在工程根目录下，命令行输入命令：
```
./genCode.sh typeflow/newModel_v1.puml
```
一阵杂乱的日志信息后..^-^，代码就生成了，你应该能在src/main/java目录的com.github.notyy.example里找到`Add Add2 Multi3和Print`等纯函数和输出端口。
编辑代码，完成填空。

输入端口NumInput的代码生成在src/main/scala目录的相应包里了，当前版本暂时还无法生成Java的输入端口代码。。。

打开NumInput,可以看到代码已经全部填好了，代码的模板来自`code_template/scala/CommandLineInputEndpoint.scala`。
我们可以看到连调用链都自动生成了，这是因为模型已经包含了从输入端口到输入端口的整个流的所有入参出参和调用顺序信息。足够让类型流代码生成器自动生成调用链。

执行NumInput，按照提示输入数字，可以看到程序按照模型预期的结果执行。

### 添加状态
第一步的例子是个典型的map/reduce运算，正好契合函数式编程的好球区，但我们更普遍的业务应用是有状态的，那么要怎么处理状态呢？
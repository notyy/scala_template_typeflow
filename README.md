# scala_template_typeflow

[TOC]

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
打开`typeflow/newModel_v1.puml`，你应该看到一个这样的模型图

![模型图](http://www.plantuml.com/plantuml/png/bP31QlCm48JF_pw5o3s7Fxc546ARbXhIrBUUXIKS4MbbfDr2A7ttsb6etPe4R30icc-6sOLz91c8dGM8PuEj3DA9sieOnIVyosLzRe9dL8MtFcotXNTEeZeOr2MsJ4-eMc-GTZkHSx2NT-yLYEvxvCn24sUwCsikZ2z4xKpZ6zCilkAWdnbhCXux-c2Q4-MMzRrQf0DACX-wadJg34KmBdg4Bu1qnIExQsNnw7WtDbDg9Vvs6BHxKXKCafZkUfDVsP9PJytmKssvt8bZkTcKIBhH0KRpLUjxJHvJBFHQWbP3G9e7Tl5_qOT0yYJxUi3JFC9N2U_-53rA_aMcrM6lKR6py0q0)。

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

打开`typeflow/newModel_v1_state.puml`，模型图如下：
![模型图](http://www.plantuml.com/plantuml/png/bPDlQzim4CPVxpw5wFiMbhuh9JH_LXRmkh2nxRMmX2CgdOnqrr76ldljYaZ6jGCE12vwVi_nw4w1XqWo43iB49yDjZ989sehOnJ_uDDMrizDy8ngvwqFz_Sxxvn4LHdK1soPlg1glK5QhyMMWttzuxq2qNqkEJUXYGlT6sjU6RyIjGkDtvaMyO_6wgMU8mVzo5YJyTZHbMbEjBMivwv2Eg0aywAFaawTOHUCYnwdyHywUWwrxcC4_nVXFq3QpnExzs68Isk1n6wKMebnqgBDISTWaCHuYbd-nfRCxMpVkcR7LfAU8oelLQ5-IyjQJAqW6txA5xt8C8BFmnHbhXdTczXAgayVmy9KpfkRkURGfro0vprUNw_5q-7kxe2a6nrWKFTQJWZdultqwlELs1To9AwI-0iDmRdebQdPJzqpiPkgz1_-o06A2_QZrFtJ-t2ONWxqMe9MMOT1dU_GWP0yoNvRy5ANE3tUVLFhGUU9nYLuAPDlLuofpixqlnYVJhrEUD2es5bu0m00)

可以看到和之前的模型相比增加了几个元素：
1. `Dispatch`， 由于输入端口遵循原则，除了输入一个整数外不含任何别的业务逻辑，所以增加了一个Dispatch函数，这个函数产生两个输出，一个是走原来的Add2和Multi3的流，没有任何改变。 另一个输出是Unit，
这是Scala的类型，相当于Java的`void`。 这里指示调用一个无参的文件输出端口：`LoadAccumulateValue`
2. `LoadAccumulateValue`是一个FileOutputEndpoint，所有对外部资源比如文件、数据库、消息队列等的访问（不论读还是写）都可以看做一个输出端口。这个函数加载一个累计值。
3. `Accumulate`函数把原来的Add的计算结果和累计值相加后，一个流传给原来的Print输出，另一个传给`SaveAccumulateValue`保存。

因此我们可以理解现在程序的逻辑是每次的计算结果会累加上次的计算结果。因此现在这是一个有状态的业务了。

同样，生成代码,**注意代码生成器不会覆盖已经存在的文件**，因此原来的业务逻辑代码保留不动，但是NumInput要删除以便生成新的调用链（将来的版本会实现不用删除文件情况下对调用链的更新。）：
```
./genCode.sh typeflow/newModel_v1_state.puml
```

再次填空，注意Dispatch的代码如下：
```
public class Dispatch {
    public Integer execute(Integer param1) {
        return param1;
    }
}
```
那个Unit类型无需返回。

注意LoadAccumulateValue和SaveAccumulateValue是根据同一个写文本文件的代码模板生成的，没有给读写提供不同的模板。
因此会需要一些修改：

`LoadAccumulateValue`第11行，修改保存累计值的文本文件的路径，因为代码生成器不知道该值。
```
File file = new File("./localoutput/accu.txt");
```
如果你没耐心自己慢慢写，在ref_code目录下有我的参考实现，但我已经好几年没写Java，所以应该已经不是fashion的写法了。如果你能contribute最新的写法更好。

然后是`SaveAccumulateValue`,第11行务必用相同的文件路径。 
然后**注意**第15行的true改成false，这样就只会覆盖同行累计值。

看一下NumInput，新的调用链已经生成好了，可以检查一下有没有问题。

现在，执行！

你可以看到再次输入的结果已经变化，因为已经把累计值计算进去了，`localoutput/accu.txt`也正确记录了累计值。

好，我们已经知道了怎么处理状态，接下来再通过后面几步来体会类型流的优势。

### 观察程序行为
类型流的模型乍一看和工作流有点像，但两者有本质的不同：工作流的每一个**步骤**都可以做任何事，比如读写文件、访问数据库等。
随便向工作流里加入一个步骤，你是无法知道这个步骤对整个系统行为的影响范围的。步骤之间可能互相干扰，不打开代码检查无法知道。

而类型流的每一个节点都只能通过输出流来影响系统行为，即使是有副作用的输出端口，你也可以很清楚的知道它的影响范围在哪里。纯函数更加如此了。

我们对之前两步例子的结果继续做扩展来说明这个优势。

打开`newModel_v1_record.puml`,应该看到如下图：

![下图](http://www.plantuml.com/plantuml/png/bPDDQnin48RFdLyXSa-WpYq44tyqrE2c9lteMQo4BIAD5ccK4ah_UtOZhmR4hc4DnkETvnszCwE5XqWoK78M8BuOR6MGJZHMnYZ-mQUrR5wEmITK6szzkB_VUESaghr1lSCsqHTKhJT8owLOfs3V_dYlG7IVIyfLECQPwfKrRmc_4hKpXTyJpU8VZTLpDOSKz6F2Xep7ZjwRuudMDkcfLaW7b6HUzK7IgHDiWd7HupZ-hADFeT6zZU3_8lm7oFjvIkvZ6EBIMixiIgc5g9roRYfr20P9r5CiwM_9ahcThDiqoIKhIKyHUoyLKx-bJLgChQ08ViXNFKjGmSCm1S-re5iJMqdDQShiisvyK9UF-waZ5OvF01ZhzEPcoXxqVSx0oqsulbwAfztTtGv97tK0ZiD7xkhprRaFo3zzLJPTysKcC0IbofR9Ahd8oLze28QWpunjUsmkcLtjkNx-KxN1AjXMhllBNZyytrNeSnbQy04SXz931qBMPFajunopdHxllhaqennoOJREOTPjM5ZYhCnlOlrqx7rWXiySTlwWKILduHq0)

跟之前的模型对比可知，只增加了一个Record输出端口，注意由于plantuml的自动布局特性，`LoadAccumulateValue`被画到左边去了，但实际上模型没有大改。

增加的这个Record，根据入参可知，它记录每次用户输入和计算的最终结果。 仅仅通过模型，我们就可以非常确定程序原有的功能没有收到任何影响。

废话少说，生成代码,记得要删除NumInput以便生成新的调用链，别的都不用改。
```
./genCode.sh typeflow/newModel_v1_record.puml
```
第11行改成
```
File file = new File("./localoutput/record.txt");
```
第15行改成
```
writer.write(param1.toString() + "," + param2.toString());
```
另外就是把localoutput/accu.txt记录的之前的累计值删除，以方便我们干净的重来。

ok,执行以下，输入1，2，3后，我的record.txt文件记录了如下内容：
```
1,6
2,16
3,30
```
你呢？

### 回放测试
在前一步的练习中，我们记录下了每次用户输入的整数和程序执行后存储到累计值中的结果。
我们现在可以重放这个记录来对我们的程序做回归测试。

打开`newModel_v1_replay.puml`查看，如下图所示：
![下图](http://www.plantuml.com/plantuml/png/bLFVQzim47xU_HMYxoqiVGibDF5M5l0oan_Run2sS4HboSYdZZBslq-UOfznPBCK6f_rVT_TvxiJF3V8BRfA0JIlKjVSyehbYZSD-mqVDZn_BQrnkaYCClRfAJ5LnNMHIYsskdRuMHUraHfNAxPnLht_CAQ8o5wack4p_pTNQMtBvaRrMibtfxyWrZSAlpcyYPyvArwSpb4QxTcfBC-uKKvCsK9XDKVv9ZAEPoz8hAmQe-FqlMYCixbeRLZpIyCVW32RdQl-VHYR6yc5kowA1Hix9MmAognFmgWZewnVdKBvE9atprtb54SnnKWDBuZrdIidfeiT-9luR_A8s3MHKJzxwYVPr1ppyvp9esXmBt9Z2qQB7B5OAubJUIX7p8MVOCsjc1t4XUErJaMd0h66FZnCTGDYBPsXzmjtVxzYk-rokTKeIc5XrA8MJe_xvVA0tdOvXdq5UeC1iHM67aBx6-hSVRnZXopzkTrbfoEL710MWR1EaXFXrv8-MpTms6rdm2uWXDWZS170avO8NT0YiVsEZIXmrodbfvU1DL91y49mUxsl3iFjX1OBf0KL6CJzDtWY14sGFnATOhBJqlr_I3t874bCnYaCkeI48UwLOUMlJYChInnorzqdeCqOk-eOuHdyR8nHWckuFDkpoDM3Pw4BLodu2m00)
和`newModel_v1_record.puml`相比，可以看到我们增加了一个新的输入端口`LoadTestRecord`

继续贯彻输入端口不做业务处理的原则，这个输入端口只是逐行读取前面记录的测试数据，每行触发一次数据流执行。

输入的字符串交给Split函数，拆分成输入值和期望值两个整数，输入值传给`Dispatch`函数，从而触发原本的业务流程。期望值传给`Compare`函数。
`Compare`函数同时接受原本业务流程最终计算出的累计值，然后在进行比较，输出Bool值，然后打印输出。

可以看出这个模型相当于给原本需要用户手工逐行输入的业务流程增加了一个自动重放入参的入口，然后比较预期的输出结果，从而达到回归测试的效果。

闲话少说，生成代码
```
./genCode.sh typeflow/newModel_v1_replay.puml
```
继续填空，`Compare`很简单，只是要注意用`equals`而不是`==`, `PrintCompareResult`也很简单，把参数填上就好了。

`Split`略为复杂，因为Split要返回两个整数，分别给不同的数据流，而Java不支持元组，所以我这里用[vavr库](https://www.vavr.io/vavr-docs/#_tuples)来实现。
请参考ref_code/Split.java

`LoadTestRecord`生成在scala工程里，略做修改，第15行，输入上一步练习记录数据的文件路径`./localoutput/record.txt`，
修改第17行，把toInt去掉。如果`./localoutput/accu.txt`里面有值，**记得清掉**。

现在执行`LoadTestRecord`，即可运行。

### 实现阿里云函数计算版本
先声明，本人没有收阿里云的广告费~~~，只是接触的最早，用起来又很方便，所以先出了阿里云的版本。 将来肯定还要支持aws lambda，华为云等多种云平台的。

在这个例子里，我用了阿里云函数计算服务（function computing）和对象存储服务（Object Storage Service —— OSS），oss因为极其便宜，所以特别适合用来做实现，mysql有点小贵。

阿里云资源的购买和配置我这里就不多说了，请自行查阅阿里云的文档。 在本地需要配置好[ossutil](https://helpcdn.aliyun.com/document_detail/50452.html)和[fun](https://help.aliyun.com/document_detail/64204.html). 
配置好你的阿里云账户。注意用fun需要使用你的root账户的ACCESS_KEY等，不能用另建的安全账户，否则会出错。

另外，我准备了[一段视频](https://www.bilibili.com/video/av77104359/)，你如果只是想了解操作的过程和效果，看一下视频也可以。

如果希望亲手手操，并且也准备好了的环境的，请跟我继续。

打开typeflow/newModel_v1_state_aliyun.puml,因看看见如下的图：
![下图](http://www.plantuml.com/plantuml/png/bP9VQzim5CMVTp-5w7iBIzzA2SsVbGMojYniUnQMI4IbAoFTsr76llliQlODYHrm873iStxoyRs6Xqmw4GSFa5yTrZhfe2glSrP_uDDILw_x51dDAlgOxkzNCGIDPklGRh1c-eAcZWvfFbTBJlxzLum6LFExH2xIFa-a5zwzCtubgYU2pqoJye_EMs6cb7lUxQQ2Bvpi8sD5BcAJthhzWPQUxTXZo2RKvDxiYwP30woI2pdYSFoxpP5JPTiQAVv6-0jG5ll4uMEAwi9S_qSNnipKQL7nngnlxCdTZe9LnO6z9ZkMs4Pj9FLJUxPgVCuTjTDlTZfrGdHZKpB1FhfskTPK7Svhmr3Zcvknmw6jEY7UR-pwUgwUDdTt6oIxjmcETElr3Q4J7tUlFb-fjIHFp5anVw0Zw4Jh36rXw3rBTh6Lyuyl-W92mVgaTVkLdTqitn2qNWdDv057kd-79WY5AVYjn4LBdJRU_ClH4XKf1qjnaOLR2IYwaDtApeo1rSE3HSF1mpy0)
这个模型是基于typeflow/newModel_v1_state.puml修改。对比[第三步:添加状态](#添加状态)的模型图，我们可以发现主要有三个改变：
1. NumInput从\<<CommandLineInputEndpoint\>>改成了\<<AliyunHttpInputEndpoint\>>，这是阿里云提供的http request/response类型的触发器。
2. Print输出端口去掉了，因为在云端运行的时候终端打印没有什么意义。取而代之的是原本流向Print的AC::Integer现在改为流回到NumInput，以便完成一个Request/Response。
3. LoadAccumulateValue和SaveAccumulateValue两个输出端口从\<<FileOutputEndpoint\>>改成了\<<AliyunOSSOutputEndpoint\>>。它们的逻辑改为用OSS来做存储和读取。

可以看到除了输入输出端口的修改外，内部的纯函数（业务逻辑）没有任何需要修改。那些不变的东西就是我们的领域模型。大家如果了解[端口适配器架构](https://herbertograca.com/2017/09/14/ports-adapters-architecture/)，就会发现我这个类型流就是一种端口适配器架构。
```
./genCodeAliyun.sh typeflow/newModel_v1_state_aliyun.puml
```
打开java目录可以看到原来的代码都在，代码生成工具并**不会自动删除图上没有的代码**。

在scala目录下多了个aliyun目录，里面是对Java函数的包装，因为阿里云函数计算要求函数都要继承`StreamRequestHandler`，实现其中的
```scala
override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
}
```
这个方法。 类型流代码生成器通过生成适配器来避免业务代码和部署平台的耦合。

大部分代码无需修改。 只有`LoadAccumulateValueHandler`和`SaveAccumulateValueHandler`这两个OSS输出端口是需要实现的。
而自动生成的`SaveAccumulateValueHandler`已经很好的完成了功能，无需修改，唯一要改的就是`LoadAccumulateValueHandler`了。
打开生成的代码可以看到已经生成包含ossClient在内的代码骨架，具体的代码可以参考`ref_code/LoadAccumulateValueHandler`。

需要注意的是代码里的这几行：
```scala
    val accessKey = System.getenv("ACCESS_KEY")
    val accessSecretKey = System.getenv("SECRET_KEY")
    val accountId = System.getenv("ACCOUNT_ID")
    val bucketName = System.getenv("BUCKET_NAME")
    val objectName = System.getenv("OBJECT_NAME")
```
这几行是读取环境变量，需要你的阿里云管理控制台的相应函数的配置界面配置这些环境变量。

除了这些代码之外，类型流代码生成器还自动生成了工程根目录下的`template.yml`, `fun`命令行工具会根据这个配置文件来向阿里云部署应用。

确认代码无误后，运行`sbt assembly`编译打包代码，`sbt assembly`会把所有编译后的代码和第三方库一起打成一个大jar包（fat jar），这样部署到云端后就不用多管依赖库的问题了。

打包完成后，运行`./deploy-aliyun.sh`把应用部署到云端。

另外，`LoadAccumulateValueHandler`需要读取OSS存储内容，如果读不到会报错。所以我们先把包含初始数据的`accu.txt`传上去。
```scala

```

### 异常处理

### 自定义类型和中文建模
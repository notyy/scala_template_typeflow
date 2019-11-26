#copy flow puml file to oss
#ossutil cp $1 oss://type-flow -f
#copy code jar package to oss
ossutil cp target/scala-2.13/scala_template_typeflow-assembly-0.0.1.jar oss://type-flow -f
#deploy to aliyun function compute
fun deploy
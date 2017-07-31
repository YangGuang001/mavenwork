# 第一课

快照没有搞定


* maven工具包包含很多的插件，例如执行maven clean compile 编译源文件生成到target目录下，clean表示删除原来的文件夹
clean 是clean插件提供的， compile是compiler提供的。  
* 在mvn项目有测试的src目录下执行mvn clean test可以执行junit测试，过程会编译test目录下的测试文件，再执行相应的测试用例。  
* mvn插件有clean，resources，compile，testResources，testCompile等。  
* testCompile执行编译任务，放到target/test-classes目录下，紧接着surefile：test负责执行测试，surefile是maven中负责执行测试用例的插件   
* mvn的compile、test、package、install依次执行，install必须执行前面的步骤
* 每一个插件都有一个或者多个目的。
* mvn 打包的插件有不同maven包jar：maven-jar-plugin， maven包war：maven-jar-plugin，可执行的包：maven-shade-plugin，自定义打包：maven-assembly-plugin   
* 带有标签orginal为原生包，可以作为被引用的包
## 项目中的配置选项  
```xml
<groupId>com.maven.work</groupId>
    <artifactId>lession1</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>  # 大部分默认的情况下为jar
    <classifier>doc</classifier> # 表示是否生成附属组件，由附属组件插件名决定
```
### 依赖中的配置选项
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.7</version>
    <packaging>jar</packaging>  # 大部分默认的情况下为jar
    <scope>test</scope>
    <optional>true</optional> # 选择依赖，例如：mysql，postgreSQL
</dependency>
```
当前项目已结依赖:mvn dependency:list   
 The following files have been resolved:
    org.hamcrest:hamcrest-core:jar:1.3:compile
    junit:junit:jar:4.12:compile
 依赖树：
 --- maven-dependency-plugin:2.8:tree (default-cli) @ lession1 ---
 com.maven.work:lession1:jar:1.0-SNAPSHOT
 \- junit:junit:jar:4.12:compile
    \- org.hamcrest:hamcrest-core:jar:1.3:compile
 * 镜像   maven中可以使用镜像代替中央仓库
 ```xml
<mirror>  
  <id>alimaven</id>  
  <name>aliyun maven</name>  
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
  <mirrorOf>central</mirrorOf>          
</mirror>  
```
 * 远程仓库 repository   
 ```xml
<repository>
  <id>central</id>
  <name>Central Repository</name>
  <url>https://repo.maven.apache.org/maven2</url>
  <layout>default</layout>
  <snapshots>
    <enabled>false</enabled>
  </snapshots>
</repository>
```
 * 部署构建到远程仓库 distrbutionManagement   
 ```xml
 <!-- 在POM中配置构件部署地址 -->
<distributionManagement>
    <!-- 发布版本的构件的仓库 -->
    <repository>
        <id>proj-release</id>
        <name>proj release repository</name>
        <url>http://192.168.1.100/content/repositories/proj-release</url>
    </repository>
    <!-- 快照版本的仓库-->
    <snapshotRepository>
        <id>proj-snapshots</id>
        <name>proj snapshot repository</name>
        <url>http://192.168.1.100/content/repositories/proj-snapshots</url>
    </snapshotRepository>
</distributionManagement>
```
# 生命周期和插件
三个相互独立的生命周期：clean（pre_clean、lean、post_clean）、default（compile...install、deploy）、site(pre_site、site)   
default为jar包服务，site为war包服务。
## 内置绑定
* 插件的目的：maven-dependency-plugin插件有几十个目标：dependency:list(列表),dependency:tree(树),dependency:analyze(分析)，
maven-compiler-plugin的目的:compiler:compile，maven-surefile-plugin的目的surefile：test
## 自定义绑定

## 插件仓库 pluginRepositories

# 聚合与继承
build的子元素testResources 表示开启资源过滤
name 字段在聚合模块中表示是自己的名称。
## 继承
dependencyManagement 即可以让子模块继承父模块的依赖配置，保证子模块的灵活配置，又不让模块引入实际的依赖   

# maven-surefire-plugin 测试插件
* mvn test默认会执行以下这组模式的类：
> ***/Test*.java:任何子目录下所有有命名以Test开头的类。
> ***/*Test.java:任何子目录下所有命名以Test结尾的类。
> ***/*TestCase.java:任何子目录下所有命名以TestCase结尾的类。   
只要按照上述的命名方式，maven test都会执行到。
* 添加includes测试类和排除excludes测试类
* cobertura-maven-plugin 可以或侧测试类覆盖率报告
* TestNG和Junit的优缺点以及和maven-surefire-plugin的配合使用
主代码和测试代码都打包：
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>2.2</version>
    <executions>
        <execution>
            <goals>
                <goal>test-jar</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

# webapp打包盒jetty-maven-plugin进行测试
在setting.xml中天pluginGroup标签可以把非maven官方的插件安装到maven插件中，从而可以使用mvn jetty:run的方式运行   


# maven常用插件总结
* maven-dependency-plugin主要目的将依赖的包输出到执行的目录。
* maven-antrun-plugin能让用户在Maven项目中运行Ant任务
* maven-archetype-plugin生成项目骨架，也可以自定项目骨架demo，交给别人使用
* maven-assembly-plugin的用途是制作项目分发包，该分发包可能包含了项目的可执行文件、源代码、readme、平台脚本等等。流的格式如zip、tar.gz、jar和war等
* maven-dependency-plugin用途是帮助分析项目依赖dependency:list能够列出项目最终解析到的依赖列表dependency:tree能进一步的描绘项目依赖树dependency:analyze可以告诉你项目依赖潜在的问题
* 编译时期的maven-compile-plugin，内置插件，主要作用编译代码，限制java版本。
* 执行代码exec-maven-plugin，主要作用通过配置直接运行编译出的代码直接可以运行。
* maven-resources-plugin添加额外的资源文件目录
* maven-jar-plugin 可以对test代码进行打包
* jetty-maven-plugin可以启动jetty容器进行测试。 
# 第一课

快照没有搞定


* maven工具包包含很多的插件，例如执行maven clean compile 编译源文件生成到target目录下，clean表示删除原来的文件夹
clean 是clean插件提供的， compile是compiler提供的。  
* 在mvn项目有测试的src目录下执行mvn clean test可以执行junit测试，过程会编译test目录下的测试文件，再执行相应的测试用例。  
* mvn插件有clean，resources，compile，testResources，testCompile等。  
* testCompile执行编译任务，放到target/test-classes目录下，紧接着surefile：test负责执行测试，surefile是maven中负责执行测试用例的插件   
* mvn的compile、test、package、install依次执行，install必须执行前面的步骤

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

# Angular练手项目之Web聊天室

## 2018-0725更新
截止2018年7月25日，该demo已经有26个star、13个fork了，谢谢大家的支持，只是当时自己刚入前端，很多都不懂，再加上Angular6已经发布，真的没有必要再参考该demo。
如果继续参考，推荐各位初学者可以看看我另外一个[demo](https://github.com/damingerdai/inventory)，同样是基于Angular + Spring Boot。这个demo里的angular部分可能更合适你们。

## 说明
这个demo是我用三周时间学习Angular2的一个小小练习。前端使用了Angular技术，后端则使用Spring Boot，使用数据库为mysql，采用maven进行构建项目。

## 项目结构
>
* src
* -- main
* -- -- angular
* -- -- java
* -- -- resources
* -- test
* sql
* pom.xml
>

## 运行项目
### 1.下载
```bash
 git clone https://github.com/damingerdai/web-qq.git
```
### 2.编译angular页面
进入angular目录下，执行：
```bash
npm install
ng build
```
然后可以发现在resources目录下会多出一个
> 说明：该步骤需要安装npm和angular cli工具

### 3.数据库设置
#### 1.执行sql目录下的sql文件：
* init.sql
* init-user.sql
> 说明：
> * init.sql用于初始化数据库结构，init-user.sql用于初始化用户数据。
> * 初始化用户可以使用单独的[用户注册模块](https://github.com/damingerdai/webqq-register)。
#### 2.数据库账号密码设置
在pom.xml中将**mysql.jdbc.username**和**mysql.jdbc.password**设置为自己本地mysql数据库的账号和密码。

### 4.启动
在主目录下执行：
```bash
mvn spring-boot:run
```

### <font color="red">现在你可以在浏览器访问http://localhost:8080啦</font>

## 开发环境
### 开发工具
* 前端：Visual Studio Code
* 后端：IntelliJ IDEA Community Edition
### 浏览器
Chrome、Microsoft Edge
### 开发平台
Winodwos10 企业版
### 其他
jdk版本必须在1.8以上
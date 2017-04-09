# Echo

Yappy Doggie



- [ ] 实现主要功能
- 微信端
- web端
- 移动端
## [tomcat 安装](http://tomcat.apache.org/)

注意下载第一个zip包，其他包好像都是不完整的．
```
unzip apache-tomcat-6.0.30.zip
mv apache-tomcat-6.0.30/ /usr/local/
cd /usr/local/
ln -s /usr/local/apache-tomcat-6.0.30/ /usr/local/tomcat
cd tomcat/bin/
vim catalina.sh

添加以下内容：

CATALINA_HOME=/usr/local/apache-tomcat-6.0.30/
export JAVA_HOME=/usr/local/jdk
export JRE_HOME=/usr/local/jdk/jre
chmod +x *.sh
```
## 启动tmocat
- `./startup.sh`
(关闭`./shutdown.sh`)
- 访问http://localhost:80/
## Java web 应用
- [x] maven导出 .war包
- [x] 现在本地调试如`http://localhost:8080/Echoyappy/echo`，如果报空指针的错．就是可以的．因为没有穿进去数据．
- [x] 上传.war包到服务器，访问`http://yappyap.com:8080/Echoyappy/echo`这是和微信连接的链接．（微信要求端口为80，但是tomcat开８０端口却访问不了，本地和服务器都是这种情况）
- [x] 访问 http://yappyap.com/Echoyappy 为web界面．
- [ ] 内网穿透调试
- [x] 增加文本消息的读取和回复
- [x] 增加菜单
- [ ] 菜事件响应
- [ ] 数据库
# License
Apache 2.0

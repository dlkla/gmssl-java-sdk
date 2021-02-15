本仓库基于gmssl，对java wrapper接口做了一些补充，具体如下：
1. 补充了生成sm2密钥方法,包含生成DER & PEM两种格式sm2公私钥;
2. 补充了公钥&私钥PEM转DER格式的方法;
3. GmSSL.c中简易实现1,2描述中的jni接口;
4. 简易封装sm2,sm3,sm4,方便直接调用

linux编译使用说明：
1. 下载gmssl源码,解压至当前工作目录,下载链接  
   https://github.com/guanzhi/GmSSL/archive/master.zip
2. unzip master.zip
3. cd GmSSL-master
4. ./config --prefix=/usr/local/gmssl --openssldir=/usr/local/gmssl no-shared  
   注：--prefix表示安装路径；no-shared 表示只编译静态库(实现openssl和gmssl的兼容)
5. make & make install
6. 配置环境变量  
   cd /etc/profile.d; touch gmssl.sh; export PATH = /usr/local/gmssl/bin:$PATH; source gmssl.sh;
7. 输入gmssl version得到GmSSL 2.5.4 - OpenSSL 1.1.0d  19 Jun 2019 表明已经正确安装
8. 编译GmSSL.java  
   在org.gmssl下javac GmSSL.c; cd ../../; javah org.gmssl.GmSSL; cp org_gmssl_GmSSL.h GmSSL.h  
   将resoure/lib下的GmSSL.c和刚才编译的GmSSL.h放在解压的GmSSL-master/java目录
9. 将编译GmSSL得到的libcrypto.so libcrypto.so.1.1, 复制到GmSSL-master/java目录  
   whereis libcrypto, cd; find libcrypto*;
10. 编译得到libgmssl.so  
    gcc -shared -fPIC -Wall -I./jni -I /home/xxx/GmSSL-master/include -L /home/xxx/GmSSL-master/java GmSSL.c -lcrypto -o libgmssljni.so
11. java项目library中添加libgmssljni.so或-Djava.library.path="libgmssljni.so所在文件夹路径"











Reference:  
[1]: http://gmssl.org/docs/quickstart.html  
[2]: https://github.com/guanzhi/GmSSL/issues/1014  
[3]: https://www.jianshu.com/p/51cb8ff49ce7
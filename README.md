一: 本项目基于gmssl，对java wrapper接口做了一些补充，具体如下：
1. 补充了生成sm2密钥方法，包含生成DER & PEM两种格式sm2公私钥;
2. 补充了公钥&私钥PEM转DER格式的方法;
3. GmSSL.c中简易实现1，2描述中的jni接口;
4. 简易封装sm2，sm3，sm4，方便直接调用;

二: lib包简要说明 
1. resource/lib目录包含已经编译好的Linux版本的so文件及Windows 10(x64)版本dll文件,  
   可以导入library直接使用;如果不能使用,可以参考三，四进行编译

三: linux编译参考：
1. 下载gmssl源码，解压至当前工作目录,下载链接  
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
9. 将编译GmSSL得到的libcrypto.so libcrypto.so.1.1，复制到GmSSL-master/java目录  
   whereis libcrypto，cd; find libcrypto*;
10. 编译得到libgmssl.so  
    gcc -shared -fPIC -Wall -I./jni -I /home/xxx/GmSSL-master/include -L /home/xxx/GmSSL-master/java GmSSL.c -lcrypto -o libgmssljni.so
11. java项目library中添加libgmssljni.so或-Djava.library.path="libgmssljni.so所在文件夹路径"

Reference:  
[1]: http://gmssl.org/docs/quickstart.html  
[2]: https://github.com/guanzhi/GmSSL/issues/1014  
[3]: https://www.jianshu.com/p/51cb8ff49ce7

四: windows 10编译参考
1. 下载gmssl源码，解压至当前工作目录,下载链接  
   https://github.com/guanzhi/GmSSL/archive/master.zip
2. 解压
3. 在window下的编译与安装，参考http://gmssl.org/docs/install.html
    - 安装Active Perl(并设置环境变量)，安装NASM，安装最新visual studio；
    - 打开GmSSL-master/crypto/evp/names2.c，添加以下4个函数，见附录
    - 以管理员身份，根据系统选择打开Visual Studio Tools下x64/x86 Command Prompt控制台
    - 执行perl Configure VC-WIN64A 或 perl Configure VC-WIN32
    - 执行nmake
    - 执行make install
4. 编译得到gmssljni.dll
    - 将生成libcrypto.lib和libcrypto-1_1-x64.dll(64位)文件复制到GmSSL-master/java目录，在vs命令行窗口java目录执行  
    执行gcc -shared -fPIC -Wall -I./jni -I ../include -L ../ GmSSL.c -lcrypto -o libgmssljni.dll；  
    - 如果报错，则将java目录的winmake重命名makefile，java目录下执行nmake即可得到gmssljni.dll

Reference:  
[1]: http://gmssl.org/docs/install.html  
[2]: https://www.codenong.com/cs110742166  
[3]: https://github.com/zhangxy123666/gmssl-ftw/blob/master/README.md  
[4]: https://blog.csdn.net/zyhse/article/details/112325129


附录：
```
static void cipher_name_len(const EVP_CIPHER *cipher, const char *from,
	const char *to, void *x)
{
	*((int *)x) += strlen(EVP_CIPHER_name(cipher));
}

static void cipher_name(const EVP_CIPHER *cipher, const char *from,
	const char *to, void *x)
{
	strcat((char *)x, EVP_CIPHER_name(cipher));
}

char *EVP_get_ciphernames(int aliases)
{
	char *ret = NULL;
	int len = 0;
	EVP_CIPHER_do_all_sorted(cipher_name_len, &len);

	ret = OPENSSL_zalloc(len);
	if (!ret) {
		return NULL;
	}

	EVP_CIPHER_do_all_sorted(cipher_name, ret);
	return ret;
}

char *EVP_get_digestnames(int aliases)
{
	return "sm3:sha1:sha256";
}
```



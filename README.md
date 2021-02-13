本仓库基于gmssl，对java jni wrapper接口做了一些补充，具体如下：
1. 补充了生成sm2密钥方法, 包含生成DER & PEM两种格式sm2公私钥;
2. 补充了公钥&私钥PEM转DER格式的方法;
3. 对新增的jni接口，在GmSSL.c中添加了简易的实现;
4. 对sm2,sm3,sm4进行了一些简易的封装,方便直接调用

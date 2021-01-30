/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_gmssl_GmSSL */

#ifndef _Included_org_gmssl_GmSSL
#define _Included_org_gmssl_GmSSL
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_gmssl_GmSSL
 * Method:    getVersions
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getVersions
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getCiphers
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getCiphers
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getDigests
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getDigests
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getMacs
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getMacs
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getSignAlgorithms
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getSignAlgorithms
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getPublicKeyEncryptions
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getPublicKeyEncryptions
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getDeriveKeyAlgorithms
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getDeriveKeyAlgorithms
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    generateRandom
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_generateRandom
  (JNIEnv *, jobject, jint);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getCipherIVLength
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_gmssl_GmSSL_getCipherIVLength
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getCipherKeyLength
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_gmssl_GmSSL_getCipherKeyLength
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getCipherBlockSize
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_gmssl_GmSSL_getCipherBlockSize
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    symmetricEncrypt
 * Signature: (Ljava/lang/String;[B[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_symmetricEncrypt
  (JNIEnv *, jobject, jstring, jbyteArray, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    symmetricDecrypt
 * Signature: (Ljava/lang/String;[B[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_symmetricDecrypt
  (JNIEnv *, jobject, jstring, jbyteArray, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getDigestLength
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_gmssl_GmSSL_getDigestLength
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getDigestBlockSize
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_gmssl_GmSSL_getDigestBlockSize
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    digest
 * Signature: (Ljava/lang/String;[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_digest
  (JNIEnv *, jobject, jstring, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getMacLength
 * Signature: (Ljava/lang/String;)[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getMacLength
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    mac
 * Signature: (Ljava/lang/String;[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_mac
  (JNIEnv *, jobject, jstring, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    generatePrivateKey
 * Signature: ()[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_generatePrivateKey
  (JNIEnv *, jobject);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getPublicKey
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_getPublicKey
  (JNIEnv *, jobject, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    sign
 * Signature: (Ljava/lang/String;[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_sign
  (JNIEnv *, jobject, jstring, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    verify
 * Signature: (Ljava/lang/String;[B[B[B)I
 */
JNIEXPORT jint JNICALL Java_org_gmssl_GmSSL_verify
  (JNIEnv *, jobject, jstring, jbyteArray, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    publicKeyEncrypt
 * Signature: (Ljava/lang/String;[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_publicKeyEncrypt
  (JNIEnv *, jobject, jstring, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    publicKeyDecrypt
 * Signature: (Ljava/lang/String;[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_publicKeyDecrypt
  (JNIEnv *, jobject, jstring, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    deriveKey
 * Signature: (Ljava/lang/String;I[B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_gmssl_GmSSL_deriveKey
  (JNIEnv *, jobject, jstring, jint, jbyteArray, jbyteArray);

/*
 * Class:     org_gmssl_GmSSL
 * Method:    getErrorStrings
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_gmssl_GmSSL_getErrorStrings
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif

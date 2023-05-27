package dev.brunofelix.marvelapp.extension

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.toMd5Hash(): String {
    var pass = this
    var encryptedString: String? = null
    val md5: MessageDigest
    try {
        md5 = MessageDigest.getInstance("MD5")
        md5.update(pass.toByteArray(), 0, pass.length)
        pass = BigInteger(1, md5.digest()).toString(16)
        while (pass.length < 32) {
            pass = "0$pass"
        }
        encryptedString = pass
    } catch (e1: NoSuchAlgorithmException) {
        e1.printStackTrace()
    }
    return encryptedString ?: ""
}

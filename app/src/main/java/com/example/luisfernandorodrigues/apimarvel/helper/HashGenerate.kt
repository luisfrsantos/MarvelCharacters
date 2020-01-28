package com.example.luisfernandorodrigues.apimarvel.helper

import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*

private const val PATTERN = "yyyyMMddHHmmss"
private const val MD5 = "MD5"
private const val RADIX = 16
private const val SIGUM = 1

class HashGenerate {

    val ts: String = SimpleDateFormat(PATTERN).format(Date())

    fun getHash(ts: String, privateKey: String, apiKey: String): String {
        val canonicalString = ts + privateKey + apiKey
        val messageDigest = java.security.MessageDigest.getInstance(MD5)
        messageDigest.reset()
        messageDigest.update(canonicalString.toByteArray())

        return BigInteger(SIGUM, messageDigest.digest()).toString(RADIX)
    }
}
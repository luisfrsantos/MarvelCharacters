package com.example.luisfernandorodrigues.apimarvel

import com.example.luisfernandorodrigues.apimarvel.helper.HashGenerate
import org.junit.Test

private const val PRIVATE_KEY = "74138d2135bab23e402b9bd6292337aec6ad93e2"
private const val API_KEY = "40b99748c5946df64d95ffa4cb37fb07"
private const val TS = "20200101101010"
private const val HASH_VALIDATION = "cea09646ad1851262221d534de848d3"

class HashGenerateTest {

    @Test
    fun get_hash_test() {
        val hashGenerate = HashGenerate()
        val hash = hashGenerate.getHash(TS, PRIVATE_KEY, API_KEY)
        assert(hash.isNotEmpty())
        assert(hash == HASH_VALIDATION)
    }
}
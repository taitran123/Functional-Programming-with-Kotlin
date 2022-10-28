package my

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

fun generateHashId(data1: String?, data2: String?, data15: ByteArray?): String {
    val sb = StringBuilder()
    var hashInput: ByteArray? = null
    if (data1!=null || data2!=null) {
        sb.append(data1)
        sb.append(data2)
        hashInput = sb.toString().toByteArray()
    } else if (data15 != null) {
        hashInput = data15
    }
    if (hashInput != null&&hashInput.isNotEmpty()) {
        return try {
            val mMessageDigest = MessageDigest.getInstance("SHA-1")
            val hashResult: ByteArray = mMessageDigest.digest(hashInput)
            Base64.getEncoder().encodeToString(hashResult)
        } catch (e: NoSuchAlgorithmException) {
            "hash_error"
        }
    }
    return "hash_error"
}

fun main() {
//    val data4 = ""
//    val data3 = ""
//    val data6 =""
//    val data2="contactPager"
//    val names = listOf(data4, data2, data3, data6)
//    val data1 = names.filter { it -> it.isNotEmpty() }.joinToString(separator = " ")

    val data1 = "http://lienxo.my"
    val data2 = "0"

    val temp1 = "Street1 , Lincon ways Strees2, Le Thanh Phuong Nha Trang, Khanh Hoa 65000 United States "
    val teml2 = "1"
    val hash = generateHashId(data1, data2, null)
    println(hash)
    val hash2 = generateHashId(temp1, teml2, null)
    println(hash2)
}
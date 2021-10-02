package com.loens2.autoDynDNS

import java.io.IOException
import java.net.URL

var lastIPv4 = ""
var lastIPv6 = ""


fun getPublicIPv4():String {
    return try {
        URL(IPv4Api).readText()
    }
    catch (e: Exception) {
        println(e)
        getPublicIPv4()
    }
}

fun getPublicIPv6():String {
    return try {
        URL(IPv6Api).readText()
    }
    catch (e: Exception) {
        println(e)
        getPublicIPv6()
    }
}

fun checkIPChange() {
    val publicIPv4 = getPublicIPv4()
    val publicIPv6 = getPublicIPv6()

    if (publicIPv4.isEmpty() || publicIPv6.isEmpty())
    if(lastIPv4 == publicIPv4 && lastIPv6 == publicIPv6) {
        println("Still up to date!")
        return
    }
    if (lastIPv4.isEmpty() || lastIPv6.isEmpty()) {
        lastIPv4 = publicIPv4
        lastIPv6 = publicIPv6
        updateDynDNS(publicIPv4,publicIPv6)
        println("$lastIPv4 , $lastIPv6")
    }
    if (lastIPv4 != publicIPv4 && lastIPv6 != publicIPv6) {
        lastIPv4 = publicIPv4
        lastIPv6 = publicIPv6
        updateDynDNS(publicIPv4,publicIPv6)
        println("$lastIPv4 , $lastIPv6")
    }
    if (lastIPv4 != publicIPv4) {
        lastIPv4 = publicIPv4
        println(lastIPv4)
        updateDynDNSIPv4(publicIPv4)
    }
    if (lastIPv6 != publicIPv6) {
        lastIPv6 = publicIPv6
        println(lastIPv6)
        updateDynDNSIPv6(publicIPv6)
    }
}
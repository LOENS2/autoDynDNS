package com.loens2.autoDynDNS

import java.net.URL

var lastIPv4 = ""
var lastIPv6 = ""



fun getPublicIPv4():String {
    return URL(IPv4Api).readText()
}

fun getPublicIPv6():String {
    return URL(IPv6Api).readText()
}

fun checkIPChange() {
    if (lastIPv4.isEmpty()) {
        lastIPv4 = getPublicIPv4()
        println(lastIPv4)
        updateDynDNS(getPublicIPv4(),getPublicIPv6())
    }
    if (lastIPv6.isEmpty()) {
        lastIPv6 = getPublicIPv6()
        println(lastIPv6)
        updateDynDNS(getPublicIPv4(),getPublicIPv6())
    }
    if (lastIPv4 != getPublicIPv4()) {
        println(lastIPv4)
        updateDynDNS(getPublicIPv4(),getPublicIPv6())
    }
    if (lastIPv6 != getPublicIPv6()) {
        println(lastIPv6)
        updateDynDNS(getPublicIPv4(),getPublicIPv6())
    }
}
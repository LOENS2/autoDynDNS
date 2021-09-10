package com.loens2.autoDynDNS

import java.net.URL

class getIP {
}

fun getPublicIPv4():String {
    return URL("").readText()

}
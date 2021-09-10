package com.loens2.autoDynDNS

import java.net.URL

var lastIPv4 = ""
var lastIPv6 = ""


class getIP {


    fun getPublicIPv4():String {
        return URL(configSave().getIPv4Api()).readText()
    }

    fun getPublicIPv6():String {
        return URL(configSave().getIPv6Api()).readText()
    }

    fun checkIPChange() {
        if (lastIPv4.isEmpty()) {
            lastIPv4 = getPublicIPv4()
        } else if (getPublicIPv4() != lastIPv4) {
            setDynDNS().updateDynDNS(getPublicIPv4(),getPublicIPv6())
        }
        if (lastIPv6.isEmpty()) {
            lastIPv6 = getPublicIPv6()
        } else if (getPublicIPv4() != lastIPv6) {
            setDynDNS().updateDynDNS(getPublicIPv4(),getPublicIPv6())
        }
    }

}
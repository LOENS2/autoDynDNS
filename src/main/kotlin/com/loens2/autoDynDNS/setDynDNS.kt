package com.loens2.autoDynDNS

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*




fun updateDynDNS(IPv4: String, IPv6: String) {
    try {
        val urlConnect = URL("$Server?hostname=$hostName&myip=$IPv4,$IPv6").openConnection()
        val userPass = "$userName:$password"
        val basicAuth = "Basic " + String(Base64.getEncoder().encode(userPass.toByteArray()))
        urlConnect.setRequestProperty("Authorization", basicAuth)
        val content = BufferedReader(InputStreamReader(urlConnect.getInputStream())).readText()

        checkResult(content)
    }
    catch (e: Exception) {
        println(e)
    }
}

fun updateDynDNSIPv4(IPv4:String) {
    try {
        val urlConnect = URL("$Server?hostname=$hostName&myip=$IPv4").openConnection()
        val userPass = "$userName:$password"
        val basicAuth = "Basic " + String(Base64.getEncoder().encode(userPass.toByteArray()))
        urlConnect.setRequestProperty("Authorization", basicAuth)
        val content = BufferedReader(InputStreamReader(urlConnect.getInputStream())).readText()

        checkResult(content)
    }
    catch (e: Exception) {
        println(e)
    }
}

fun updateDynDNSIPv6(IPv6: String) {
    try {
        val urlConnect = URL("$Server?hostname=$hostName&myip=$IPv6").openConnection()
        val userPass = "$userName:$password"
        val basicAuth = "Basic " + String(Base64.getEncoder().encode(userPass.toByteArray()))
        urlConnect.setRequestProperty("Authorization", basicAuth)
        val content = BufferedReader(InputStreamReader(urlConnect.getInputStream())).readText()

        checkResult(content)
    }
    catch (e: Exception) {
        println(e)
    }
}



fun checkResult(content:String) {
    println(content)

    if (Regex("badauth").containsMatchIn(content)) {
        setSystemTrayWarning("Wrong DynDNS Credentials!")
    }

    if (Regex("notfqdn").containsMatchIn(content)) {
        setSystemTrayWarning("Wrong hostname!")
    }

    if (Regex("nohost").containsMatchIn(content)) {
        setSystemTrayWarning("Hostname doesn't match the credentials!")
    }

    if (Regex("numhost").containsMatchIn(content)) {
        setSystemTrayWarning("Too many hosts to be updated (more than 20)")
    }

    if (Regex("abuse").containsMatchIn(content)) {
        setSystemTrayWarning("Hostname is blocked for abuse (Too many updates in short time period)")
    }
}





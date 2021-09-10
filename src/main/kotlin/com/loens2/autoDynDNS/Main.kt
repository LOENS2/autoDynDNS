package com.loens2.autoDynDNS

class Main {

}

fun main() {
    configSave().createConfigFile()
    configSave().readConfigFile()
    val updateChecker = Thread{
        while (true) {
        getIP().checkIPChange()
        Thread.sleep(600000)
        }
    }
    updateChecker.join()
    updateChecker.start()
}
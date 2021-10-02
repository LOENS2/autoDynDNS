package com.loens2.autoDynDNS


fun main() {
    createConfigFile()
    readConfigFile()
    activateOnStartup()
    setSystemTrayInfo("Startup successful")
    println("Startup successful")
    val updateChecker = Thread{
        while (true) {
            println("Checking IP")
            checkIPChange()
            Thread.sleep(interval*1000)
        }
    }
    updateChecker.isDaemon = true
    updateChecker.join()
    updateChecker.start()
}
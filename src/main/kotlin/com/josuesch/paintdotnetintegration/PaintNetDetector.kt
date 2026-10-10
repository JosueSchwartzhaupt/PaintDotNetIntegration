package com.josuesch.paintdotnetintegration

import java.io.File
import java.io.InputStreamReader
import java.io.BufferedReader

object PaintNetDetector {
    fun detect(): String? {
        // 1. Common paths
        val commonPaths = listOf(
            "C:\\Program Files\\paint.net\\PaintDotNet.exe",
            "C:\\Program Files (x86)\\paint.net\\PaintDotNet.exe"
        )
        for (path in commonPaths) {
            if (File(path).exists()) return path
        }

        // 2. Microsoft Store apps path
        val localAppData = System.getenv("LOCALAPPDATA")
        if (localAppData != null) {
            val storePath = "$localAppData\\Microsoft\\WindowsApps\\PaintDotNet.exe"
            if (File(storePath).exists()) return storePath
        }

        // 3. Registry (HKLM)
        var regPath = getPathFromRegistry("HKLM\\SOFTWARE\\paint.net")
        if (regPath != null && File(regPath).exists()) {
            return regPath
        }
        
        // 4. Registry (HKCU)
        regPath = getPathFromRegistry("HKCU\\SOFTWARE\\paint.net")
        if (regPath != null && File(regPath).exists()) {
            return regPath
        }

        return null
    }

    private fun getPathFromRegistry(registryKey: String): String? {
        try {
            val process = Runtime.getRuntime().exec(arrayOf("reg", "query", registryKey, "/v", "TARGETDIR"))
            process.waitFor()
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = reader.readText()
            
            if (output.contains("REG_SZ")) {
                val path = output.substringAfter("REG_SZ").trim()
                val exePath = "$path\\PaintDotNet.exe"
                if (File(exePath).exists()) return exePath
            }
        } catch (e: Exception) {
            // Ignore exception and return null
        }
        return null
    }
}

package com.josuesch.paintdotnetintegration

import com.intellij.openapi.vfs.VirtualFile
import java.io.File

object PaintNetLauncher {

    fun open(file: VirtualFile) {
        openAll(listOf(file))
    }

    fun openAll(files: List<VirtualFile>) {
        if (files.isEmpty()) return
        val exePath = resolveExePath()
        val command = mutableListOf(exePath)
        command.addAll(files.map { it.path.replace("/", "\\") })
        ProcessBuilder(command).start()
    }

    fun launchEmpty() {
        val exePath = resolveExePath()
        ProcessBuilder(exePath).start()
    }

    private fun resolveExePath(): String {
        val settings = PaintNetSettings.getInstance()
        var exePath = settings.state.paintNetPath

        if (exePath.isEmpty() || !File(exePath).exists()) {
            val detectedPath = PaintNetDetector.detect()
            if (detectedPath != null) {
                exePath = detectedPath
                settings.state.paintNetPath = detectedPath
            } else {
                throw IllegalStateException(
                    "Paint.NET executable not found. Please configure the path in Settings \u2192 Tools \u2192 Paint.NET Integration."
                )
            }
        }
        return exePath
    }
}
package com.josuesch.paintdotnetintegration

import com.intellij.openapi.vfs.VirtualFile
import java.io.File

object PaintNetLauncher {
    // Adjust this path if Paint.NET is installed elsewhere
    private const val PAINT_NET_EXE = "C:\\Program Files\\paint.net\\PaintDotNet.exe"

    fun open(file: VirtualFile) {
        val exe = File(PAINT_NET_EXE)
        if (!exe.isFile) {
            throw IllegalStateException("Paint.NET executable not found at $PAINT_NET_EXE")
        }
        // Use ProcessBuilder with separate arguments to avoid quoting issues
        ProcessBuilder(exe.absolutePath, file.path)
            .redirectErrorStream(true)
            .start()
    }
}
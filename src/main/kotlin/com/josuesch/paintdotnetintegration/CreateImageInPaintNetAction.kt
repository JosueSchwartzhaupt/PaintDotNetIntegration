package com.josuesch.paintdotnetintegration

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class CreateImageInPaintNetAction : AnAction(), DumbAware {

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
        val project = event.project
        event.presentation.isEnabledAndVisible = project != null && (file == null || file.isDirectory)
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val selectedFile = event.getData(CommonDataKeys.VIRTUAL_FILE)

        // Target directory: selected directory, or project base dir
        val targetDir = if (selectedFile != null && selectedFile.isDirectory) {
            selectedFile
        } else {
            project.baseDir
        } ?: return

        // Ask user for new image file name
        val fileName = Messages.showInputDialog(
            project,
            "Enter image file name:",
            "Create Image in Paint.NET",
            Messages.getQuestionIcon(),
            "new_image.png",
            null
        )

        if (fileName.isNullOrBlank()) return

        val finalName = if (fileName.contains(".")) fileName else "$fileName.png"
        val newFile = File(targetDir.path, finalName)

        if (newFile.exists()) {
            Messages.showErrorDialog(project, "File '$finalName' already exists!", "Error")
            return
        }

        try {
            // Write a blank 800x600 transparent image
            val img = BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB)
            ImageIO.write(img, "png", newFile)

            // Refresh VFS and open in Paint.NET
            ApplicationManager.getApplication().runWriteAction {
                targetDir.refresh(false, true)
                val vf = targetDir.findChild(finalName)
                if (vf != null) {
                    PaintNetLauncher.open(vf)
                } else {
                    PaintNetLauncher.openFileDirectly(newFile)
                }
            }
        } catch (e: Exception) {
            Messages.showErrorDialog(
                project,
                e.message ?: "Failed to create image file",
                "Paint.NET Integration Error"
            )
        }
    }
}

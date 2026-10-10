package com.josuesch.paintdotnetintegration

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.Messages

class EditInPaintNetAction : AnAction(), DumbAware {

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(event: AnActionEvent) {
        val files = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY)
        val hasImage = files?.any { isImage(it) } == true
        event.presentation.isVisible = hasImage
        event.presentation.isEnabled = hasImage
    }

    override fun actionPerformed(event: AnActionEvent) {
        val files = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY) ?: return
        val imageFiles = files.filter { isImage(it) }
        if (imageFiles.isEmpty()) return

        FileDocumentManager.getInstance().saveAllDocuments()

        try {
            PaintNetLauncher.openAll(imageFiles)
        } catch (e: Exception) {
            Messages.showErrorDialog(
                event.project,
                e.message ?: "Failed to open Paint.NET",
                "Paint.NET Integration Error"
            )
        }
    }

    private fun isImage(file: com.intellij.openapi.vfs.VirtualFile): Boolean {
        return file.extension?.lowercase() in SUPPORTED_EXTENSIONS
    }

    companion object {
        private val SUPPORTED_EXTENSIONS = setOf(
            "png",
            "jpg",
            "jpeg",
            "bmp",
            "gif",
            "webp",
            "tga"
        )
    }
}
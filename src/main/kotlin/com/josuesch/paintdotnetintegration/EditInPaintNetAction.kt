package com.josuesch.paintdotnetintegration

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.DumbAware

class EditInPaintNetAction : AnAction(), DumbAware {

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
        val isImg = file?.let { isImage(it) } == true
        event.presentation.isVisible = isImg
        event.presentation.isEnabled = isImg
    }

    override fun actionPerformed(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        FileDocumentManager.getInstance().saveAllDocuments()
        PaintNetLauncher.open(file)
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
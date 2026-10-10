package com.josuesch.paintdotnetintegration

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.fileEditor.FileDocumentManager

@ActionUpdateThread(ActionUpdateThread.BGT)
class EditInPaintNetAction : AnAction(), DumbAware {

    // Show the action only for supported image files
    override fun update(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
        val isImg = file?.let { isImage(it) } == true
        event.presentation.isVisible = isImg
        event.presentation.isEnabled = isImg
    }

    override fun actionPerformed(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        // Persist any unsaved edits before opening in Paint.NET
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


import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.fileEditor.FileDocumentManager

@ActionUpdateThread(ActionUpdateThread.BGT)
class EditInPaintNetAction : AnAction(), DumbAware {

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.fileEditor.FileDocumentManager

class EditInPaintNetAction : AnAction(), DumbAware {

    // Show the action only for supported image files
    override fun update(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
        val isImg = file?.let { isImage(it) } == true
        event.presentation.isVisible = isImg
        event.presentation.isEnabled = isImg
    }

    override fun actionPerformed(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        // Persist any unsaved edits before opening in Paint.NET
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
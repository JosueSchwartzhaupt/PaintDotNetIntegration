package com.josuesch.paintdotnetintegration

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.Messages

class CreateImageInPaintNetAction : AnAction(), DumbAware {

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
        event.presentation.isEnabledAndVisible = file?.isDirectory == true
    }

    override fun actionPerformed(event: AnActionEvent) {
        try {
            PaintNetLauncher.launchEmpty()
        } catch (e: Exception) {
            Messages.showErrorDialog(
                event.project,
                e.message ?: "Failed to open Paint.NET",
                "Paint.NET Integration Error"
            )
        }
    }
}

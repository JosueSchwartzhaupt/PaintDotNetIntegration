package com.josuesch.paintdotnetintegration

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.util.ui.FormBuilder
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class PaintNetSettingsConfigurable : Configurable {
    private var pathField: TextFieldWithBrowseButton? = null
    private var myPanel: JPanel? = null

    override fun getDisplayName(): String = "Paint.NET Integration"

    override fun createComponent(): JComponent? {
        pathField = TextFieldWithBrowseButton()
        pathField?.addBrowseFolderListener(
            "Select Paint.NET Executable",
            "Choose the PaintDotNet.exe file",
            null,
            FileChooserDescriptorFactory.createSingleFileDescriptor()
        )

        val autoDetectBtn = JButton("Auto-detect")
        autoDetectBtn.addActionListener {
            val detected = PaintNetDetector.detect()
            if (detected != null) {
                pathField?.text = detected
            }
        }

        val buttonPanel = JPanel(FlowLayout(FlowLayout.LEFT, 0, 0)).apply {
            add(autoDetectBtn)
        }

        myPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Paint.NET path:", pathField!!)
            .addComponent(buttonPanel)
            .addComponentFillVertically(JPanel(), 0)
            .panel

        return myPanel
    }

    override fun isModified(): Boolean {
        val settings = PaintNetSettings.getInstance()
        return pathField?.text != settings.state.paintNetPath
    }

    override fun apply() {
        val settings = PaintNetSettings.getInstance()
        settings.state.paintNetPath = pathField?.text ?: ""
    }

    override fun reset() {
        val settings = PaintNetSettings.getInstance()
        pathField?.text = settings.state.paintNetPath
    }
}

package de.stetro.fontatlas.data

import java.awt.Canvas
import java.io.File
import javax.swing.JFileChooser


object FileService {
    fun selectFile(canvas: Canvas): File? {
        val fileChooser = JFileChooser()
        if (fileChooser.showSaveDialog(canvas) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.selectedFile
        }
        return null
    }
}
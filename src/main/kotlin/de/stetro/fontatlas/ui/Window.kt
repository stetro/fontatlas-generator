package de.stetro.fontatlas.ui

import de.stetro.fontatlas.data.FontService
import java.awt.Dimension
import javax.swing.*


class Window : JFrame("FontAtlas Generator") {

    private var fontAtlasCanvas = FontAtlasCanvas()

    private var panel = JPanel()

    init {
        // Font Selector
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(prepareFontPanel(fontAtlasCanvas::changeFont))

        // Font Altas View
        panel.add(fontAtlasCanvas)

        // Save Button
        val saveButton = JButton("Save")
        saveButton.addActionListener { fontAtlasCanvas.exportImage() }
        panel.add(saveButton)

        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setSize(512, 600)
        add(panel)
        isVisible = true
    }

    private fun prepareFontPanel(onFontChange: (String) -> Unit): JPanel {
        val fontSelector = JComboBox<String>()
        fontSelector.addActionListener { onFontChange(fontSelector.selectedItem as String) }
        FontService.getFonts().map { f -> fontSelector.addItem(f.name) }
        val fontPanel = JPanel()
        fontPanel.layout = BoxLayout(fontPanel, BoxLayout.X_AXIS)
        fontPanel.add(JLabel(" Font: "))
        fontPanel.add(fontSelector)
        fontPanel.preferredSize = Dimension(512, 40)
        return fontPanel
    }
}
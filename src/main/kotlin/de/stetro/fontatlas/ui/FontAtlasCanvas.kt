package de.stetro.fontatlas.ui

import de.stetro.fontatlas.data.FileService
import java.awt.*
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


class FontAtlasCanvas : Canvas() {
    private val paddingTop = 25
    private val paddingLeft = 5
    private val vertcalSpacing = 34.3
    private val horizontalSpacing = 32
    private val fontSize = 32

    init {
        preferredSize = Dimension(512, 512)
        background = Color.BLACK
        invalidate()
    }

    override fun paint(g: Graphics?) {
        paintFontAtlas(g, true)
    }

    private fun paintFontAtlas(g: Graphics?, grid: Boolean) {
        if (g is Graphics2D) {
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
        }
        g?.color = Color.WHITE
        g?.font = font
        for (i in 0..15) {
            for (j in 0..14) {
                g?.drawString(Character.toString((i + (j * 16) + 32).toChar()), paddingLeft + i * horizontalSpacing, paddingTop + (j * vertcalSpacing).toInt())
            }
        }
        if (grid) {
            for (i in 0..15) {
                g?.drawLine((512 / 16) * i, 0, (512 / 16) * i, 512)
                g?.drawLine(0, (512 / 15) * i, 512, (512 / 15) * i)
            }
        }
    }

    fun exportImage() {

        val selectedFile = FileService.selectFile(this)
        selectedFile.let {
            val image = BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB)
            val g = image.graphics as Graphics2D
            paintFontAtlas(g, false)
            ImageIO.write(image, "bmp", selectedFile)
        }

    }

    fun changeFont(font: String) {
        this.font = Font(font, Font.CENTER_BASELINE, fontSize)
        println("changed to $font")
        invalidate()
    }
}
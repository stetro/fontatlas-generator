package de.stetro.fontatlas.data

import java.awt.GraphicsEnvironment

object FontService {

    fun getFonts() = GraphicsEnvironment.getLocalGraphicsEnvironment().allFonts

}
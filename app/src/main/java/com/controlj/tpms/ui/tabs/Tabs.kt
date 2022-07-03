package com.controlj.tpms.ui.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.drawablepainter.DrawablePainter
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify

enum class Tabs(val icon: Iconify.IconValue) {
    Live(Iconify.IconValue.zmdi_car) {
        @Composable
        override fun DrawContent() {
            Live()
        }
    },
    History(Iconify.IconValue.zmdi_chart) {
        @Composable
        override fun DrawContent() {
        }
    },
    Settings(Iconify.IconValue.zmdi_settings) {
        @Composable
        override fun DrawContent() {
        }
    },
    Devices(Iconify.IconValue.zmdi_devices) {
        @Composable
        override fun DrawContent() {
        }
    };

    @Composable
    fun drawIcon(): DrawablePainter {
        return DrawablePainter(
            IconDrawable(LocalContext.current, icon)
                .actionBarSize()
                .color(0xFFFFFFFF.toInt())
        )
    }

    @Composable
    abstract fun DrawContent()
}
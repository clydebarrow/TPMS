package com.controlj.tpms.data

import com.hoho.android.usbserial.driver.Ch34xSerialDriver
import com.hoho.android.usbserial.driver.UsbSerialDriver
import kotlin.reflect.KClass

class Receiver(
    val vendor: Int = 0x1A86,
    val product: Int = 0x7523,
    val driver: KClass<out UsbSerialDriver> = Ch34xSerialDriver::class
) {
}
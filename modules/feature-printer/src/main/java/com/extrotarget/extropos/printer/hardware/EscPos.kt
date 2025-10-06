package com.extrotarget.extropos.printer.hardware

object EscPos {
    fun formatReceipt(title: String, body: String): ByteArray {
        val sb = StringBuilder()
        sb.append("*** ").append(title).append(" ***\n")
        sb.append(body).append("\n")
        sb.append("\n\n")
        return sb.toString().toByteArray()
    }
}

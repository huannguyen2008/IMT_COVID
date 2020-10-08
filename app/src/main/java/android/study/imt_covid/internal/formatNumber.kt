package android.study.imt_covid.internal

import java.text.DecimalFormat

fun formatNumber(value: Float): String? {
    var value = value
    val arr = arrayOf("", "K", "M", "B", "T", "P", "E")
    var index = 0
    while (value / 1000 >= 1) {
        value /= 1000
        index++
    }
    val decimalFormat = DecimalFormat("#.##")
    return java.lang.String.format("%s %s", decimalFormat.format(value), arr[index])
}
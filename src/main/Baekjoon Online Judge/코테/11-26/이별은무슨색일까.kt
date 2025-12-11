
fun main() {
    val n = readln().toInt()
    val message = when (n) {
        in 620..780 -> "Red"
        in 590..620 -> "Orange"
        in 570..590 -> "Yellow"
        in 495..570 -> "Green"
        in 450..495 -> "Blue"
        in 425..450 -> "Indigo"
        in 380..425 -> "Violet"
        else -> ""
    }
    println(message)
}
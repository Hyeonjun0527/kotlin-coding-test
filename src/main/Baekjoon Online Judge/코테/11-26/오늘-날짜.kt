import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val today = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    println(today.format(formatter))
}
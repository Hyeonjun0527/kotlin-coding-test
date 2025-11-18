import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    var t = readln().toInt()
    repeat(t) {
        var x = readln().toInt()
        println(x.div(2))
    }

}
//13 = 2 2 2 2 2 3

fun main() {
    val n = readln().toInt()
    repeat(n) {
        val s = readln()
        if (s[0]==('P')) {
            println("skipped")
        } else {
            val r = s.split("+").sumOf { it.toInt() }.toString()
            println(r)
        }
    }
}
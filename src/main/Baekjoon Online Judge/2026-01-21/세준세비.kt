
fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        readln()
        val (n, m) = readln().split(" ").map { it.toInt() }
        //n 세준 세비.
        var sejun = readln().split(" ").map { it.toInt() }.toMutableList()

        var sebi = readln().split(" ").map { it.toInt() }.toMutableList()

        val 세준맥스 = sejun.max()
        val 세비맥스 = sebi.max()

        if (세준맥스 >= 세비맥스) {
            println("S")
        } else {
            println("B")
        }


    }
}
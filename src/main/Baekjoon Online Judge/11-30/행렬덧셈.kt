
fun main(args: Array<String>) {
    val (n,m) = readln().split(" ").map {it.toInt()}

    val a = List(n) {
        readln().split(" ").map { it.toInt() }
    }

    val b = List(n) {
        readln().split(" ").map { it.toInt() }
    }

    val result = a.zip(b) { rowA, rowB ->
        rowA.zip(rowB) { x, y -> x + y }
    }

    val output = result.joinToString("\n") { row ->
        row.joinToString(" ")
    }
    print(output)


}
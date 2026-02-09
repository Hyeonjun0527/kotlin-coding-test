package backjoon.`20251115`

fun main(args: Array<String>) {
    val (n,a,b) = readln().split(" ").map { it.toInt() }

    if (a < b) {
        print("Bus")
    } else if ( a == b) {
        print("Anything")
    } else {
        print("Subway")
    }
}

//
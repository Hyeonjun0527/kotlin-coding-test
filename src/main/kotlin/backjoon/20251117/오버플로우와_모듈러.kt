fun main(args: Array<String>) {
    // 1. 여기서 자르기를 호출하려면
    val (n, m) = readln().split(" ").map { it.toLong() }

    val result = readln().split(" ").map { it.toLong() }
    val sol = result.fold(1L) { acc, e -> ((acc % m) * (e % m)) % m }
    println(sol)
}
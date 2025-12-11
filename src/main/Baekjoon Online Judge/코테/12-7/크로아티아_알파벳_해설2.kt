fun main() {
    val patterns = listOf("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")

    val result = patterns.fold(readln()) { acc, it ->
        acc.replace(it, "X")
    }

    println(result.length)
}

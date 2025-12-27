
private lateinit var mom: IntArray

private fun find(x: Int): Int {
    if (mom[x] == x) return x
    mom[x] = find(mom[x])
    return mom[x]
}

private fun union(a: Int, b: Int) {
    val rootA = find(a)
    val rootB = find(b)
    if (rootA != rootB) mom[rootB] = rootA
}

private fun isSame(a: Int, b: Int): Boolean {
    return find(a) == find(b)
}

fun main() {
    val (n, m) = readln().trim().split(' ').map { it.toInt() }

    mom = IntArray(n + 1) { it }

    repeat(m) {
        val (q, a, b) = readln().trim().split(' ').map { it.toInt() }

        if (q == 0) {
            union(a, b)
        } else {
            if (isSame(a, b)) println("YES")
            else println("NO")
        }
    }
}


private lateinit var mom: IntArray

private fun find(x: Int): Int {
    if (mom[x] == x) return x
    mom[x] = find(mom[x])
    return mom[x]
}

private fun union(a: Int, b:Int) {
    val rootA = find(a)
    val rootB = find(b)
    if (rootA != rootB) mom[rootB] = rootA
}

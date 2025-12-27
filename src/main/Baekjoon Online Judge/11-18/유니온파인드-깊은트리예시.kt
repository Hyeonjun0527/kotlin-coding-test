private lateinit var mom: IntArray

private fun find(x: Int, depth: Int = 0): Int {
    println("${"|--".repeat(depth)}find($x) 호출됨 → mom[$x] = ${mom[x]}")

    if (mom[x] == x) {
        println("${"  ".repeat(depth)}✔ $x 는 자신의 부모가 자기 자신 → root")
        return x
    }

    val root = find(mom[x], depth + 1)

    println("${"  ".repeat(depth)}경로 압축: mom[$x] = $root 로 변경됨 (원래는 ${mom[x]})")
    mom[x] = root

    return root
}

fun main() {

    // ⭐ 깊은 트리를 강제로 만들기
    // 1→2→3→4→5→6→7 형태의 스파게티 트리
    val n = 7
    mom = IntArray(n + 1)

    for (i in 1 until n) {
        mom[i] = i + 1
    }
    mom[n] = n   // 마지막은 루트

    println("[강제로 만든 깊은 트리 mom] ${mom.joinToString()}")

    println("\n=== find(1) 호출 ===")
    find(1)

    println("\n=== find(1) 호출 후 mom 배열 ===")
    println(mom.joinToString())
}

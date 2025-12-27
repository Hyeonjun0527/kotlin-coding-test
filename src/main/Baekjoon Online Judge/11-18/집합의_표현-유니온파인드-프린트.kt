private lateinit var mom: IntArray

private fun find(x: Int, depth: Int = 0): Int {
    println("${"|--".repeat(depth)}find($x) 호출됨 → mom[$x] = ${mom[x]}")

    // 루트일 경우
    if (mom[x] == x) {
        println("${"  ".repeat(depth)}✔ $x 는 자신의 부모가 자기 자신 → root")
        return x
    }

    // 재귀 호출
    val root = find(mom[x], depth + 1)

    // 경로 압축
    println("${"  ".repeat(depth)}경로 압축: mom[$x] = $root 로 변경됨 (원래는 ${mom[x]})")
    mom[x] = root

    return root
}

private fun union(a: Int, b: Int) {
    println("union($a, $b) 호출됨. union에서 find 2번 호출할 것임")
    val rootA = find(a)
    val rootB = find(b)

    println("union($a, $b) → rootA=$rootA, rootB=$rootB")

    if (rootA != rootB) {
        mom[rootB] = rootA
        println(" → root $rootB 를 $rootA 아래로 붙임")
    }
}

private fun isSame(a: Int, b: Int): Boolean {
    val ra = find(a)
    val rb = find(b)
    return ra == rb
}

fun main() {
    val (n, m) = readln().split(' ').map { it.toInt() }

    mom = IntArray(n + 1) { it }
    println("[초기 mom] ${mom.joinToString()}")

    repeat(m) {
        val (q, a, b) = readln().split(' ').map { it.toInt() }

        println("\n=== q=$q, a=$a, b=$b ===")

        if (q == 0) union(a, b)
        else println(if (isSame(a, b)) "YES" else "NO")

        println("[mom] ${mom.joinToString()}")
    }
}

//
//private lateinit var mom: IntArray   // 각 원소의 부모를 저장하는 배열(Union-Find에서 부모 포인터 역할)
//
//private fun find(x: Int): Int {
//    // x의 대표(root)를 찾는 함수 + 경로 압축 적용
//    if (mom[x] == x) return x        // x가 자기 자신이면 root
//    mom[x] = find(mom[x])            // 경로 압축: 바로 대표에게 연결시킴
//    return mom[x]                    // 대표 반환
//}
//
//private fun union(a: Int, b: Int) {
//    // a와 b를 같은 집합으로 합치는 함수
//    val rootA = find(a)              // a의 대표 노드
//    val rootB = find(b)              // b의 대표 노드
//
//    println("[union 호출] a=$a(root=$rootA), b=$b(root=$rootB)")
//
//    if (rootA != rootB) {
//        mom[rootB] = rootA           // b의 대표를 a의 대표 아래로 붙이기
//        println(" → root $rootB 가 root $rootA 에 합침")
//    } else {
//        println(" → 이미 같은 집합이므로 합치지 않음")
//    }
//}
//
//private fun isSame(a: Int, b: Int): Boolean {
//    // 두 원소의 대표가 같으면 같은 집합
//    val ra = find(a)
//    val rb = find(b)
//
//    println("[isSame 호출] a=$a(root=$ra), b=$b(root=$rb)")
//
//    return ra == rb
//}
//
//fun main() {
//    val (n, m) = readln().trim().split(' ').map { it.toInt() }   // n: 노드 수, m: 연산 수
//
//    mom = IntArray(n + 1) { it }         // 처음에는 모든 원소가 자기 자신을 부모로 가짐 (각자 독립 집합)
//
//    println("[초기 mom 배열] ${mom.joinToString()}")
//
//    repeat(m) {
//        val (q, a, b) = readln().trim().split(' ').map { it.toInt() }
//
//        println("\n=== 연산: q=$q a=$a b=$b ===")
//
//        if (q == 0) {
//            // union 연산
//            union(a, b)
//        } else {
//            // find + 연결 여부 YES/NO
//            val result = if (isSame(a, b)) "YES" else "NO"
//            println("결과 출력 → $result")
//        }
//
//        println("[현재 mom 배열] ${mom.joinToString()}")
//    }
//}

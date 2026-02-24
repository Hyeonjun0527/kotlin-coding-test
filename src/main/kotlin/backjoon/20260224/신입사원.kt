package backjoon.`20260224`//  신입 사원

fun main(args: Array<String>) {
    //이중반복하면 시간초과임. 그럼...먼저 내림차순 정렬함. 그다음 순회하는데 1 n-1,n-2, n-3...1 의 합만큼 반복하면 또 시간초과
    val t = readln().toInt()
    val sb = StringBuilder()
    repeat(t) {
        val n = readln().toInt()
        data class 사람(val 서류등수: Int, val 면접등수: Int)
        val 사람들 = mutableListOf<사람>()
        var 최소값 = Integer.MAX_VALUE
        repeat(n) {
            val (서류등수,면접등수) = readln().split(" ").map { it.toInt() }
            사람들.add(사람(서류등수,면접등수))
        }
        사람들.sortWith(compareBy { it.서류등수 })
        var cnt = 0
        사람들.forEach {
            if (it.면접등수 < 최소값) {
                cnt++//안꿀리면 cnt++
            }
            최소값 = minOf(it.면접등수, 최소값)
        }
        sb.append("${cnt}\n")
    }
    print(sb)
}

/*
(1,4), (2,5), (3,6), (4,2), (5,7), (6,1), (7,3)
첫 사람은 무조건 뽑는다고 하자.
minInterview = 4, 선발 목록: {(1,4)}

5 < 4 ? 아니지 → 탈락
이유: 서류 더 좋은 (1,4)가 이미 있고, 면접도 (1,4)가 더 좋음(4가 5보다 좋음) → 둘 다 밀림



* */
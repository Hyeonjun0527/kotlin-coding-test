package backjoon.`20260216`

fun main() {
    val N = readln().toInt()
    val list = readln().split(" ").map { it.toInt() }.toMutableList()

    var maxv = 0
    var sum = 0

    fun dfs() {
        if (list.size == 2) {
            if (sum > maxv) maxv = sum
            return
        }

        for (i in 1 until list.size - 1) {
            val left = list[i - 1]
            val right = list[i + 1]
            val energy = left * right

            val 제거한거 = list.removeAt(i)
            sum += energy

            dfs()

            sum -= energy
            list.add(i, 제거한거)
        }
    }

    dfs()
    print(maxv)
}



/*
백트래킹인 이유

브루트포스 가능하네?, 계속 같은 행위가 반복되네?
선택트리가 있나? 있네. n이 작네 그럼 브루트포스 백트래킹이네.

같은행위반복? => 백트래킹? dp?

백트래킹: “선택 트리를 끝까지 탐색해서 답을 찾는다(중복 있어도 N 작으면 OK)”
DP: “같은 상태가 자꾸 반복되니, 상태별 답을 저장해서 중복을 제거한다”

DP 목적

    **중복 상태(Overlapping Subproblems)**가 많이 생김

    같은 상태의 답을 저장해서 재사용 → 시간 줄이기

    같은 ‘상태’가 여러 번 등장해서 중복 계산이 크고, 그걸 저장해서 재사용할 수 있냐

백트래킹 목적

    가능한 선택들을 전부(또는 가지치기하며) 탐색해서

    조건을 만족하는 해를 찾거나/세거나/최적값을 구함

    상태를 바꿨다가 복구(undo) 하면서 진행

* */
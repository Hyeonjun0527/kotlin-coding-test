package backjoon.soma2cha.기출

fun main() {
    val 크기 = readln().toInt()
    val map = Array(크기) { readln().toCharArray() }
    val 추가아이템 = readln().trim()[0]

    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)

    val dx1 = intArrayOf(-1, -1, 1, 1)
    val dy1 = intArrayOf(-1, 1, -1, 1)

    val dx2 = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val dy2 = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

    fun 범위안(x: Int, y: Int): Boolean {
        return x in 0 until 크기 && y in 0 until 크기
    }

    fun 칸점수(x: Int, y: Int): Int {
        val 문자 = map[x][y]

        if (문자 == '.') return 0

        if (문자 == '+') {
            var 점수 = 0
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (범위안(nx, ny) && map[nx][ny] != '.') {
                    점수++
                }
            }
            return 점수
        }

        if (문자 == 'x') {
            var 점수 = 0
            for (i in 0 until 4) {
                val nx = x + dx1[i]
                val ny = y + dy1[i]
                if (범위안(nx, ny) && map[nx][ny] != '.') {
                    점수++
                }
            }
            return 점수
        }

        var 플러스존재 = false
        var 엑스존재 = false
        var 별존재 = false

        for (i in 0 until 8) {
            val nx = x + dx2[i]
            val ny = y + dy2[i]
            if (!범위안(nx, ny)) continue

            when (map[nx][ny]) {
                '+' -> 플러스존재 = true
                'x' -> 엑스존재 = true
                '*' -> 별존재 = true
            }
        }

        var 종류수 = 0
        if (플러스존재) 종류수++
        if (엑스존재) 종류수++
        if (별존재) 종류수++

        return 종류수 * 2
    }

    fun 후보증가점수(x: Int, y: Int, 새문자: Char): Long {
        val 영향칸들 = ArrayList<Pair<Int, Int>>()

        for (행차 in -1..1) {
            for (열차 in -1..1) {
                val nx = x + 행차
                val ny = y + 열차
                if (범위안(nx, ny)) {
                    영향칸들.add(Pair(nx, ny))
                }
            }
        }

        var 이전합 = 0L
        for ((현재행, 현재열) in 영향칸들) {
            이전합 += 칸점수(현재행, 현재열).toLong()
        }

        map[x][y] = 새문자

        var 이후합 = 0L
        for ((현재행, 현재열) in 영향칸들) {
            이후합 += 칸점수(현재행, 현재열).toLong()
        }

        map[x][y] = '.'

        return 이후합 - 이전합
    }

    var 현재총점 = 0L
    for (행 in 0 until 크기) {
        for (열 in 0 until 크기) {
            현재총점 += 칸점수(행, 열).toLong()
        }
    }

    var 최대증가점수 = Long.MIN_VALUE

    for (행 in 0 until 크기) {
        for (열 in 0 until 크기) {
            if (map[행][열] != '.') continue

            val 증가점수 = 후보증가점수(행, 열, 추가아이템)
            최대증가점수 = maxOf(최대증가점수, 증가점수)
        }
    }

    println(현재총점 + 최대증가점수)
}

/*
*
* 맞아. 전체점수를 매번 계산하면 시간초과 쪽으로 간다.

왜냐하면 빈칸 하나 시험할 때마다
격자 전체 n x n을 다시 돌면서 모든 칸 점수를 새로 구하면,
후보가 너무 많기 때문이야.

처음부터 감으로 보면,
빈칸이 최대 거의 n^2개 있고,
각 빈칸마다 “여기 두면 어떨까”를 확인해야 한다.
그때 매번 전체 점수를 다시 계산하면
후보 하나당 O(n^2)가 들고,
후보도 O(n^2)개니까
전체가 O(n^4)가 된다.
n ≤ 1000이면 이건 절대 못 버틴다.
그래서 이 문제는 전체점수를 다시 구하는 방식이 아니라,
점수가 바뀔 수 있는 주변 칸만 다시 계산해서 증가량만 구해야 한다.
즉 핵심은 “총점 재계산”이 아니라 “변화량 계산”이다.
*
* */
package backjoon.치트시트.실전문제모음.시뮬레이션

// 알고리즘 분류: 시뮬레이션

fun main(args: Array<String>) {
    val n = readln().toInt()
    val sb = StringBuilder()
    var dx = arrayOf(0,1,0,-1)
    var dy = arrayOf(1,0,-1,0)
    repeat(n) {
        val orderList = readln()
        var dir = 0
        var x = 0
        var y = 0
        var minX = 0
        var minY = 0
        var maxX = 0
        var maxY = 0

        fun 사각형계산() {
            maxX = maxOf(maxX, x)
            minX = minOf(minX, x)
            maxY = maxOf(maxY, y)
            minY = minOf(minY, y)
        }
        for (order in orderList) {
            when(order) {
                'L'-> dir = (dir + 3) % 4//4 -> 0,1,2,3
                'R'-> dir = (dir + 1) % 4
                'F'-> {
                    x += dx[dir]
                    y += dy[dir]
                    사각형계산()
                }
                'B' -> {
                    x -= dx[dir]
                    y -= dy[dir]
                    사각형계산()
                }
            }
        }
        val width = maxX - minX
        val height = maxY - minY
        sb.append(width*height).append("\n")
    }
    print(sb)
}
/*
문제를 한 문장으로 요약

명령 문자열(L/R/F/B)을 따라 거북이를 이동시켰을 때, 거북이가 지나간 모든 위치들을 포함하는 가장 작은 직사각형의 넓이를 구한다.

여기서 직사각형은 “축에 평행한(minX~maxX, minY~maxY)” 박스라고 보면 된다.

핵심 아이디어 1) “경로 전체”를 저장할 필요가 없다

거북이가 지나간 점이 수백/수천 개가 될 수 있지만, 넓이를 구하는 데 필요한 건 딱 4개뿐이다.

지나간 x 중 최솟값 minX

지나간 x 중 최댓값 maxX

지나간 y 중 최솟값 minY

지나간 y 중 최댓값 maxY

왜냐하면 축에 평행한 최소 직사각형은 결국

가로 길이 = maxX - minX

세로 길이 = maxY - minY

으로 정해지기 때문.

그래서 너의 코드는 “모든 점 기록” 대신 이동할 때마다 min/max만 갱신한다. 이게 가장 깔끔하고 빠른 방식.

핵심 아이디어 2) 방향(dir)을 0~3으로 두고 dx/dy로 이동한다

방향을 “북/동/남/서” 같은 문자열로 관리하면 매번 조건문이 복잡해지는데,
정수 0~3으로 방향을 표현하면 회전도 이동도 한 줄로 끝난다.

너의 코드:

var dx = arrayOf(0,1,0,-1)
var dy = arrayOf(1,0,-1,0)
var dir = 0

이게 의미하는 방향 체계는:

dir = 0 → (0, 1) : 위로(북)

dir = 1 → (1, 0) : 오른쪽(동)

dir = 2 → (0, -1): 아래(남)

dir = 3 → (-1, 0): 왼쪽(서)

즉 “dir에 해당하는 dx,dy를 더하면 앞으로 가는 것”.

핵심 아이디어 3) 회전은 mod 4로 처리한다

오른쪽 회전 R은 dir에 +1, 왼쪽 회전 L은 dir에 -1인데,
음수 처리 귀찮으니까 이렇게 한다:

'L' -> dir = (dir + 3) % 4
'R' -> dir = (dir + 1) % 4

(dir + 1) % 4 : 0→1→2→3→0

(dir + 3) % 4 : 사실상 -1과 동일 (0에서 왼쪽 돌면 3으로)

이 패턴은 방향 문제에서 거의 “정석”임.

핵심 아이디어 4) F/B는 “현재 방향을 기준으로” 이동한다.

F: 현재 dir 방향으로 한 칸 전진

B: 현재 dir 방향으로 한 칸 후진 (전진의 반대)

너의 코드:

'F' -> {
  x += dx[dir]
  y += dy[dir]
  사각형계산()
}
'B' -> {
  x -= dx[dir]
  y -= dy[dir]
  사각형계산()
}

여기서 중요한 포인트:

B는 “방향을 뒤집는 게 아니라” 현재 방향 벡터를 빼는 것

즉 “방향(dir)은 그대로, 위치만 반대로 1칸”

핵심 아이디어 5) 이동할 때마다 사각형 범위를 갱신한다

너의 사각형계산():

fun 사각형계산() {
  maxX = maxOf(maxX, x)
  minX = minOf(minX, x)
  maxY = maxOf(maxY, y)
  minY = minOf(minY, y)
}

이걸 이동(F/B) 직후에만 호출하는 이유:

L/R은 위치가 변하지 않아서 min/max가 바뀔 일이 없다.

실제로 “지나간 점”은 이동했을 때만 새로 생긴다.

(참고로 시작점 (0,0)을 포함시키려면 초기값을 0으로 잡으면 자동 포함됨. 너는 minX=minY=maxX=maxY=0이라 시작점 포함 완료.)

마지막 계산: 넓이

마지막에

val width = maxX - minX
val height = maxY - minY
sb.append(width*height)

width/height는 항상 0 이상

한 번도 이동 안 했으면 min=max라서 넓이 0

이게 문제에서 요구하는 “가장 작은 직사각형” 넓이.
* */

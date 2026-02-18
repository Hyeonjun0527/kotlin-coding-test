package backjoon

fun main() {
    val (h, w) = readln()
        .split(" ")
        .map { it.toInt() }
    val n = readln().toInt()
    val 스티커들 = mutableListOf<Pair<Int, Int>>()
    repeat(n) {
        val (r, c) = readln()
            .split(" ")
            .map { it.toInt() }
        스티커들.add(r to c)
    }

    var maxv = 0

    for (i in 스티커들.indices) {
        for (j in i + 1..스티커들.lastIndex) {
            val (r1, c1) = 스티커들[i]
            val (r2, c2) = 스티커들[j]

            //회전고려. 가로로붙이냐 세로로 붙이냐 고려.
            val 회전고려 = arrayOf(
                arrayOf(r1, c1), arrayOf(c1, r1)
            )
            val 회전고려2 = arrayOf(
                arrayOf(r2, c2), arrayOf(c2, r2)
            )

            for ((r1,c1) in 회전고려) {
                for ((r2,c2) in 회전고려2) {
                    val 가로가능 = r1 + r2 <= w && c1 <= h && c2 <= h
                    val 세로가능 = c1 + c2 <= h && r1 <= w && r2 <= w
                    val 넓이 = r1 * c1 + r2 * c2
                    if (가로가능 || 세로가능) {
                        maxv = maxOf(넓이,maxv)
                    }
                }
            }
        }
    }
    print(maxv)
}

/*

0 1 2 3 4

i j
0 1,2,3,4
1 2,3,4

스티커 2개를 붙이는거고 겹치면 안됨.
스티커회전가능 벗어나면안댐.
스티커 여러개임.
 그러면 브루트포스하면, 스티커가 n개잖아.
  전부반복해도됨.
   2개택하는거니. 일단 2개 선택하고, 두 스티커 잡앗다고 하자.
    그거 가능한 넓이를 생각해보자.
     이런의심이 먼저 듬 회전하는게 의미가 잇나?
      회전해서 붙였을때 불가능할수가 잇음.
       스티커 회전하느냐 스티커 2개 가로로붙이냐
       세로로붙이냐에 따라 성공여부가 갈림.
        단순히 최대값이면 큰스티커 고르면 되나?
         아님. 작은스티커 2개가 딱맞을수도.
          그럼 어케해야대 브루트포스 다 순회해봐야함.
           그리고 max 값 찾아야함.

즉. 회전을 고려해야함. 스티커 2개 붙이는 방식을 고려해야함.
이걸 이산화할 수 있나?
이산화할수있음. 얼마안됨.
회전 90도 돌린버전과 안돌린버전 딱 2가지임.
그리고 스티커 2개가 붙이는 방식은 가로로 세로로 딱 2가지임.
가로로햇을때 두스티커 순서는 상관없음. 직사각형이므로 대칭임.


* */

fun main(args: Array<String>) {
    val n = readln().toInt()
    val list = intArrayOf(1, 5, 10, 50)
    val result = mutableSetOf<Int>()

    fun algo(cur: Int, cnt: Int, sum: Int) {

        if (cnt == n) {
            result.add(sum)
            return
        }

        for (i in cur until list.size) {
            algo(i,
                cnt + 1,
                sum + list[i]
            )
        }
    }
    algo(0,0,0)
    println(result.size)
}

/*
그러면 이거 문제 보면 음 일단 1, 5 ,10 ,50을 택하는거로 시작하는군.
다음 상태(n=2)에서는 각 1,5,10,50 경우에 대해서 자기자신보다 크거나 같은 수만큼 고르네. 그걸 다음 상태에도 반복하네?
그러면 음. n.. n+1 n+2....계속 같은 규칙이 적용되네.
같은 규칙이 마아아악 확산되네. 이건 재귀다.
그러면 재귀로 이걸 그대로 구현하자.
재귀는 상태정의 종료조건 상태전이야.
상태 정의하면 일단 sum을 계속 추적해야하고, set에 넣어야겟고. 현재 내가 보고 잇는값.이 상태가 기본적으로 되어야하고, cnt 현재 내가 얼마까지 택했는지 추적해야겠네.
그리고 종료조건은 cnt n만큼 셋을때 멈추면 되나? 한 규칙이 그만큼 라이프사이클 가지네.
상태전이는? cnt++되고, sum 구해주고, 현재 상태 바꺼주면 되네. 현재 상태는 자기보다 같거나 큰수만 1,5,10,50 배열 순회하면 되겟네.
dfs랑 이웃을 쫘악 순회함 ?= 배열을 쫘악 순회함 비슷함.
중복조합로직임.
* */
//  트리의 부모 찾기

fun main(args: Array<String>) {
    //노드번호순서로 소팅해야함.
    //소팅되면 반복을 함. 반복의 하나의 케이스 보자.
    // 5라고 해. 5가 주어짐. 그러면 어케하냐. 트리를 dfs 순회해서 5를 찾아.
    //5를 찾으면 5의 바로 이전 노드를 출력 한다.

    val n = readln().toInt()
    val adjList = MutableList (n + 1) { mutableListOf<Int>() }

    repeat(n-1) {//입력받기
        val (a, b) = readln().split(" ").map { it.toInt() }
        adjList[a].add(b)
        adjList[b].add(a)
    }
    var previousNode = MutableList (n + 1) { it }

//    adjList.forEach { println(it) }

    val sb = StringBuilder()

    val visited = BooleanArray(n + 1) { false }

    fun dfs(v: Int) {
        visited[v] = true
        for (j in adjList[v]) {//이웃 전부 순회
            if (visited[j]) continue
            previousNode[j] = v
            dfs(j)
        }
    }
    dfs(1)

    for (i in 2..n) {
        sb.append("${previousNode[i]}\n")
    }
//    previousNode.drop(2)
//    previousNode.forEach {
//        sb.append("${it}\n")
//    }
    print(sb)
}
//리스트 클리어하는법 모르겟음 왜 드랍 안대는지 모르겟?
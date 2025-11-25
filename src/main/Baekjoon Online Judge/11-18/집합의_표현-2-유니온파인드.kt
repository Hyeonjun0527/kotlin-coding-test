
private lateinit var mom: IntArray

//부모 대표 노드를 찾는 함수
private fun find(x: Int): Int {
    if (mom[x] == x) return x
    mom[x] = find(mom[x])
    return mom[x]
}

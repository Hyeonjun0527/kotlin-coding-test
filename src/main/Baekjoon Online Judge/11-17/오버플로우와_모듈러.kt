
fun main(args: Array<String>) {
    //N과 M 이 주어짐 m으로 나눠. n만큼 받아. 그거 곱해
    val (n,m) = readln().split(" ").map { it.toLong()}
    val result = readln().split(" ").map {it.toLong()}
    val sol = result.fold(1L){acc,e->((acc%m)*(e%m))%m}
    println(sol)
}
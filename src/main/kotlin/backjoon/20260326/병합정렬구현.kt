package backjoon.`20260326`

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.toMutableList()
    var cnt = 0
    var tmp = IntArray(n+1)
    var sol = -1
    fun merge(A: MutableList<Int>, p:Int,q:Int,r:Int) {//s,m,e
        var i = p//왼쪽 배열의 시작점
        var j = q + 1//오른쪽배열의 시작점
        var t = 1//새로운 배열에 넣을
        while(i <= q && j <= r) {
            if (A[i] <= A[j]) {
                tmp[t++] = A[i++]
            } else {
                tmp[t++] = A[j++]
            }
        }

        while (i <= q) {
            tmp[t++] = A[i++]
        }
        while (j <= r) {
            tmp[t++] = A[j++]
        }
        i = p
        t = 1
        while (i <= r) {
            A[i] = tmp[t]
            cnt++
            if (cnt == k) {
                sol = tmp[t]
            }
            i++
            t++
        }
    }

    fun merge_sort(A: MutableList<Int>, p:Int, r:Int) {
        if (p < r) {
            var q = (p+r) / 2
            merge_sort(A,p,q)
            merge_sort(A,q+1,r)
            merge(A,p,q,r)
        }
    }
    merge_sort(arr,0,arr.size-1)
    print(sol)
}


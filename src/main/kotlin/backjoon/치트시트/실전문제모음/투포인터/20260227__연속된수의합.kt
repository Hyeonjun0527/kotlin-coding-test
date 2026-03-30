package backjoon.치트시트.실전문제모음.투포인터

// 알고리즘 분류: 투포인터

class Solution7 {
    fun solution(num: Int, total: Int): IntArray {
        // num개 연속된 수를 [x, x+1, x+2, ... , x+(num-1)] 라고 두자.
        val n = num

        // x를 제외하고 뒤에 붙는 증가분의 합:
        // (0 + 1 + 2 + ... + (n-1)) = n*(n-1)/2
        // 예) n=5면 [x, x+1, x+2, x+3, x+4] 이고 증가분 합은 0+1+2+3+4 = 10
        val 합보정offset = n * (n - 1) / 2

        // 전체합 total = (x + (x+1) + ... + (x+n-1))
        //             = n*x + (0+1+...+(n-1))
        //             = n*x + 합보정offset
        // 따라서 x = (total - 합보정offset) / n
        // 문제에서 항상 가능한 케이스만 준다고 했으니 나누어떨어진다.
        val 시작start = (total - 합보정offset) / n

        // 시작값부터 연속으로 n개를 채우면 답이 된다. (이미 오름차순)
        val 결과result = mutableListOf<Int>()
        for (i in 0 until n) {
            // i=0이면 start, i=1이면 start+1 ... 이런 식으로 연속된 수 생성
            결과result.add(시작start + i)
        }

        // 프로그래머스는 IntArray로 리턴해야 해서 변환
        return 결과result.toIntArray()
    }
}

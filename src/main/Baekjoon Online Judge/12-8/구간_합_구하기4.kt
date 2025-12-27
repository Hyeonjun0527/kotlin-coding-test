/*

## 4. 그래서 prefix[j] - prefix[i-1]를 해보면

* prefix[j]
    = a1 + a2 + ... + aᵢ₋₁ + aᵢ + ... + aⱼ

* prefix[i-1]
    = a1 + a2 + ... + aᵢ₋₁


이 둘을 빼면:

> (a1 + ... + aᵢ₋₁ + aᵢ + ... + aⱼ)
> − (a1 + ... + aᵢ₋₁)
> = **aᵢ + aᵢ₊₁ + ... + aⱼ**

앞쪽이 **쫙 상쇄**되고,
딱 우리가 원하는 **i~j 구간합만 남는 구조**가 됨.

그래서 **공식이 정확히:**

> **i~j 합 = prefix[j] - prefix[i-1]**

이 되는 거야.
* */


fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val nums = readln().split(" ").map { it.toInt() }

    // 누적합(prefix sum) 함수형 생성
    val prefix = nums.runningFold(0) { acc, x -> acc + x }
    // prefix[0] = 0
    // prefix[k] = nums[0]부터 nums[k-1]까지의 합

    repeat(m) {
        val (i, j) = readln().split(" ").map { it.toInt() }
        val result = prefix[j] - prefix[i - 1]
        println(result)
    }
}

/*
val prefix = mutableListOf<Int>()

var acc = 0               // 초기값(초깃값)
prefix.add(acc)           // prefix[0] = 0

for (x in nums) {         // nums 요소를 앞에서부터 하나씩 순회
    acc = acc + x         // 새 누적값 계산
    prefix.add(acc)       // prefix에 넣기
}

* */
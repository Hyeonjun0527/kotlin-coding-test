package backjoon.치트시트.함수형프로그래밍

fun main() {

    // ==========================================
    println("""
        1. filter + map (국룰 패턴)
        상황: 데이터 중 필요한 것만 골라내서(Filter), 형태를 변형(Map)하고 싶을 때.
        예시: 1~5 중 '짝수'만 골라 '제곱'한 리스트 만들기.
        기존 방식: for문 돌며 if 체크하고 새 리스트에 add.
        함수형 패턴:
    """.trimIndent())

    val list1 = listOf(1, 2, 3, 4, 5)
    val result1 = list1.filter { it % 2 == 0 } // [2, 4]
        .map { it * it }        // [4, 16]
    println("결과: $result1")


    // ==========================================
    println("""
        
        2. groupBy (도감 만들기/분류하기)
        상황: 데이터를 특정 기준에 따라 그룹 지어 Map으로 만들고 싶을 때.
        예시: 단어들을 '글자 수' 별로 묶기.
        기존 방식: Map 만들고, key 있는지 확인하고, 리스트 꺼내서 add...
        함수형 패턴:
    """.trimIndent())

    val list2 = listOf("a", "bb", "c", "ddd", "ee")
    val result2 = list2.groupBy { it.length }
    println("결과: $result2")
    // 출력: {1=[a, c], 2=[bb, ee], 3=[ddd]}


    // ==========================================
    println("""
        
        3. flatMap (상자 펼치기)
        상황: 리스트 안의 리스트(2차원)를 1차원으로 쫙 펴고 싶을 때.
        예시: [[1, 2], [3], [4, 5]] -> [1, 2, 3, 4, 5]
        기존 방식: 이중 for문 돌면서 하나하나 꺼내서 add.
        함수형 패턴:
    """.trimIndent())

    val list3 = listOf(listOf(1, 2), listOf(3), listOf(4, 5))
    val result3 = list3.flatMap { it } // 또는 list3.flatten()
    println("결과: $result3")


    // ==========================================
    println("""
        
        4. zip / zipWithNext (커플 맺기)
        상황: 서로 다른 두 리스트를 묶거나, 연속된 요소(나와 옆 놈)를 비교할 때.
        예시: "AABBA"에서 앞뒤 글자가 바뀌는 구간 찾기.
        기존 방식: for(i in 0 until n-1) 후 arr[i]와 arr[i+1] 비교.
        함수형 패턴:
    """.trimIndent())

    val str4 = "AABBA"
    val result4 = str4.zipWithNext { a, b ->
        if (a != b) "$a->$b (변경)" else "유지"
    }
    println("결과: $result4")


    // ==========================================
    println("""
        
        5. fold (눈덩이 굴리기)
        상황: 리스트 데이터를 하나로 응축(Reduce)시킬 때. (합계, 문자열 합치기 등)
        예시: 리스트 [1, 2, 3]을 "1->2->3" 문자열로 만들기.
        기존 방식: StringBuilder 만들고 루프 돌며 append (마지막 화살표 처리 복잡).
        함수형 패턴:
    """.trimIndent())

    val list5 = listOf(1, 2, 3)
    val result5 = list5.fold("") { acc, el ->
        if (acc.isEmpty()) "$el" else "$acc->$el"
    }
    println("결과: $result5")


    // ==========================================
    println("""
        
        6. Nested zip (행렬 덧셈 / 2차원 짝짓기)
        상황: 두 개의 2차원 리스트(행렬)의 '같은 위치'끼리 연산해야 할 때.
        예시: 행렬 A + 행렬 B.
        기존 방식: 이중 for문 돌며 arrA[i][j] + arrB[i][j] 접근 (인덱스 관리 귀찮음).
        함수형 패턴: 바깥 zip으로 행(Row)을 묶고, 안쪽 zip으로 값(Element)을 묶음.
    """.trimIndent())

    val matrixA = listOf(listOf(1, 2), listOf(3, 4))
    val matrixB = listOf(listOf(5, 6), listOf(7, 8))

    val result6 = matrixA.zip(matrixB) { rowA, rowB ->
        // 여기서 rowA는 [1, 2], rowB는 [5, 6]
        rowA.zip(rowB) { x, y -> x + y }
    }

    val output6 = result6.joinToString("\n") { row ->
        row.joinToString(" ")
    }
    println("결과:\n$output6")


    // ==========================================
    println("""
        
        7. count + all (심판관과 계수기)
        상황: 내부 요소가 '모두' 조건을 만족하는 케이스만 세고 싶을 때.
        예시: 모든 글자가 'a'인 단어는 몇 개? ("aba"는 탈락)
        기존 방식: 이중 for문, flag 변수, break 사용.
        함수형 패턴: count(세어줘) + all(전부 통과니?)
    """.trimIndent())

    val list7 = listOf("aaa", "aba", "aa")
    val result7 = list7.count { word ->
        // word의 문자(c)가 하나라도 'a'가 아니면 false 리턴 -> count 집계 제외
        word.all { c -> c == 'a' }
    }
    println("결과: $result7 개 (aaa, aa)")


    // ==========================================
    println("""
        
        8. Break Alternatives (find / takeWhile / Sequence)
        상황: for문의 break처럼 중간에 멈추거나, 효율적으로 찾고 싶을 때.
        
        [도구 설명]
        A. find: "찾으면 바로 멈추고 걔 줘." (하나 찾을 때)
        B. takeWhile: "조건 틀리면 거기서 수도꼭지 잠가." (구간 자를 때)
        C. asSequence: "미리 계산 안 하고 하나씩 주문 처리해." (대용량/무거운 연산 최적화)
    """.trimIndent())

    val list8 = listOf(1, 2, 10, 3, 4) // 10 뒤의 3, 4는 무시하고 싶음

    // A. find
    val findResult = list8.find { it > 5 } // 10 찾고 끝
    println("find 결과: $findResult")

    // B. takeWhile
    val takeResult = list8.takeWhile { it < 10 } // 10 만나는 순간 종료 (뒤에 3, 4 무시)
    println("takeWhile 결과: $takeResult")

    // C. asSequence (Lazy Evaluation)
    val seqResult = (1..100000).asSequence() // 10만 개 준비만 해둠 (계산 X)
        .map { it * 2 }     // 주문 들어온 놈만 2배
        .find { it > 50 }   // 50 넘는 거 나오면 즉시 종료 (나머지 99900개 계산 안 함)
    println("Sequence 결과: $seqResult")

    // ==========================================
    println("""
        
        9. Deduplicate Consecutive (압축하기)
        상황: '연속으로' 중복되는 것만 하나로 합치고 싶을 때. (전체 중복 제거인 distinct와 다름)
        예시: "aaabbbccd" -> "abcd" / "aabbaa" -> "aba"
        기존 방식: for문 돌며 prev 변수와 현재 값 비교해서 다를 때만 저장.
        함수형 패턴: filterIndexed { index, c -> index == 0 || 이전 것과 다름 }
    """.trimIndent())

    val str9 = "aaabbbccd"
    // 0번(첫글자)이거나, 내 앞(index-1)이랑 다르면 -> 남겨!
    val result9 = str9.filterIndexed { index, c ->
        index == 0 || str9[index - 1] != c
    }
    println("결과: $result9")

    // ==========================================
    println("""
        
        10. distinct / distinctBy (전체 중복 제거)
        상황: 리스트 전체에서 중복된 요소를 싹 다 없애고 하나씩만 남길 때.
        예시: [1, 2, 2, 1, 3] -> [1, 2, 3]
        기존 방식: Set을 만들어서 add하거나, contain 체크하며 새 리스트 생성.
        함수형 패턴: .distinct() (단순 제거) / .distinctBy { } (특정 기준 제거)
    """.trimIndent())

    val list10 = listOf("A", "B", "A", "C", "B")

    // 1. 단순 전체 중복 제거
    val result10 = list10.distinct()
    println("distinct 결과: $result10")
    // 출력: [A, B, C] (순서는 유지됨)

    // 2. (응용) 특정 기준(key)으로 중복 제거 (distinctBy)
    // 예: 첫 글자가 같은 놈은 중복으로 치겠다.
    val words = listOf("apple", "banana", "avocado", "blueberry")
    val result10_2 = words.distinctBy { it.first() }
    println("distinctBy 결과: $result10_2")
    // 출력: [apple, banana]
    // ('avocado'는 'apple' 때문에 탈락, 'blueberry'는 'banana' 때문에 탈락)
}

//forEach는 break가 안된다. 함수형 프로그래밍에서 어케하지 그럼? all을 써라.
//count all 패턴 words.count { word -> word.all { condition } }
//각 문자가 모두 condtion을 만족하는 단어가 몇 개인지 세어줘.

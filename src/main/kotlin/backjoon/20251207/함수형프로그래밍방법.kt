package backjoon.`20251207`
fun main() {



    print("""
        5. fold (눈덩이 굴리기)
        리스트의 데이터를 하나로 응축(Reduce)시킬 때 씁니다. 합계, 누적 문자열 생성 등에 쓰입니다.
        상황: 리스트 [1, 2, 3]을 가지고 "1->2->3" 문자열을 만들고 싶다.
        기존 방식: StringBuilder 만들고 루프 돌면서 append
        함수형 패턴:
    """.trimIndent())

    val list = listOf(1, 2, 3)
    val result = list.fold("") { acc, el ->
        if (acc.isEmpty()) "$el" else "$acc->$el"
    }
    print(result)

}


//forEach는 break가 안된다. 함수형 프로그래밍에서 어케하지 그럼?
//
//count all 패턴 words.count { word -> word.all { condition } }
//각 문자가 모두 condtion을 만족하는 단어가 몇 개인지 세어줘.

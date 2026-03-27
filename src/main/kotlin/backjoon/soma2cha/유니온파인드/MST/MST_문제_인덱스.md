# MST 문제 인덱스

`소마2차/유니온파인드/MST` 폴더로 프로젝트 내 기존 MST 관련 풀이를 복사해 정리했다.

| 파일명 | 원본 경로 | 추정 문제 | 상태 |
|---|---|---|---|
| `64.cpp` | `old-coding-test/algorithm/ProblemSolving2/MST/64.cpp` | BOJ 1197 최소 스패닝 트리 | 풀이 파일 |
| `64clear.cpp` | `old-coding-test/algorithm/ProblemSolving2/MST/64clear.cpp` | BOJ 1197 최소 스패닝 트리 | 정리 버전 |
| `65.cpp` | `old-coding-test/algorithm/ProblemSolving2/MST/65.cpp` | BOJ 17472 다리 만들기 2 | 풀이 파일 |
| `66.cpp` | `old-coding-test/algorithm/ProblemSolving2/MST/66.cpp` | 미확인 | 미완성 템플릿 |
| `최소_스패닝_트리.cpp` | `old-coding-test/복습요망/최소_스패닝_트리.cpp` | BOJ 1197 최소 스패닝 트리 계열 | 복습용 풀이 |

## Kotlin 변환본 (프로그래머스식)

| Kotlin 파일명 | 대응 원본 | 설명 |
|---|---|---|
| `64.kt` | `64.cpp` | 크루스칼 + 정렬 간선 버전 |
| `64clear.kt` | `64clear.cpp` | 크루스칼 + 우선순위큐 버전 |
| `최소_스패닝_트리.kt` | `최소_스패닝_트리.cpp` | 최소 스패닝 트리 복습 버전 |
| `65.kt` | `65.cpp` | 다리 만들기 2 (BFS 라벨링 + 크루스칼) |

## Kotlin 스키마

| 스키마 파일명 | 대응 코드 |
|---|---|
| `스키마/64_스키마_모델링_mermaid.md` | `64.kt` |
| `스키마/64clear_스키마_모델링_mermaid.md` | `64clear.kt` |
| `스키마/최소_스패닝_트리_스키마_모델링_mermaid.md` | `최소_스패닝_트리.kt` |
| `스키마/65_스키마_모델링_mermaid.md` | `65.kt` |
| `스키마/MST_공통_크루스칼_스키마_모델링_mermaid.md` | 공통 흐름 |

## 정리 기준

- 원본 파일명은 추적 가능성을 위해 그대로 유지
- 원본 위치와 문제 성격을 표로 매핑
- 공통 크루스칼 흐름은 `스키마/MST_공통_크루스칼_스키마_모델링_mermaid.md`에 정리
- Kotlin 변환본은 `class Solution` + `fun solution(...)` 형태로 정리

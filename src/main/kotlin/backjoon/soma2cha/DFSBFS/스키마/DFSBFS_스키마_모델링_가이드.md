# DFSBFS 스키마 모델링 가이드

이 폴더 문서는 시뮬레이션 문서와 동일 정책으로 작성한다.

- `S`: 실행 흐름 상태
- `V`: 핵심 상태 변수
- 머메이드는 `통합 다이어그램 (S+V)` 1개
  - 위: `subgraph STATE`, `direction LR`
  - 아래: `subgraph FLOW`, `direction TB`
- 각 `S` 노드 설명 끝에 `: V...`로 영향 상태를 표시
- 다이어그램 아래에 `V 갱신 규칙 (S 단계 기준)` 작성

const fs = require("fs");
const path = require("path");

const REPO_ROOT = process.cwd();
const SOURCE_ROOT = path.join(REPO_ROOT, "src/main/kotlin/backjoon");
const OUTPUT_ROOT = path.join(SOURCE_ROOT, "치트시트/실전문제모음");
const AUTOCP_PATH = path.join(REPO_ROOT, ".autocp");
const DATE_DIR_RE = /^(2025|2026)\d{4}$/;

const CATEGORY_ORDER = [
  "출력",
  "DFS와BFS",
  "슬라이딩윈도우",
  "투포인터",
  "DP",
  "그래프",
  "greedy",
  "정렬",
  "dataStructure",
  "문자열",
  "수학",
  "시뮬레이션",
  "브루트포스",
  "재귀분할정복",
  "구현",
  "미분류",
];

const EXCLUDED_BASE_NAMES = new Set([
  "hello",
  "test",
  "연습",
  "fold",
  "replace",
  "성능",
  "함수형프로그래밍방법",
  "DFS-BFS-로그",
  "bfs",
  "dfs",
  "dfsbfs",
  "dFS와BFS_BFS",
  "atcode1",
]);

const PRIMARY_RULES = [
  { category: "출력", patterns: [/별찍기/, /^A더하기B/, /^AB/, /^_AB$/, /SciComLove/, /등록/, /오늘-날짜/, /정보보호학부/, /^UCPC/, /박사-과정/, /행렬덧셈/, /큰수/, /이별은무슨색일까/] },
  { category: "슬라이딩윈도우", patterns: [/회전초밥/, /윈도우/, /꿀아르바이트/] },
  { category: "투포인터", patterns: [/같이 눈사람 만들래/, /연속된수의합/, /수열의점수/, /볼모으기/] },
  { category: "DFS와BFS", patterns: [/dfs/i, /bfs/i, /단지번호붙이기/, /섬의_개수/, /섬의개수/, /유기농_배추/, /숨바꼭질/, /트리의_부모_찾기/, /바이러스/, /그림/, /항체인식/] },
  { category: "그래프", patterns: [/다익스트라/, /최단경로/, /백도어/, /유니온파인드/, /집합의_표현/, /벨만포드/, /플루이드/] },
  { category: "DP", patterns: [/피보나치/, /타일/, /돌게임/, /파스칼/, /01타일/, /신나는함수실행/, /캡틴이다솜/, /레벨햄버거/, /점화식/, /^1_2_3_$/] },
  { category: "재귀분할정복", patterns: [/칸토어집합/, /병합정렬/] },
  { category: "greedy", patterns: [/ATM/, /동전0/, /거스름돈/, /저금/, /잃어버린괄호/, /회의실배정/, /신입사원/, /내일할거야/, /스네이크버드/, /설탕/] },
  { category: "정렬", patterns: [/정렬/, /나이순정렬/, /좌표_정렬하기/, /파일정리/, /생태학/, /오름차/] },
  { category: "dataStructure", patterns: [/수찾기/, /숫자카드2/, /^CD$/, /회사에있는사람/, /임스와함께하는미니게임/, /포스택/, /표편집/, /과제는끝나지않아/, /싸이버개강총회/] },
  { category: "문자열", patterns: [/메세지/, /그룹_단어_체커/, /크로아티아_알파벳/, /비밀번호발음하기/, /무한문자열/, /놀라운문자열/, /NEMODEMIC/, /크로스워드_만들기/, /A___illegal/, /세준세비/] },
  { category: "수학", patterns: [/소인수/, /최대공약수/, /분수/, /헨리/, /민겸수/, /시그마/, /오버플로우/, /^24$/, /2진수8진수/, /3의배수/, /날짜계산/, /조약돌꺼내기/, /회문인수/, /금민수/, /로마숫자/, /천재수학자성필/, /행운의편지/] },
  { category: "시뮬레이션", patterns: [/색종이/, /거북이/, /주차장/, /칠무해/, /몬스터트럭/, /디지털시계/, /파이어볼/, /오목/, /지뢰찾기/, /디지털티비/, /이번학기 평점은 몇점/, /두스티커/, /^D_Day$/, /안전지대/, /평행/, /운동장/, /초콜릿/, /셔틀/, /탕수육/, /롤케이크/, /맞혀보세요/, /직사각형을만드는방법/, /에너지모으기/, /에르다노바와오리진스킬/] },
  { category: "브루트포스", patterns: [/덩치/, /영화감독_숌/, /사각형_면적/, /금민수의개수/, /두스티커/, /오목/] },
];

const TAG_RULES = [
  { category: "DFS와BFS", name: [/dfs/i, /bfs/i, /단지번호붙이기/, /섬의_개수/, /섬의개수/, /유기농_배추/, /숨바꼭질/, /트리의_부모_찾기/, /바이러스/, /그림/, /항체인식/], content: [/ArrayDeque/, /visited/, /dx\s*=/, /dy\s*=/, /Queue/, /큐/] },
  { category: "DP", name: [/피보나치/, /타일/, /돌게임/, /파스칼/, /01타일/, /점화식/, /레벨햄버거/, /캡틴이다솜/, /신나는함수실행/], content: [/\bdp\b/i, /memo/i, /점화/, /메모이/] },
  { category: "그래프", name: [/다익스트라/, /최단경로/, /백도어/, /유니온파인드/, /집합의_표현/, /벨만포드/, /플루이드/], content: [/PriorityQueue/, /graph/, /간선/, /정점/, /인접/] },
  { category: "greedy", name: [/ATM/, /동전0/, /거스름돈/, /저금/, /잃어버린괄호/, /회의실배정/, /신입사원/, /내일할거야/, /스네이크버드/, /설탕/, /볼모으기/], content: [/sort/, /sorted/, /compareBy/, /그리디/] },
  { category: "정렬", name: [/정렬/, /나이순정렬/, /좌표_정렬하기/, /생태학/, /파일정리/, /오름차/], content: [/sort/, /sorted/, /compareBy/, /thenBy/] },
  { category: "dataStructure", name: [/수찾기/, /숫자카드2/, /^CD$/, /회사에있는사람/, /임스와함께하는미니게임/, /포스택/, /표편집/, /과제는끝나지않아/, /싸이버개강총회/, /추월/, /비슷한단어/], content: [/HashMap/, /HashSet/, /MutableMap/, /MutableSet/, /Stack/, /ArrayDeque/, /PriorityQueue/] },
  { category: "문자열", name: [/메세지/, /그룹_단어_체커/, /크로아티아_알파벳/, /비밀번호발음하기/, /무한문자열/, /놀라운문자열/, /NEMODEMIC/, /크로스워드_만들기/, /A___illegal/, /세준세비/] },
  { category: "수학", name: [/소인수/, /최대공약수/, /분수/, /헨리/, /민겸수/, /시그마/, /오버플로우/, /^24$/, /2진수8진수/, /3의배수/, /날짜계산/, /조약돌꺼내기/, /회문인수/, /금민수/, /행운의편지/, /천재수학자성필/], content: [/BigInteger/, /gcd/i, /lcm/i, /소수/, /%/, /mod/] },
  { category: "시뮬레이션", name: [/색종이/, /거북이/, /주차장/, /칠무해/, /몬스터트럭/, /디지털시계/, /파이어볼/, /오목/, /지뢰찾기/, /디지털티비/, /이번학기 평점은 몇점/, /두스티커/, /^D_Day$/, /안전지대/, /평행/, /운동장/, /초콜릿/, /셔틀/, /탕수육/, /롤케이크/, /맞혀보세요/, /직사각형을만드는방법/, /에너지모으기/, /에르다노바와오리진스킬/], content: [/dx\s*=/, /dy\s*=/, /repeat\(/, /when\s*\(/] },
  { category: "투포인터", name: [/같이 눈사람 만들래/, /연속된수의합/, /수열의점수/, /볼모으기/], content: [/left/, /right/, /while\s*\(\s*left\s*</] },
  { category: "슬라이딩윈도우", name: [/회전초밥/, /윈도우/, /꿀아르바이트/], content: [/windowed/, /sliding/i, /left/, /right/] },
  { category: "브루트포스", name: [/덩치/, /영화감독_숌/, /사각형_면적/, /금민수의개수/, /두스티커/, /오목/], content: [/for\s*\(.*for\s*\(/s, /브루트/] },
  { category: "재귀분할정복", name: [/칸토어집합/, /병합정렬/, /레벨햄버거/] },
  { category: "출력", name: [/별찍기/, /^A더하기B/, /^AB/, /^_AB$/, /SciComLove/, /등록/, /오늘-날짜/, /정보보호학부/, /^UCPC/, /박사-과정/, /행렬덧셈/, /큰수/, /이별은무슨색일까/] },
];

function loadAutocp() {
  if (!fs.existsSync(AUTOCP_PATH)) {
    return {};
  }
  const raw = JSON.parse(fs.readFileSync(AUTOCP_PATH, "utf8"));
  return raw.solutionFiles || {};
}

function ensureDir(dirPath) {
  fs.mkdirSync(dirPath, { recursive: true });
}

function sanitizeStem(name) {
  return name
    .normalize("NFC")
    .replace(/[\\/:*?"<>|]/g, "_")
    .replace(/\s+/g, "_");
}

function listCandidates() {
  const files = [];

  for (const dirName of fs.readdirSync(SOURCE_ROOT)) {
    if (!DATE_DIR_RE.test(dirName)) {
      continue;
    }

    const absDir = path.join(SOURCE_ROOT, dirName);
    for (const fileName of fs.readdirSync(absDir)) {
      if (!fileName.endsWith(".kt")) {
        continue;
      }
      files.push(path.join(absDir, fileName));
    }
  }

  return files.sort();
}

function shouldExclude(filePath, metadata, content) {
  const baseName = path.basename(filePath, ".kt");
  const fileName = path.basename(filePath);

  if (fileName === ".kt" || /^[._]+$/.test(baseName)) {
    return true;
  }

  if (/^_\d+$/.test(baseName) && !metadata?.linkedProblemId) {
    return true;
  }

  if (EXCLUDED_BASE_NAMES.has(baseName) && !metadata?.linkedProblemId) {
    return true;
  }

  if (!content.includes("fun main") && !content.includes("class Solution")) {
    return true;
  }

  return false;
}

function addMatchedTags(target, rules, haystack) {
  for (const rule of rules) {
    const nameMatched = rule.name?.some((pattern) => pattern.test(haystack.baseName)) || false;
    const contentMatched = rule.content?.some((pattern) => pattern.test(haystack.content)) || false;
    if (nameMatched || contentMatched) {
      target.add(rule.category);
    }
  }
}

function detectPrimaryCategory(baseName, tags) {
  for (const rule of PRIMARY_RULES) {
    if (rule.patterns.some((pattern) => pattern.test(baseName))) {
      return rule.category;
    }
  }

  for (const category of CATEGORY_ORDER) {
    if (tags.has(category)) {
      return category;
    }
  }

  return "구현";
}

function detectTags(baseName, content) {
  const tags = new Set();
  addMatchedTags(tags, TAG_RULES, { baseName, content });

  if (tags.size === 0) {
    tags.add("구현");
  }

  return tags;
}

function buildProblemTitle(filePath, metadata) {
  const linked = metadata?.linkedProblemId;
  if (linked?.second) {
    return linked.second;
  }
  return path.basename(filePath, ".kt");
}

function buildKotlinCopy(entry) {
  const lines = entry.content.split(/\r?\n/);
  const hasPackage = lines.length > 0 && lines[0].trim().startsWith("package ");
  const bodyLines = hasPackage ? lines.slice(1) : lines.slice();

  while (bodyLines.length > 0 && bodyLines[0].trim() === "") {
    bodyLines.shift();
  }

  const packageLine = `package backjoon.치트시트.실전문제모음.${entry.primaryCategory}`;
  const header = `// 알고리즘 분류: ${entry.primaryCategory}`;
  const body = bodyLines.join("\n").replace(/\n+$/, "");

  return `${packageLine}\n\n${header}\n\n${body}\n`;
}

function main() {
  const autocp = loadAutocp();
  const candidates = listCandidates();
  const categoryMap = new Map(CATEGORY_ORDER.map((category) => [category, []]));
  const excluded = [];

  fs.rmSync(OUTPUT_ROOT, { recursive: true, force: true });
  ensureDir(OUTPUT_ROOT);

  for (const absPath of candidates) {
    const relativeSourcePath = path.relative(REPO_ROOT, absPath).replace(/\\/g, "/");
    const metadata = autocp[relativeSourcePath];
    const content = fs.readFileSync(absPath, "utf8");

    if (shouldExclude(absPath, metadata, content)) {
      excluded.push({
        relativeSourcePath,
        reason: "스크래치/보조 파일로 판단",
      });
      continue;
    }

    const baseName = path.basename(absPath, ".kt");
    const tags = Array.from(detectTags(baseName, content)).sort((a, b) => CATEGORY_ORDER.indexOf(a) - CATEGORY_ORDER.indexOf(b));
    const primaryCategory = detectPrimaryCategory(baseName, new Set(tags));
    const dateDir = path.basename(path.dirname(absPath));
    const problemTitle = buildProblemTitle(absPath, metadata);
    const safeName = sanitizeStem(baseName);
    const categoryDir = path.join(OUTPUT_ROOT, primaryCategory);
    ensureDir(categoryDir);

    const outputPath = path.join(categoryDir, `${dateDir}__${safeName}.kt`);
    const entry = {
      absPath,
      outputPath,
      relativeSourcePath,
      metadata,
      content,
      baseName,
      tags,
      dateDir,
      primaryCategory,
      problemTitle,
    };

    fs.writeFileSync(outputPath, buildKotlinCopy(entry), "utf8");
    categoryMap.get(primaryCategory).push(entry);
  }

  const summary = CATEGORY_ORDER
    .map((category) => `${category}:${(categoryMap.get(category) || []).length}`)
    .join(", ");

  console.log(`generated -> ${path.relative(REPO_ROOT, OUTPUT_ROOT)}`);
  console.log(`included -> ${Array.from(categoryMap.values()).reduce((sum, entries) => sum + entries.length, 0)}`);
  console.log(`excluded -> ${excluded.length}`);
  console.log(summary);
}

main();

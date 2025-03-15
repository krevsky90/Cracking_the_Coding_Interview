package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 126. Word Ladder II (hard)
 * https://leetcode.com/problems/word-ladder-ii
 * <p>
 * #Company (15.03.2025): 0 - 3 months Meta 4 Amazon 3 Bloomberg 3 0 - 6 months Google 4 Microsoft 2 Lyft 2 6 months ago TikTok 3 LinkedIn 2 Uber 2 Yelp 2 Box 2
 * <p>
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord,
 * or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 * <p>
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * Constraints:
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * The sum of all shortest transformation sequences does not exceed 10^5.
 */
public class WordLadder2 {
    public static void main(String[] args) {
        WordLadder2 obj = new WordLadder2();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
//        String beginWord = "aaaaa";
//        String endWord = "ggggg";
//        List<String> wordList  = List.of("aaaaa","caaaa","cbaaa","daaaa","dbaaa","eaaaa","ebaaa","faaaa","fbaaa","gaaaa","gbaaa","haaaa","hbaaa","iaaaa","ibaaa","jaaaa","jbaaa","kaaaa","kbaaa","laaaa","lbaaa","maaaa","mbaaa","naaaa","nbaaa","oaaaa","obaaa","paaaa","pbaaa","bbaaa","bbcaa","bbcba","bbdaa","bbdba","bbeaa","bbeba","bbfaa","bbfba","bbgaa","bbgba","bbhaa","bbhba","bbiaa","bbiba","bbjaa","bbjba","bbkaa","bbkba","bblaa","bblba","bbmaa","bbmba","bbnaa","bbnba","bboaa","bboba","bbpaa","bbpba","bbbba","abbba","acbba","dbbba","dcbba","ebbba","ecbba","fbbba","fcbba","gbbba","gcbba","hbbba","hcbba","ibbba","icbba","jbbba","jcbba","kbbba","kcbba","lbbba","lcbba","mbbba","mcbba","nbbba","ncbba","obbba","ocbba","pbbba","pcbba","ccbba","ccaba","ccaca","ccdba","ccdca","cceba","cceca","ccfba","ccfca","ccgba","ccgca","cchba","cchca","cciba","ccica","ccjba","ccjca","cckba","cckca","cclba","cclca","ccmba","ccmca","ccnba","ccnca","ccoba","ccoca","ccpba","ccpca","cccca","accca","adcca","bccca","bdcca","eccca","edcca","fccca","fdcca","gccca","gdcca","hccca","hdcca","iccca","idcca","jccca","jdcca","kccca","kdcca","lccca","ldcca","mccca","mdcca","nccca","ndcca","occca","odcca","pccca","pdcca","ddcca","ddaca","ddada","ddbca","ddbda","ddeca","ddeda","ddfca","ddfda","ddgca","ddgda","ddhca","ddhda","ddica","ddida","ddjca","ddjda","ddkca","ddkda","ddlca","ddlda","ddmca","ddmda","ddnca","ddnda","ddoca","ddoda","ddpca","ddpda","dddda","addda","aedda","bddda","bedda","cddda","cedda","fddda","fedda","gddda","gedda","hddda","hedda","iddda","iedda","jddda","jedda","kddda","kedda","lddda","ledda","mddda","medda","nddda","nedda","oddda","oedda","pddda","pedda","eedda","eeada","eeaea","eebda","eebea","eecda","eecea","eefda","eefea","eegda","eegea","eehda","eehea","eeida","eeiea","eejda","eejea","eekda","eekea","eelda","eelea","eemda","eemea","eenda","eenea","eeoda","eeoea","eepda","eepea","eeeea","ggggg","agggg","ahggg","bgggg","bhggg","cgggg","chggg","dgggg","dhggg","egggg","ehggg","fgggg","fhggg","igggg","ihggg","jgggg","jhggg","kgggg","khggg","lgggg","lhggg","mgggg","mhggg","ngggg","nhggg","ogggg","ohggg","pgggg","phggg","hhggg","hhagg","hhahg","hhbgg","hhbhg","hhcgg","hhchg","hhdgg","hhdhg","hhegg","hhehg","hhfgg","hhfhg","hhigg","hhihg","hhjgg","hhjhg","hhkgg","hhkhg","hhlgg","hhlhg","hhmgg","hhmhg","hhngg","hhnhg","hhogg","hhohg","hhpgg","hhphg","hhhhg","ahhhg","aihhg","bhhhg","bihhg","chhhg","cihhg","dhhhg","dihhg","ehhhg","eihhg","fhhhg","fihhg","ghhhg","gihhg","jhhhg","jihhg","khhhg","kihhg","lhhhg","lihhg","mhhhg","mihhg","nhhhg","nihhg","ohhhg","oihhg","phhhg","pihhg","iihhg","iiahg","iiaig","iibhg","iibig","iichg","iicig","iidhg","iidig","iiehg","iieig","iifhg","iifig","iighg","iigig","iijhg","iijig","iikhg","iikig","iilhg","iilig","iimhg","iimig","iinhg","iinig","iiohg","iioig","iiphg","iipig","iiiig","aiiig","ajiig","biiig","bjiig","ciiig","cjiig","diiig","djiig","eiiig","ejiig","fiiig","fjiig","giiig","gjiig","hiiig","hjiig","kiiig","kjiig","liiig","ljiig","miiig","mjiig","niiig","njiig","oiiig","ojiig","piiig","pjiig","jjiig","jjaig","jjajg","jjbig","jjbjg","jjcig","jjcjg","jjdig","jjdjg","jjeig","jjejg","jjfig","jjfjg","jjgig","jjgjg","jjhig","jjhjg","jjkig","jjkjg","jjlig","jjljg","jjmig","jjmjg","jjnig","jjnjg","jjoig","jjojg","jjpig","jjpjg","jjjjg","ajjjg","akjjg","bjjjg","bkjjg","cjjjg","ckjjg","djjjg","dkjjg","ejjjg","ekjjg","fjjjg","fkjjg","gjjjg","gkjjg","hjjjg","hkjjg","ijjjg","ikjjg","ljjjg","lkjjg","mjjjg","mkjjg","njjjg","nkjjg","ojjjg","okjjg","pjjjg","pkjjg","kkjjg","kkajg","kkakg","kkbjg","kkbkg","kkcjg","kkckg","kkdjg","kkdkg","kkejg","kkekg","kkfjg","kkfkg","kkgjg","kkgkg","kkhjg","kkhkg","kkijg","kkikg","kkljg","kklkg","kkmjg","kkmkg","kknjg","kknkg","kkojg","kkokg","kkpjg","kkpkg","kkkkg","ggggx","gggxx","ggxxx","gxxxx","xxxxx","xxxxy","xxxyy","xxyyy","xyyyy","yyyyy","yyyyw","yyyww","yywww","ywwww","wwwww","wwvww","wvvww","vvvww","vvvwz","avvwz","aavwz","aaawz","aaaaz");
        List<List<String>> result = obj.findLadders(beginWord, endWord, wordList);
        System.out.println(result);
    }

    /**
     * NOT SOLVED by myself:
     * info: https://www.youtube.com/watch?v=AD4SFl7tu7I
     * <p>
     * but I built map of BFS levels
     * <p>
     * time to implement (and got TLE) ~ 57 mins
     * time to solve finally ~ 4h
     * <p>
     * THE MAIN idea:
     * build map starting from beginWord, BUT call dfs starting from endWord!!! (otherwise you will get TLE!)
     * <p>
     * BEATS ~ 90%
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //edge case: if endWord is in wordList
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return new ArrayList<>();

        //step 1: build map of levels
        //start from the beginWord!
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        wordSet.remove(beginWord);

        Map<Integer, Set<String>> map = new HashMap<>();
        int level = 0;
        boolean isEndWordFound = false;
        long startTime = System.currentTimeMillis();
        while (!q.isEmpty()) {
            map.put(level, new HashSet<>());

            //proceed level by level
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                map.get(level).add(w);
                if (endWord.equals(w)) {
                    isEndWordFound = true;
                    break;  //there is no sense to check other words on the same level, since non of them will lead to shortest path to beginWord
                }
                List<String> adjWords = findAdjacentWords1(w, wordSet);
                q.addAll(adjWords);
                wordSet.removeAll(adjWords);
            }

            if (isEndWordFound) {
                //if true - we have found the level that contains beginWord => there is no sense to build map further (and fill higher levels)
                break;
            }

            level++;
        }

        //step 2: use DFS, starting from endWord. NOTE: if we start from beginWord, not all ways will reach endWord
        // => we will consider useless paths which is not optimal
        List<List<String>> result = new ArrayList<>();
        LinkedList<String> tempPath = new LinkedList<>(); //linkedList - to add new word to the beginning and have O(1)
        tempPath.add(endWord);

        //level is maxLevel where endWord lives
        dfs(beginWord, map, level, tempPath, result);

        return result;
    }

    private void dfs(String beginWord, Map<Integer, Set<String>> map, int level, LinkedList<String> tempPath, List<List<String>> result) {
        String firstWord = tempPath.getFirst();
        if (beginWord.equals(firstWord)) {
            result.add(new ArrayList<>(tempPath));
            return;
        }

        //find fit adj words on the prev level
        Set<String> neighbourCandidates = map.getOrDefault(level - 1, new HashSet<>());
        List<String> adjWords = findAdjacentWords1(firstWord, neighbourCandidates);
        for (String candidate : adjWords) {
            //backtracking part!
            tempPath.addFirst(candidate);
            dfs(beginWord, map, level - 1, tempPath, result);
            tempPath.removeFirst();
        }
    }

    //finally gives time ~ 85%, space ~ 30-50%
    private List<String> findAdjacentWords2(String word, Set<String> words) {
        StringBuilder sb = new StringBuilder(word);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char prev = sb.charAt(i);
            for (int j = 0; j < 26; j++) {
                sb.setCharAt(i, (char) ('a' + j));
                if (words.contains(sb.toString())) {
                    result.add(sb.toString());
                }
            }
            sb.setCharAt(i, prev);
        }
        return result;
    }

    //time ~ O(words.length * w1.length())
    //finally gives time ~ 91%, space ~ 92%
    private List<String> findAdjacentWords1(String word, Set<String> words) {
        List<String> result = new ArrayList<>();
        for (String tempWord : words) {
            if (isDifferenceOne(word, tempWord)) {
                result.add(tempWord);
            }
        }
        return result;
    }

    private boolean isDifferenceOne(String w1, String w2) {
        if (w1.length() != w2.length()) return false;
        int diffCount = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) return false;
            }
        }

        return diffCount == 1;
    }

    //////////////////////////////////////////////////////
    /**
     * tried to solve the problem in naive way:
     * to build adjMap for all words:
     * 1) it is not simple! you should not mark node as visited until all nods of this level are processed!
     * 2) if you use DFS after that - it will create ALL possible ways, but not the shortest => you will have to cut them,
     *  keeping current min length variable or smth like this (did not implement it finally)
     */
    public List<List<String>> findLaddersByAdjMapForWords(String beginWord, String endWord, List<String> wordList) {
        //edge case: if endWord is in wordList
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return new ArrayList<>();

        //start from the beginWord!
        Map<String, Set<String>> map = new HashMap<>();
        map.putIfAbsent(beginWord, new HashSet<>());

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        Set<String> visited = new HashSet<>();
        Set<String> toBeMarked = new HashSet<>();

        boolean isEndWordFound = false;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                if (endWord.equals(w)) {
                    isEndWordFound = true;
                    break;  //there is no sense to check other words on the same level, since none of them will lead to the shortest path to beginWord
                }

                for (String tempWord : wordSet) {
                    if (!visited.contains(tempWord) && isDifferenceOne(w, tempWord)) {
                        map.putIfAbsent(tempWord, new HashSet<>());
                        map.get(w).add(tempWord);
                        map.get(tempWord).add(w);
                        //do not add duplicates to queue!
                        if (!q.contains(tempWord)) {
                            q.add(tempWord);
                        }

                    }
                }

                toBeMarked.add(w);
            }

            visited.addAll(toBeMarked);

            if (isEndWordFound) break;
        }

        //step 2: use DFS, starting from endWord and using map. NOTE: if we start from beginWord, not all the ways will reach endWord
        // => we will consider useless paths which is not optimal
        List<List<String>> result = new ArrayList<>();
        System.out.println(map);
        LinkedList<String> tempPath = new LinkedList<>(); //linkedList - to add new word to the beginning and have O(1)
        tempPath.add(endWord);

        //reset visited: now it stores words that are already part of tempPath
        visited = new HashSet<>();

        dfs(beginWord, map, tempPath, visited, result);

        return result;
    }

    //returns ALL possible ways, not the shortest!
    private void dfs(String beginWord, Map<String, Set<String>> map, LinkedList<String> tempPath, Set<String> visited, List<List<String>> result) {
        String firstWord = tempPath.getFirst();

        if (beginWord.equals(firstWord)) {
            result.add(new ArrayList<>(tempPath));
            return;
        }

        if (visited.contains(firstWord)) {
            //we have cycle => return, do nothing
            return;
        }

        visited.add(firstWord);

        Set<String> adjWords = map.getOrDefault(firstWord, new HashSet<>());
        for (String candidate : adjWords) {
            //backtracking 1
            tempPath.addFirst(candidate);
            dfs(beginWord, map, tempPath, visited, result);
            tempPath.removeFirst();
        }

        //backtracking 2
        visited.remove(firstWord);
    }
}

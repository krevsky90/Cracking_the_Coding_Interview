package solving_techniques.p21_Trie;

import java.util.*;

/**
 * 588. Design In-Memory File System (hard)
 * https://leetcode.com/problems/design-in-memory-file-system/
 * <p>
 * #Company: 0 - 3 months Amazon 4 Uber 4 0 - 6 months TikTok 4 Microsoft 2 Shopify 2 Baidu 2 6 months ago Snowflake 17 Google 7 Coupang 7 Coinbase 6 DoorDash 4 Apple 3 Oracle 3 Salesforce 3
 * <p>
 * Design a data structure that simulates an in-memory file system.
 * <p>
 * Implement the FileSystem class:
 * <p>
 * FileSystem() Initializes the object of the system.
 * List<String> ls(String path)
 * If path is a file path, returns a list that only contains this file's name.
 * If path is a directory path, returns the list of file and directory names in this directory.
 * The answer should in lexicographic order.
 * void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
 * void addContentToFile(String filePath, String content)
 * If filePath does not exist, creates that file containing given content.
 * If filePath already exists, appends the given content to original content.
 * String readContentFromFile(String filePath) Returns the content in the file at filePath.
 * <p>
 * Example 1:
 * Input
 * ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 * [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 * Output
 * [null, [], null, null, ["a"], "hello"]
 * <p>
 * Explanation
 * FileSystem fileSystem = new FileSystem();
 * fileSystem.ls("/");                         // return []
 * fileSystem.mkdir("/a/b/c");
 * fileSystem.addContentToFile("/a/b/c/d", "hello");
 * fileSystem.ls("/");                         // return ["a"]
 * fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 * <p>
 * Constraints:
 * 1 <= path.length, filePath.length <= 100
 * path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
 * You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
 * You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
 * 1 <= content.length <= 50
 * At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */
public class DesignInMemoryFileSystem {
    public static void main(String[] args) {
        DesignInMemoryFileSystem fileSystem = new DesignInMemoryFileSystem();

        String[] dd = "/a/b".split("/");

        fileSystem.ls("/");                         // return []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fileSystem.ls("/"));                         // return ["a"]
        fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use Trie to store dirs/files
     * Node:
     * children map: dir_file_name -> Node
     * 2) use map: filePath -> fileContent
     * <p>
     * time to solve ~ 1.5h
     * <p>
     * time ~
     * space ~
     * <p>
     * many attempts:
     * - "/".split("/") = []
     * - "".split("/") = [""] => do NOT remove "/" in the beginning of path if path = "/"
     * - incorrectly compared path != "/". need to use equals
     * - forgot to sort the collection in ls method
     * <p>
     * BEATS ~ 60%
     */
    private Map<String, String> filepathToContent;
    private Trie trie;

    public DesignInMemoryFileSystem() {
        filepathToContent = new HashMap<>();
        trie = new Trie();
    }

    public List<String> ls(String path) {
        if (filepathToContent.containsKey(path)) {
            //if it is file
            int lastIndexOfSlash = path.lastIndexOf("/");
            return Arrays.asList(path.substring(lastIndexOfSlash + 1));
        } else {
            Node cur = trie.root;
            // if path = "/" => we will not split it, just skip since
            // "/".split("/") gives empty array, "/a/b" gives ["", "a", "b"]
            if (!"/".equals(path)) {
                path = path.substring(1);
                String[] elements = path.split("/");
                for (String el : elements) {
                    cur = cur.children.get(el);
                }
            }

            List<String> result = new ArrayList<>();
            result.addAll(cur.children.keySet());
            Collections.sort(result, (a, b) -> a.compareTo(b));
            return result;
        }
    }

    public void mkdir(String path) {
        trie.addPath(path);
    }

    public void addContentToFile(String filePath, String content) {
        trie.addPath(filePath);  //will create dirs if not exist
        filepathToContent.put(filePath, filepathToContent.getOrDefault(filePath, "") + content);
    }

    public String readContentFromFile(String filePath) {
        return filepathToContent.getOrDefault(filePath, "");
    }

    class Trie {
        Node root = new Node();

        public void addPath(String path) {
            String[] elements = path.substring(1).split("/");   //to exclude leading /
            Node cur = root;
            for (String el : elements) {
                if (!cur.children.containsKey(el)) {
                    cur.children.put(el, new Node());
                }
                cur = cur.children.get(el);
            }
        }
    }

    class Node {
        Map<String, Node> children;   //map element name to Node created with the same name

        Node() {
            this.children = new HashMap<>();
        }
    }

    /**
     * Official solution:
     * idea: Trie
     * where Node = File/Dir with boolean flag and content
     * NOTE: to avoid substring(1), we can avoid 0-th element (which is "") after splitting
     */
    class FileSystem {
        class File {
            boolean isfile = false;
            HashMap<String, File> files = new HashMap<>();
            String content = "";
        }

        File root;

        public FileSystem() {
            root = new File();
        }

        public List<String> ls(String path) {
            File t = root;
            List<String> files = new ArrayList<>();
            if (!path.equals("/")) {
                String[] d = path.split("/");
                for (int i = 1; i < d.length; i++) {
                    t = t.files.get(d[i]);
                }
                if (t.isfile) {
                    files.add(d[d.length - 1]);
                    return files;
                }
            }
            List<String> res_files = new ArrayList<>(t.files.keySet());
            Collections.sort(res_files);
            return res_files;
        }

        public void mkdir(String path) {
            File t = root;
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                if (!t.files.containsKey(d[i])) t.files.put(d[i], new File());
                t = t.files.get(d[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            File t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.files.get(d[i]);
            }
            if (!t.files.containsKey(d[d.length - 1])) t.files.put(d[d.length - 1], new File());
            t = t.files.get(d[d.length - 1]);
            t.isfile = true;
            t.content = t.content + content;
        }

        public String readContentFromFile(String filePath) {
            File t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.files.get(d[i]);
            }
            return t.files.get(d[d.length - 1]).content;
        }
    }
}

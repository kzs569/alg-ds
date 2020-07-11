package DataStructure.Tree;

import java.util.HashMap;

public class TrieTree {

    private Node root;
    public TrieTree(){
        root = new Node();
    }

    public void insert(String words){
        insert(this.root,words);
    }
    //
    private void insert(Node root,String words){
        words = words.toLowerCase();
        char[] chars = words.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if(root.childs[index] != null){
                root.childs[index].prefixNum++;
            }else{
                root.childs[index] = new Node();
                root.childs[index].prefixNum++;
            }
            if(i == chars.length - 1){
                root.childs[index].isLeaf = true;
                root.childs[index].duplicates++;
            }
            root = root.childs[index];
        }
    }

    /**
     * 遍历Trie树，查找所有的words以及出现次数
     * @return HashMap<String, Integer> map
     */
    public HashMap<String,Integer> getAllWords(){
        return preTraversal(this.root, "");
    }

    /**
     * 前序遍历。。。
     * @param root      子树根节点
     * @param prefixs   查询到该节点前所遍历过的前缀
     * @return
     */
    private HashMap<String,Integer> preTraversal(Node root,String prefixs){
        HashMap<String, Integer> map=new HashMap<String, Integer>();

        if(root!=null){

            if(root.isLeaf==true){
                ////当前即为一个单词
                map.put(prefixs, root.duplicates);
            }

            for(int i=0,length=root.childs.length; i<length;i++){
                if(root.childs[i]!=null){
                    char ch=(char) (i+'a');
                    ////递归调用前序遍历
                    String tempStr=prefixs+ch;
                    map.putAll(preTraversal(root.childs[i], tempStr));
                }
            }
        }
        return map;
    }
    /**
     * 判断某字串是否在字典树中
     * @param word
     * @return true if exists ,otherwise  false
     */
    public boolean isExist(String word){
        return search(this.root, word);
    }
    /**
     * 查询某字串是否在字典树中
     * @param word
     * @return true if exists ,otherwise  false
     */
    private boolean search(Node root,String word){
        char[] chs=word.toLowerCase().toCharArray();
        for(int i=0,length=chs.length; i<length;i++){
            int index=chs[i]-'a';
            if(root.childs[index]==null){
                ///如果不存在，则查找失败
                return false;
            }
            root=root.childs[index];
        }
        return true;
    }

    /**
     * 得到以某字串为前缀的字串集，包括字串本身！ 类似单词输入法的联想功能
     * @param prefix 字串前缀
     * @return 字串集以及出现次数，如果不存在则返回null
     */
    public HashMap<String, Integer> getWordsForPrefix(String prefix){
        return getWordsForPrefix(this.root, prefix);
    }
    /**
     * 得到以某字串为前缀的字串集，包括字串本身！
     * @param root
     * @param prefix
     * @return 字串集以及出现次数
     */
    private HashMap<String, Integer> getWordsForPrefix(Node root,String prefix){
        HashMap<String, Integer> map=new HashMap<String, Integer>();
        char[] chrs=prefix.toLowerCase().toCharArray();
        ////
        for(int i=0, length=chrs.length; i<length; i++){

            int index=chrs[i]-'a';
            if(root.childs[index]==null){
                return null;
            }

            root=root.childs[index];

        }
        ///结果包括该前缀本身
        ///此处利用之前的前序搜索方法进行搜索
        return preTraversal(root, prefix);
    }

    private class Node {
        /**
         * @duplicates 字串的重复数目
         * @prifixNum 字串为前缀的字串数
         * @childs
         * @isLeaf 是否为单词节点
         */
        private int duplicates;
        private int prefixNum;
        private Node childs[];
        private boolean isLeaf;

        public Node(int duplicates, int prefixNum, Node[] childs, boolean isLeaf) {
            this.duplicates = duplicates;
            this.prefixNum = prefixNum;
            this.childs = childs;
            this.isLeaf = isLeaf;
        }

        public Node() {
            duplicates = 0;
            prefixNum = 0;
            isLeaf = false;
            childs = new Node[26];
        }
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        trie.insert("I");
        trie.insert("Love");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("xiaoliang");
        trie.insert("xiaoliang");
        trie.insert("man");
        trie.insert("handsome");
        trie.insert("love");
        trie.insert("chinaha");
        trie.insert("her");
        trie.insert("know");

        HashMap<String,Integer> map=trie.getAllWords();

        for(String key:map.keySet()){
            System.out.println(key+" 出现: "+ map.get(key)+"次");
        }


        map=trie.getWordsForPrefix("chin");

        System.out.println("\n\n包含chin（包括本身）前缀的单词及出现次数：");
        for(String key:map.keySet()){
            System.out.println(key+" 出现: "+ map.get(key)+"次");
        }

        if(trie.isExist("xiaoming")==false){
            System.out.println("\n\n字典树中不存在：xiaoming ");
        }
    }
}

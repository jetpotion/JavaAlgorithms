public class Problem103 {

    /* Binary Tree Zigzag Level Order Traversal */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> wrapList = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        int level = 0;
        
        if (root == null) return wrapList;
        q.add(root);
        
        while(!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i ++) {
                TreeNode node = q.poll();
                /* left to right */
                if (level % 2 == 0) {
                    list.add(node.val);
                } else if (level % 2 == 1) {
                    list.add(0, node.val);
                }
                
                
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
            wrapList.add(list);
            level ++;
            
        }
        
        return wrapList; 
    }

}
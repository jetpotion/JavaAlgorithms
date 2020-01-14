public class Problem111 {

    /* Minimum Depth of Binary Tree */
    public int minDepth(TreeNode root) {
        
        if (root == null) return 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        int min = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) return min;
                if (node.left != null) q.offer(node.left);
                if (node.right!= null) q.offer(node.right);
                
            }
            min ++;
            
        }
        return min;
    }

}
public class Problem1161 {
    
    /*  Maximum Level Sum of a Binary Tree */
    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE; 
        int maxLevel = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int level = 1; !q.isEmpty(); level ++) {
            int sum = 0;
            int size = 0;
            for (int i = 0; i < size; i ++) {
                TreeNode n = q.poll();
                sum += n.val;
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
                
            }
            if (max < sum) {
                max = sum;
                maxLevel = level;
            }
        }
        return maxLevel;
    }

}
public class Problem958 {
    /* Check Completeness of a Binary Tree */

    // Idea: The first time you see a NULL is also the last time you see a NULL.
    // Otherwise, the tree binary tree is not complete
    public boolean isCompleteTree(TreeNode root) {
        
        Queue<TreeNode> q = new LinkedList<>();
        List<TreeNode> list = new ArrayList<>();
        q.offer(root);
        
        boolean seenNull = false;
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
                        
            if (n != null) {
                if (seenNull)
                    return false;
                q.offer(n.left);
                q.offer(n.right);   
            } else {
                seenNull = true;
            }

        }
        return true;
        
    }

}
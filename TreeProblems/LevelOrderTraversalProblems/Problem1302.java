public class Problem1302 {

    /* Deepest Leaves Sum */
    public int deepestLeavesSum(TreeNode root) {
        
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return 0;
        q.offer(root);
        int sum = 0;
        while (!q.isEmpty()) {
            sum = 0;
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right!= null) q.offer(node.right);
                level.add(node.val);
            }
        }
        return sum;
    }

    
}
public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null) return res;
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right!= null) q.offer(node.right);
                level.add(node.val);
            }
            res.add(level);
        }
        return res;
    }

}
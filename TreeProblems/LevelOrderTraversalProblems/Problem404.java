public class Problem404 {

    /* 404. Sum of Left Leaves */
    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;

        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        int leftSum = 0;
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode = q.remove();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    leftSum += node.left.val;
                }
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
        return leftSum;
    }
}
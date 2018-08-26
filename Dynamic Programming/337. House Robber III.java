/*https://mnmunknown.gitbooks.io/algorithm-notes/content/620,_dong_tai_gui_hua.html
非常好*/

public class Solution {
    public int rob(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.val;


        int leftLvl = 0, rightLvl = 0;
        int subleftLvl = 0, subrightLvl = 0;

        if(root.left != null){
            leftLvl = rob(root.left);
            subleftLvl = rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null){
            rightLvl = rob(root.right);
            subrightLvl = rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(subleftLvl + subrightLvl + root.val, leftLvl + rightLvl);
    }
}

fun main() {
    val s=Solution()
    val root=TreeNode(1)
    val n1=TreeNode(3)
    val n2=TreeNode(2)
    val n3=TreeNode(7)
    val n4=TreeNode(6)
    val n5=TreeNode(5)



    root.left=n1
    root.right=n2
    n1.left=n3
    n1.right=n4
    n2.right=n5

    val solution= s.minimumOperations(root)

   println( solution)
}


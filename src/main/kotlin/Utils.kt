object Utils {
    fun  minNumOfSwaps(array: IntArray):Int{
        var count=0

        //Created sorted copy
        val tmp=array.copyOf()
        tmp.sort()

        //Create hashmap contains the original array indices
        val hashMap=HashMap<Int,Int>(array.size)
        for (i in array.indices){
            hashMap[array[i]]=i
        }

        for (i in array.indices){
            if (tmp[i]!=array[i]){
             count++
             //The actual index of this number in the unsorted array
             val j:Int =hashMap[tmp[i]]!!

            //Swap in the original array and hashmap
             array[i]=array[j].also { array[j]=array[i] }
                hashMap[array[j]]=j
                hashMap[array[i]]=i
            }
        }
        return count
    }
    fun traverseTreeBFS(root: TreeNode) {
        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)

        while (queue.isNotEmpty()) {
            // Get the size of the current level
            val levelSize = queue.size
            for (i in 0..<levelSize) {
                val currentNode = queue.removeFirst()
                print("${currentNode.`val`} ")

                if (currentNode.left != null) {
                    queue.addLast(currentNode.left!!)
                }

                if (currentNode.right != null) {
                    queue.addLast(currentNode.right!!)
                }
            }
            // Print a newline after processing the current level
            println()
        }
    }

}
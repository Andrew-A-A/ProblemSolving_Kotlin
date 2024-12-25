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
    fun traverseBinaryTreeBFS(root: TreeNode) {
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

//    fun findTreeDiameter(edges:Array<IntArray>):Int{
//        val adj= generateAdjacencyMap(edges)
//        var maxHops=0
//        var farthestNode=-1
//        var isNodeVisited=BooleanArray(edges.size+1)
//        // First BFS to determine the farthest node
//        val queue=ArrayDeque<Int>()
//        queue.addFirst(0)
//        while (queue.isNotEmpty()){
//            val currentNode=queue.removeFirst()
//            if (!isNodeVisited[currentNode]){
//                queue.addAll(adj[currentNode]!!.toList())
//                for (child in adj[currentNode]!!) {
//                    if (!isNodeVisited[child]) {
//                        farthestNode = child
//                     //   break
//                    }
//                }
//            isNodeVisited[currentNode]=true
//            }
//        }
//
//        //Second step to calculate the max hops starting for the farthest node
//        isNodeVisited=BooleanArray(edges.size+1)
//        queue.addFirst(farthestNode)
//        while (queue.isNotEmpty()){
//            val currentNode=queue.removeFirst()
//            if (!isNodeVisited[currentNode]){
//
//
//               // queue.addAll(adj[currentNode]!!.toList())
//                var counted=false
//                for (child in adj[currentNode]!!) {
//                    if (!isNodeVisited[child]) {
//                        //farthestNode = child
//                        queue.addLast(child)
//                        if (!counted){
//                            maxHops++
//                            counted=true
//                        }
//                    }
//                }
//
//                isNodeVisited[currentNode]=true
//            }
//        }
//        return maxHops
//    }
//
//    private fun generateAdjacencyMap(
//        edges: Array<IntArray>,
//    ):HashMap<Int,ArrayList<Int>> {
//        val adj=HashMap<Int,ArrayList<Int>>()
//        for (i in edges.indices ) {
//            val n1 = edges[i][0]
//            val n2 = edges[i][1]
//
////            if (n1 > n2) {
////                n1 = n2.also { n2 = n1 }
////            }
//
//            if (!adj.contains(n1)) {
//                adj[n1] = arrayListOf(n2)
//            }
//            else{
//                adj[n1]!!.add(n2)
//            }
//            if (!adj.contains(n2)) {
//                adj[n2] = arrayListOf(n1)
//            }
//            else{
//                adj[n2]!!.add(n1)
//            }
//
//        }
//        return adj
//    }
fun findTreeDiameter(edges: Array<IntArray>): Int {
    val adj = generateAdjacencyMap(edges)
    // Step 1: First BFS to find the farthest node from any starting node (e.g., node 0)
    val (farthestNode, _) = bfs(0, adj)

    // Step 2: Second BFS starting from the farthest node to calculate the diameter
    val (_, diameter) = bfs(farthestNode, adj)

    return diameter
}

    private fun bfs(start: Int, adj: HashMap<Int, ArrayList<Int>>): Pair<Int, Int> {
        val queue = ArrayDeque<Pair<Int, Int>>() // Pair(node, distance)
        val visited = mutableSetOf<Int>()
        queue.add(Pair(start, 0))

        var farthestNode = start
        var maxDistance = 0

        while (queue.isNotEmpty()) {
            val (currentNode, distance) = queue.removeFirst()
            if (currentNode in visited) continue
            visited.add(currentNode)

            if (distance > maxDistance) {
                maxDistance = distance
                farthestNode = currentNode
            }

            for (neighbor in adj[currentNode] ?: emptyList()) {
                if (neighbor !in visited) {
                    queue.add(Pair(neighbor, distance + 1))
                }
            }
        }

        return Pair(farthestNode, maxDistance)
    }

    private fun generateAdjacencyMap(edges: Array<IntArray>): HashMap<Int, ArrayList<Int>> {
        val adj = HashMap<Int, ArrayList<Int>>()
        for (edge in edges) {
            val n1 = edge[0]
            val n2 = edge[1]
            adj.computeIfAbsent(n1) { arrayListOf() }.add(n2)
            adj.computeIfAbsent(n2) { arrayListOf() }.add(n1)
        }
        return adj
    }


}
fun main() {
    val s=Solution()

    val solution= s.leftmostBuildingQueries(intArrayOf(6,4,8,5,2,7),
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(0,3),
            intArrayOf(2,4),
            intArrayOf(3,4),
            intArrayOf(2,2)
    ))

    println( solution.contentToString());
}


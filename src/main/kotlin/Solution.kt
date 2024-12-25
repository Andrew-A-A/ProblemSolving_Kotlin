import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size - 1
        var mid = end / 2
        if (nums[mid] == target)
            return mid

        while (start < end) {
            if (target > nums[mid]) {
                start = mid + 1
            } else if (target < nums[mid]) {
                end = mid - 1
            } else {
                return mid
            }
            if (end != 0)
                mid = (end + start) / 2
        }
        if (nums[mid] == target)
            return mid
        return mid
    }

    fun isPalindrome(x: Int): Boolean {
        val a = x.toString()
        var i = 0
        var j = a.length - 1
        val s = Stack<Char>()
        while (i <= j) {
            s.push(a[i])
            i++
            if (s.firstElement() == a[j]) {
                s.pop()
                j--
            } else
                return false
        }
        return true
    }

    fun addTwoNumbers(l1: ListNode, l2: ListNode): ListNode {
        var ans: ListNode = ListNode(l1.`val` + l2.`val`)
        var tmp = ans
        var L1 = l1
        var L2 = l2
        while (L1.next != null && l2.next != null) {
            L1 = L1.next!!
            L2 = L2.next!!
            tmp.next = ListNode(l1.`val` + l2.`val`)
            tmp = tmp.next!!
        }

        return ans
    }

    fun PredictTheWinner(nums: IntArray): Boolean {
        var p1 = 0
        var p2 = 0
        var i = 0
        var j = nums.size - 1
        while (i < j) {
            if (nums[i] >= nums[j]) {
                p1 += nums[i]
                i++
            } else {
                p1 += nums[j]
                j--
            }
            if (nums[i] >= nums[j]) {
                p2 += nums[i]
                i++
            } else {
                p2 += nums[j]
                j++
            }
        }
        if (nums.size % 2 != 0) p1 += nums[i]
        return p1 >= p2
    }

    fun strStr(haystack: String, needle: String): Int {

        return haystack.indexOf(needle)
    }

    fun isAnagram(s: String, t: String): Boolean {

        return s.toCharArray().sorted().toString() == t.toCharArray().sorted().toString()
    }

    fun isMonotonic(nums: IntArray): Boolean {
        val x = nums.sorted().toIntArray()
        return nums.contentEquals(x) || nums.contentEquals(x.reversed().toIntArray())
    }

    fun plusOne(digits: IntArray): IntArray {
        var i = digits.size - 1
        while (i >= 0) {
            digits[i]++
            if (digits[i] == 10) {
                digits[i] = 0
                i--
            } else
                return digits
        }
        if (digits[0] == 0) {
            val x = IntArray(digits.size + 1)
            x[0] = 1
            digits.copyInto(x, 1, 0)
            return x
        }
        return digits
    }

    fun lengthOfLastWord(s: String): Int {
        var i = s.lastIndex
        var cnt = 0
        while (s[i] == ' ') i--

        while (s[i] != ' ') {
            cnt++
            i--
            if (i < 0)
                break
        }

        return cnt
    }

    fun lemonadeChange(bills: IntArray): Boolean {
        var bucks_5 = 0
        var bucks_10 = 0

        bills.forEach { bill ->
            when (bill) {
                5 -> {
                    bucks_5++
                }

                10 -> {
                    bucks_10++
                    bucks_5--
                    if (bucks_5 < 0) return false
                }

                20 -> {
                    if (bucks_10 == 0 && bucks_5 >= 3) bucks_5 -= 3
                    else {
                        bucks_10--
                        bucks_5--
                    }
                    if ((bucks_5 < 0 || bucks_10 < 0) && bucks_5 < 3) return false
                }
            }
        }
        return true
    }

    fun calPoints(operations: Array<String>): Int {
        var points = 0
        val ops = IntArray(operations.size)
        var j = 0
        for (i in operations.indices) {

            when (operations[i]) {
                "+" -> {
                    ops[j] = ops[j - 2] + ops[j - 1]
                    points += ops[j]
                    j++
                }

                "D" -> {
                    ops[j] = ops[j - 1] * 2
                    points += ops[j]
                    j++
                }

                "C" -> {
                    points -= ops[j - 1]
                    ops[j - 1] = 0
                    j--
                }

                else -> {
                    ops[j] = operations[i].toInt()
                    points += ops[j]
                    j++
                }

            }
        }
        return points
    }

    fun judgeCircle(moves: String): Boolean {
        var x = 0
        var y = 0
        moves.forEach { move ->
            when (move) {
                'U' -> y++
                'D' -> y--
                'L' -> x--
                'R' -> x++
            }
        }
        return x == 0 && y == 0
    }

    fun multiply(num1: String, num2: String): String {
        return (num1.toBigInteger() * num2.toBigInteger()).toString()
    }

    fun repeatedSubstringPattern(s: String): Boolean {
        if (s.length == 1)
            return false
        if (s.length == 2)
            return s[0] == s[1]

        var s2 = s + s
        s2 = s2.substring(1, s2.length - 2)
        return s2.contains(s)
    }


    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
        val list: ArrayList<Boolean> = ArrayList<Boolean>()
        val max = candies.max()
        for (candy in candies) {
            if (candy + extraCandies >= max) {
                candy + extraCandies
                list.add(true)
            } else {
                list.add(false)
            }
        }
        return list
    }

    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var count = n
        val size = flowerbed.size
        for (current in flowerbed.indices) {
            if (count == 0) break
            val previous = if (current - 1 < 0) 0 else current - 1
            val next = if (current + 1 >= size) size - 1 else current + 1
            if (flowerbed[current] == 0 && flowerbed[previous] == 0
                && flowerbed[next] == 0
            ) {
                count--
                flowerbed[current] = 1
            }
        }
        return count == 0
    }

    fun reverseWords(s: String): String {
        val list = s.split(" ", ignoreCase = true).filter { s: String -> s.isNotEmpty() }
        return list.reversed().toString().removeSurrounding("[", "]").replace(",", "").trim()
    }

    fun largestAltitude(gain: IntArray): Int {
        var current = 0
        var max = current
        for (num in gain) {
            current += num
            if (current > max)
                max = current
        }
        return max
    }

    fun removeStars(s: String): String {
        val x = Stack<Char>()
        for (char in s) {
            if (char == '*')
                x.pop()
            else
                x.push(char)
        }
        return x.toString().removeSurrounding("[", "]").replace(", ", "")
    }

    fun mergeAlternately(word1: String, word2: String): String {
        val len = if (word1.length > word2.length) word1.length else word2.length
        val sb = StringBuilder()
        for (i in 0..<len) {
            if (i < word1.length)
                sb.append(word1[i])
            if (i < word2.length)
                sb.append(word2[i])
        }
        return sb.toString()
    }

    fun gcdOfStrings(str1: String, str2: String): String {
        val minLen = min(str1.length, str2.length)
                if ((str1 + str2) != (str2 + str1)) {
            return ""
        }
        for (i in minLen downTo 1) {
           if (str1.length % i== 0 && str2.length % i ==0){
                return str2.substring(0,i)
            }
        }
        return ""
    }
    fun reverseVowels(s: String): String {
        val sb=StringBuilder(s);
        val vowels= charArrayOf('I','i','a','A','e','E','O','o','u','U')
        var start=0
        var end=s.length-1
        while (start<end){
            val isStartVowel=vowels.contains(s[start])
            val isEndVowel=vowels.contains(s[end])
            if (isStartVowel&&isEndVowel){
                sb.setCharAt(start,s[end])
                sb.setCharAt(end,s[start])
                start++
                end--
            }else{
                if (!isStartVowel){
                    start++
                }
                if (!isEndVowel){
                    end--
                }

            }
        }
        return sb.toString()
    }
    fun productExceptSelf(nums: IntArray): IntArray {
        //Get the product of the whole array
        var arrayProduct=1
        var zeroCont=0

            nums.forEach { num: Int ->
                if (num!=0) {
                    arrayProduct *= num
                }
                else{
                    zeroCont++
                }
        }
        if (zeroCont>=2){
            arrayProduct=0
        }

        val ansArray = IntArray(nums.size)

        if (zeroCont>0){
            for (i in nums.indices){
            if (nums[i]==0){
                ansArray[i]=arrayProduct
             }
            else{
                ansArray[i]=0
                }
            }
        }
        else{
            for (i in nums.indices){
                if (nums[i]==0){
                    ansArray[i]=arrayProduct
                }
                else{
                ansArray[i]=arrayProduct/nums[i]
                }
            }
        }

        return ansArray
    }
    fun increasingTriplet(nums: IntArray): Boolean {
        var p1=Int.MAX_VALUE
        var p2=Int.MAX_VALUE
        nums.forEach { num: Int ->
            if (num<=p1){
                p1=num
            }else if(num<=p2){
                p2=num
            }else{
                return true
            }
        }

        return false
    }
    fun isSubsequence(s: String, t: String): Boolean {
    var counter=0
        if (s.isEmpty()){
            return true
        }

        for (i in t.indices){
            if (t[i]==s[counter]){
                counter++
                if (counter==s.length)
                    return true
                }
            }
        return false
    }
    fun leftmostBuildingQueries(heights: IntArray, queries: Array<IntArray>): IntArray {
        val ans=IntArray(queries.size)
        for(i in queries.indices){
            var x=queries[i][0]
            var y=queries[i][1]
            if(x>y) {
                x = y.also { y = x }
            }

            if (x==y){
                ans[i]=x
            }else if(heights[y]>heights[x]){
                ans[i]=y
            }else{
               for (k in y..<heights.size){
                   if (heights[k]>heights[x]){
                       ans[i]=k
                       break
                   }
               }
                if (ans[i]==0){
                    ans[i]=-1
                }
            }
        }
    return ans
    }
    fun minimumOperations(root: TreeNode?): Int {
        val queue = ArrayDeque<TreeNode>()
        var count=0
        if (root!=null)
            queue.addLast(root)
        else
            return 0

        while (queue.isNotEmpty()) {
            // Get the size of the current level
            val levelSize = queue.size
            val currentArray=IntArray(levelSize)
            for (i in 0..<levelSize) {
                val currentNode = queue.removeFirst()
               // print("${currentNode.`val`} ")
                currentArray[i]=currentNode.`val`
                if (currentNode.left != null) {
                    queue.addLast(currentNode.left!!)
                }

                if (currentNode.right != null) {
                    queue.addLast(currentNode.right!!)
                }
            }
            // Print a newline after processing the current level
            //println()
            count+=Utils.minNumOfSwaps(currentArray)
        }
        return count
      }
    fun minimumDiameterAfterMerge(edges1: Array<IntArray>, edges2: Array<IntArray>): Int {
        //Calculate diameter of each tree
       val d1 = Utils.findTreeDiameter(edges1)
        val d2=Utils.findTreeDiameter(edges2)

        val r1 = if (d1%2==0)
            d1/2
        else{
            d1/2 +1
        }
        val r2 = if (d2%2==0)
            d2/2
        else{
            d2/2 +1
        }


        return max(r1+r2+1, max(d1,d2))
    }

    fun largestValues(root: TreeNode?): List<Int> {
        val maxValuesList=ArrayList<Int>()
        val queue=ArrayDeque<TreeNode>()
        if (root!=null) {
           queue.addLast(root)
          //  maxValuesList.add(root.`val`)
        }
        while (queue.isNotEmpty()){
            val currentRowSize=queue.size
            val currentRow=IntArray(currentRowSize)
            var maxVal=Int.MIN_VALUE
                for (i in 0  ..<currentRowSize){
                    val currentNode=queue.removeFirst()
                    currentRow[i]=currentNode.`val`
                    maxVal = if (currentNode.`val`>maxVal) currentNode.`val` else maxVal
                    if (currentNode.left!=null){
                        queue.addLast(currentNode.left!!)
                    }
                    if (currentNode.right!=null){
                        queue.addLast(currentNode.right!!)
                    }
                }
            maxValuesList.add(maxVal)
        }



        return maxValuesList.toList()
    }
}


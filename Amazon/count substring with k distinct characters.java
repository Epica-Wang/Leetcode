https://www.geeksforgeeks.org/count-number-of-substrings-with-exactly-k-distinct-characters/

Given a string of lowercase alphabets, count all possible substrings (not necessarily distinct) that has exactly k distinct characters.
Examples:

Input: abc, k = 2
Output: 2
Possible substrings are {"ab", "bc"}

Input: aba, k = 2
Output: 3
Possible substrings are {"ab", "ba", "aba"}

Input: aa, k = 1
Output: 3
Possible substrings are {"a", "a", "aa"}

The problem can be solved in O(n*n).
Idea is to maintain a hash table while generating substring and checking the number of unique characters using that hash table.
The implementation below assume that the input string contains only characters from ‘a’ to ‘z’.

int countkDist(String str, int k)
    {
        // Initialize result
        int res = 0;

        int n = str.length();

        // To store count of characters from 'a' to 'z'
        int cnt[] = new int[26];

        // Consider all substrings beginning with
        // str[i]
        for (int i = 0; i < n; i++)
        {
            int dist_count = 0;

            // Initializing count array with 0
            Arrays.fill(cnt, 0);

            // Consider all substrings between str[i..j]
            for (int j=i; j<n; j++)
            {
                // If this is a new character for this
                // substring, increment dist_count.
                if (cnt[str.charAt(j) - 'a'] == 0)
                    dist_count++;

                // Increment count of current character
                cnt[str.charAt(j) - 'a']++;

                // If distinct character count becomes k,
                // then increment result.
                if (dist_count == k)
                    res++;
            }
        }

        return res;
    }

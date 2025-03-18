import re
from collections import defaultdict

def is_valid_string(s):


    # \D* matches zero or more non-digits
    # \d matches the first digit.
    # (?:\D+\d){1,2} matches 1 or 2 occurrences of one or more non-digits followed by a digit
    # \D*$

    pattern = r'^(?=\D*\d(?:\D+\d){1,2}\D*$).{6,}$'
    return bool(re.fullmatch(pattern, s))

#test cases
print(is_valid_string("a1b2c"))   #This is false (less than 6 characters)
print(is_valid_string("a1b2c3"))  #This is true  
print(is_valid_string("abc123"))  #This is false (2+ digits not seperated by non alphanumeric char)
print(is_valid_string("1a2b3c4")) #This is false (more than 3 nums)
print(is_valid_string("a12b3c"))  #This is false (not all nums are separated)

def count_word_frequencies(text):
    #first, convert text to lowercase and extract words using regex
    text_lower = text.lower()
    words = re.findall(r"[a-z']+", text_lower)
    
    #track counts and first occurrence of each word
    counts = defaultdict(int)
    first_occurrence = {}
    for idx, word in enumerate(words):
        counts[word] += 1
        if word not in first_occurrence:
            first_occurrence[word] = idx
    
    #sort by frequency descending
    #then by first occurrence ascending
    sorted_words = sorted(counts.keys(), key=lambda x: (-counts[x], first_occurrence[x]))
    
    #display the results
    print("Word Frequencies:")
    for word in sorted_words:
        print(f"{word}: {counts[word]}")

# Example usage
text = "Hello world! This is a test. Hello, this test is only a test."
count_word_frequencies(text)

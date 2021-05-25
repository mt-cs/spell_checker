# Dictionary and Spell Checker

Author: Marisa Tania 

This project implements a Dictionary ADT with extra functionality of being able to find the closest entries 
in the dictionary to the given invalid word using a compact prefix tree. 


### Program Options
Each portion of the display can be toggled with command line options. Here are the options:
```bash

Options:
    * add             Adds a given word to the dictionary.
    * check           Checks if a given word is in the dictionary.
    * checkPrefix     Checks if the given prefix is a prefix of any word in the dictionary.
    * suggest         If the given word is in not in the dictionary, returns an array of the closest entries in the dictionary.
```

### About Compact Prefix Tree
Instead of a hash table or a binary search tree, this program uses a compact prefix tree: a tree where each node has:
• a string prefix
• up to 26 children (one for each letter of the alphabet),
• a valid bit,

For example, consider the following tree:
<img width="852" alt="compact_prefix" src="https://user-images.githubusercontent.com/60201466/119573015-8032e680-bd68-11eb-90d8-db9a93e4efc2.png">

This dictionary structure has three main advantages:
   - The methods add and check can be performed in constant time (with respect to the number of words in the dictionary -- both these operations are linear in the length of the word being added or checked)
   - Suggest can be done (relatively) easily
   - Determining if a string is a valid prefix of some word can be done in constant time (relative to the size of the dictionary)

### Program Output
printTree output a tree (in a human readable form that uses indentations and preorder traversal) to a file. For example, for a tree that contains: car, carted, carts, doge, doges and doghouse, the string should looks like this (the first line contains the prefix of the root which is an empty string):

```bash
car* 
  t
    ed*
    s* 
dog
  e* 
    s*
  house*

```

```bash
Check 'train', expected true, result: true
Check 'cat', expected false, result: false
Check 'busy', expected true, result: true
Check 'training', expected true, result: true
Check 'cow', expected false, result: false
Check 'bart', expected true, result: true
Check Prefix 'b', expected true, result: true
Check Prefix 'bar', expected true, result: true
Check Prefix 'c', expected false, result: false
Check Prefix 'ar', expected false, result: false

 b
  a
   ll*
   r*
    ometer*
    t*
     s*
  us*
   es*
   iness*
   y*
 train*
  ing*

Successfully wrote to the file.

Suggestion for train: [train]

Suggestion for bald: [ball, bar]

Suggestion for cod: [bus]


 ace*
  s*
 bat*
 c
  at*
   erpillar*
  ow*
 dog*

Suggestion for art: [ace, aces]

Suggestion for cat: [cat]

Suggestion for doggo: [dog, ace, aces, bat, cat]

```


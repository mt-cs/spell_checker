# Project 3: Spell Checker
For this project, you will implement a Dictionary ADT with extra functionality of being able to find the closest entries 
in the dictionary to the given invalid word. You will implement such a dictionary using a compact prefix tree. Please refer to the pdf description of the project for details.

## Suggest Method
The public suggest method calls two recursive helper methods: private suggest and get children.
It takes in String word and number of suggestions as its parameter.

First the public suggest method will check if the word is in the dictionary. If the word passed in is in the dictionary, then it will return an array of length 1 that contains only that word.

If the word is not in the dictionary, then an ArrayList suggestions  will be created.
The public suggest method passed in the suggestions arraylist, currentPrefix so far, the root node, number of suggestions asked when it calls the private suggest helper method.

The private suggest helper method's job is to get to the destinated node and collect its children. The private suggest helper method is a void recursive method with two base cases: First is if the node is null, second is if the ArrayList suggestions size is bigger than the number of suggestions.
The method started with getting the longest common prefix between the node and the word input. Then it will get the suffix word.

The recursion comes early in this method to search down the destinated root by checking if the longest common prefix is equal to the node prefix and the suffix word is empty. This means that we still can go down the root and look for the closest node to the word. During the search going down, we check if the node is a valid word, then we will add it to the suggestions ArrayList. We continue to search down on the index of the suffix word, until we find the node that doesn't match with our search. For example if we are searching for cad and the word in the dictionary is cat, we will stop at node ca.

Once we are at the correct node, we will call the recursive get children method to collect all the children of this node. The get children method takes in the node root, the currentPrefix, arraylist suggestions and the number of suggestions. If the root is null, then it will return. If the suggestions ArrayList size is bigger than the number of suggestions asked then it will return. The recursion happen by searching through all the children on the Node array and adding the word to the arrayList suggestions. If the particular index has children of its own, we will recursively collect the children too. For example, cat might have children cats and catterpilar. Once the number of suggestions are filled, we will return.

The public suggest method will return the ArrayList suggestions as a String array.



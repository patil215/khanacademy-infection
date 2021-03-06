# Infection

This is my solution to the Khan Academy Infection interview problem.

## Running the Code

A JAR file (khanacademy-infection.jar) is part of the repository. The easiest way to run the simulation would be to download this file and run with Java.

The code is an IntelliJ IDEA project, so git-cloning, importing into IntelliJ, and running it should work. No external libraries were used.

## Basic Implementation

My procedure for infection was fairly simple, and was as follows:

* Generating a random set of user/coach trees:
  1. Start with a HashMap of given users which maps a user ID to a user.
  2. Each user has a list of students, a version of Khan Academy that they see, and a coach. The user also has an attribute called "peopleBelow". This is the number of students below them, and their students, etc. It's basically the number of users below them in the tree structure.
  3. Each user starts with no coach and no students.
  4. Coach/student relationships are randomly assigned to each user. Whenever a new user is added to the tree, every coach above that user has their peopleBelow attribute updated. What this means is that now we can easily and efficiently see the number of people in each tree after all the users have been assigned.
* Total infection works as follows:
  1. Randomly pick a user.
  2. Recursively travel up that user's tree until the root user is reached.
  3. Recursively infect all users below the root until the entire tree is infected.
* Limited infection works as follows:
  1. Create a list of all the users that are root users. This effectively creates a list of all the different trees since the tree can be traversed by just looking at the ID of the user's students.
  2. Sort this list by the number of peopleBelow, least to greatest. Now we have a list of trees.
  3. Iterate through the list of trees until one is found that is equal to or lesser than the target number of users to infect.
  4. Infect this tree, and keep infecting trees after it in the list until the target is reached or there are no more trees to infect.

## Visualization
* I made my visualization using the Swing library in Java.
* Each user is represented by a circle, and relationships between users are lines between the circles. A green user is one that does not have a different version, and a blue user is one that has been "infected" with a new version.
* The person running the visualization can adjust the number of users and the number of relationships between them. Clicking "Generate" will create a random graph/tree with randomized users and relationships.
* Clicking "Total Infection" will select a random user, and infect all users connected to him.
* Clicking "Limited Infection" will take whatever value was entered for "Target #" and try to infect as many users as possible.
* Clicking "Reset" will clear the screen.

Here's a screenshot of the simulation run with 100 users, 75 relationships, and a target limited infection of 30:

![Visualization Screenshot](/img/screenshot.png)

## Limitations
* The problem was fairly open ended, so I assumed that each student can have only one coach. If each student has multiple coaches - say one coach across multiple trees, the code can be modified to work across this and still infect the correct people, but the strict coach to student tree heirarchy would no longer be valid.
* The tree structure also does not perfectly represet the possibility where A coaches B, B coaches C, and C coaches A. While all users would still be connected in the same tree and would still be infected with the same version, C would not directly be above A in the tree and the hierarchy wouldn't be perfectly preserved. A graph would work better for this situation.

## TODO
* Currently, limited infection falls apart when there are a ton of users linked to the same tree and there are very few trees. For example, if the number of users is 100, and the number of relationships is 99, then all the users are in a tree. Since limited infection infects entire trees and not parts of them, this means that either all have to be infected or none. This can be solved by modifying the algorithm to backtrack to the last tree if the end of the list is reached and the target hasn't been filled, and then partially infect each level of that tree downwards. Of course, this means that at some level there will be coaches that have a different version from their students.
* Generally, on smaller numbers of users, limited infection tends to be off by a margin much larger than with many users. This is also because limited infection only infects entire trees, as mentioned in the above bullet point.

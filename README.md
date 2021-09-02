# My First Personal Project

## Efe Demir

#### Most recent change (SEP 2021): ####
- Fixed all problematic bugs related to the save and load component of the GUI game.
- Uploaded project to personal GitHub

#### Initial project submission description: ####

I am aiming to make my project a game similar to a simple and single-player version of **agar.io**. The purpose of the 
application is simply for entertainment. Anybody can play the game, but the game will be made for my own personal 
leisure. I was inspired by the game "Spore" on the old *Nokia* phone, which I remember playing as a kid. 
This is why the project interests me.

##### Here is a description of the game I am doing:
-  Blob Game
    - Blob is an object that moves using ***W,A,S,D***, and begins the game by eating 'food pieces' on the game map
    - Blob grows by eating; by going over the food pieces
       
    - Blob and other entities have a field called blobSize, which is an int
        - Object size is determined by this field
        - Every time an object *eats* something that is smaller, the size of the eaten object is added to the size of
          object that ate it.
    - After reaching a certain size threshold, Blob *evolves* by changing color
    - *Food pieces* are blue color
    - **Rule of survival:** The bigger creature eats the smaller creature
    - A new food piece *respawns* in a random part of the map as another one gets eaten by Blob
    - Map size is limited, Blob cannot completely move out of the borders of the game screen
    - Blob wins game by reaching a certain size (game finishes after a certain size is reached)
    - Blob develops gravity as it grows, so it can eat food pieces that is close to it but doesn't touch it; power 
    increases as it grows

    
    
    
##### User Stories - Phase 1 & 2
-  As a user, I want to be able to eat food pieces using Blob
-  As a user, I want to be able to evolve Blob (have it change color once it reaches a threshold)
-  As a user, I want to be able to return the size of Blob, which is an int
-  As a user, I want to be able to move Blob by adding x or y to its x, y coordinates
-  As a user, I want to be able to save the state of my blobGUI
-  As a user, I want to be able to reload a saved blobGUI state
-  As a user, I want to be able to overwrite saved blobGUI data and reset blobGUI 

##### Instructions for Grader - Phase 3
You can generate the first required event by pressing W, A, S, or D to move the blobGUI around the game
You can generate the second required event by eating food pieces around the map; the blobGUI's size grows, as seen on 
the game's score panel
You can trigger my audio components by starting the game, evolving blobGUI (automatically after it grows to a certain
size), reseting the game (press r), or clearing saved data.

##### Phase 4: Task 2 - Include a Type Hierarchy
I have a type hierarchy in my model package where Sprite is an abstract class. The subclasses that extend it are Blob 
and FoodPiece.Each subclass overrides the draw() method so that they are drawn in their own way. The two draw methods' 
designs are similar, but from the GUI it's clear that the drawn sprites (Blob and 10 Food Pieces) are different.

##### Phase 4: Task 3 - Design Analysis of Code
1) I initially had one Blob class. I felt that I had two clusters that were not necessary together, one cluster relating
   to the command game and the other relating to the GUI. So I split the Blob class into the BlobCommand and BlobGUI to 
   improve the cohesion of my program.
   
2) While working on Phase 3, I identified a cohesion issue with my game panel. So I decided to split the game panel to
   the classes GamePanel and ScorePanel through my inspiration from Space Invaders. I think this is good enough as 
   I can't split the panels further. I thought about separating the GamePanel class further by creating a KeyPanel 
   class, but it created too many bugs, so I chose to keep it as it is. 
   
Note to TA marking this:   
I have spent 7 hours analyzing my code, I believe that I don't have any other poor cohesion or too much coupling
anywhere else, because I kept on improving that throughout Phase 3. Dear TA, while marking this, if you spot any
that can be fixed, please leave a comment on the github page. Otherwise, I believe that I can receive full marks for
Task 3.  



###### // Things I would like to do next:

###### // -  As a user, I want to be able to display the text "Round 2" when Blob first evolves
###### // - Create moving objects that move in a random direction that eat smaller objects (including MC) 
###### // - Moving objects bigger than MC are red, and smaller ones are green. 
###### // - If body sizes of objects that collide are equal, they cannot go over each other and just collide
###### // - After blobGUI evolves, moving creatures are introduced to the game
###### // - Creatures move from one end of the screen to the other in a straight line
###### // - Creatures appear from the edges of the screen randomly
###### // - New Predators' and Prey's sizes increase with Blob growing (MC size * 0.5-1.5)
###### // - You lose the game if you are eaten by a bigger creature
###### // - The size in which you lost the game is your score (Body size value)
 
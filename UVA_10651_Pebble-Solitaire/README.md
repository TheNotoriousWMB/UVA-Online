#Description

This program reads in the board from a user defined n number of games of Pebble Solitaire, and for each game returns the minimum number of pebbles left on the board after all available moves have been made.

In order to solve this in an effective and efficient manner, this program will utilize a recursive algorithm and dynamic programming.


 The point of Pebble Solitaire is to use existing pebbles on the board and jump their neighbors into empty slots in order to eliminate these neighboring pebbles, until there are no more available moves.
 
 Each recursive call of the solve function will scan the inherited board until it finds a space, and then search the neighboring slots on the board. First two to the left, then the two to the right. If these pairs of neighboring slots both contain pebbles, the furthermost pebble will be inserted in place of the empty slot and the middle slot containing a pebble will be emptied. 
 
 At the beginning of each recursive call, the number of pebbles on the current board will be counted and upon completion of the recursive call, returned to the parent function call. If this received count is less than the currently calculated count forthe existing board, the received count will replace the count native to the current call. This ensures that the minimum number of pebbles left over at the end of a particular game is known by the state of the board from the beginning to the end of that game. 
 
 The count is stored along with the current board state in a HashMap for fast access later. This is where the dynamic programming portion of the program comes into play. It is now unnecessary to re-solve a particular board if its solution is already known. This will save much time, particularly when there are to be numerous games to solve in the same execution of the program. 
 
 ****************************************************************************************************************************************
 Sample run:
 ****************************************************************************************************************************************
 	$ cat input.txt
 		4
 		oo--oooo-ooo
 		oooo-oooo-oo
 		-oooo-oo-oo-
 		oooooooo-oo-
 		
 	$ java main < input.txt
 		2
 		1
 		1
 		2

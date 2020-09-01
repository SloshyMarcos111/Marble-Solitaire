Assignment 4 Edits:

A new abstract class, AbstractMarbleSolitaire that implements the MarbleSolitaireModel, was made to allow for the new 
TriangleSolitaireModelImpl and EuropeanSolitaireModelImpl to extend from. The older MarbleSolitaireModelImpl now also extends it.

The common features for each implementation was moved to the abstract class and the implementations now only hold the constructors specified in the 
assignments.

Edits to old methods:

-The setBoard method now takes into account the type of board being set and sets it accordingly with logic that determines what the 
object is an instance of. Based on this, a helper method (one of the three added: setBoardDefault, setBoardEuro, and setBoardTriangle) is called
and appropriately returns the board to the setBoard method.

-The copy constructor from assignment 3 was removed and replaced with a method that copies the board of an object instead.

-getMarkAt method now uses the new copy method instead of a copy constructor that simplifies the copying.

-setBlank method logic simplified to determine the right dimension to base logic on (armThickness for triangular or armthickness * 2 + armthicknes - 2
for a European or English board. 

-move method changed to accommodate the triangle movement. The method has been broken up into two helpers that are called based on the instance of the 
object. The added methods are moveTriangle and moveDefaultOrEuro and they take the same arguments as the move method (fromRow, fromCol, toRow, toCol).

-determineDim method was added to determine the proper dimension that is used in logic based on the instance of the object. If the object is triangula, then
the dimension is returned as the armthicknes. If else, then the dimension is as in previous assignments. (The dimension in both cases is the length and height
of the board 2D array)

-isGameOver method has been split into 2 helper methods. One for the triangle board and the other for the European or English boards. The helpers are called 
based on the insance of the onject. Logic was not modified for the European and English boards, which is the same for both, but the new method for the 
triangle board is new.

-getInplayCount was added to help the getGameState method specifically for the triangle board. This takes in a row of the triangular board and returns the
count of the the INPLAY "O" marbles in the row.

-getGameState was modified to used a different mapping for the triangular board. This shifts the rows based on the getInplayCount of the row and allows
for a pyramid representation of the board.
European and English are unmodified.
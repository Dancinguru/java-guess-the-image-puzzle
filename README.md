# java-guess-the-image-puzzle
This is the use case of this puzzle.
## brief description:

All the pieces of our puzzle have four sides. Each one of the sides is represented by a positive
integer. These numbers represent shapes. The number zero represents the border, which is a special
side. For example, on these pieces, you can see sides 0 (border), 1, 2 and 3. Side 1 fits with side 1,
side 2 would fit with side 2, etc. On the last images you can see how 3 pieces could fit together

<img src="./1.png" alt="result image"/>


# result

Even though the sides 1 and 2 look similar, they are actually mirrored and that’s why they have a
different number. There could be more than 2 pieces with the same side. That means that those
pieces can fit together. The sides of the pieces are named sequentially, clockwise, starting with the
left side. For example, the first piece is 0 1 2 0.
The puzzle file is defined on a text file. A puzzle has a width and a height, which are defined on the
first row of the file; then, line by line represents each one of the pieces. For example, this is a 16-
piece puzzle:
4 4
1 4 3 5
0 5 3 5
1 5 3 0
5 4 5 2
1 5 0 0
0 5 2 1
1 0 4 4
2 4 4 2
....

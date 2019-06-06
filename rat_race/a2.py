# Do not import any modules. If you do, the tester may reject your submission.

# Constants for the contents of the maze.

# The visual representation of a wall.
WALL = '#'

# The visual representation of a hallway.
HALL = '.'

# The visual representation of a brussels sprout.
SPROUT = '@'

# Constants for the directions. Use these to make Rats move.

# The left direction.
LEFT = -1

# The right direction.
RIGHT = 1

# No change in direction.
NO_CHANGE = 0

# The up direction.
UP = -1

# The down direction.
DOWN = 1

# The letters for rat_1 and rat_2 in the maze.
RAT_1_CHAR = 'J'
RAT_2_CHAR = 'P'


class Rat:
    """ A rat caught in a maze. """

    # Write your Rat methods here.
    def __init__ (self, symbol, row, col):
        self.symbol = symbol
        self.row = row
        self.col = col
        self.num_sprouts_eaten = 0

    def set_location (self, row, col):
        self.row = row
        self.col = col

    def eat_sprout (self):
        self.num_sprouts_eaten += 1

    def __str__ (self):
        return self.symbol+" at " + "(" + str(self.row) +", "+ str(self.col) + \
        ")" + " ate " + str(self.num_sprouts_eaten) + " sprouts."

class Maze:
    """ A 2D maze. """

    # Write your Maze methods here.
    def __init__ (self, maze, rat1, rat2):
        self.maze = maze
        self.rat_1 = rat1
        self.rat_2 = rat2
        self.num_sprouts_left = 0
        for i in range(len(maze)):
            for j in range(len(maze[0])):
                if maze[i][j] == SPROUT:
                    self.num_sprouts_left += 1

    def is_wall (self, row, col):
        return self.maze[row][col] == WALL

    def get_character (self, row, col):
        if self.rat_1.row == row and self.rat_1.col == col:
            return self.rat_1.symbol
        if self.rat_2.row == row and self.rat_2.col == col:
            return self.rat_2.symbol
        return self.maze[row][col]

    def move (self, rat, horizontal, vertical):
        new_row = rat.row + vertical
        new_col = rat.col + horizontal
        if not self.is_wall(self.maze[new_row][new_col]):
            rat.set_location(new_row, new_col)
            if self.maze[new_row][new_col] == SPROUT:
                rat.eat_sprout()
                self.maze[new_row][new_col] == HALL
                self.num_sprouts_left += -1
            return True
        return False

    def __str__ (self):
        return self.rat_1.__str__() + self.rat_2.__str__()
        return '\n'.join(''.join(row) for row in self.maze(len(self.maze),len
        (self.maze[0]))) + self.rat_1.__str__() + self.rat_2.__str__()

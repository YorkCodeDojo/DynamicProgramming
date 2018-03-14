'''
Created on 13 Apr 2016

@author: Andy
'''

from collections import namedtuple
from util.helper import accumulate

HOME = 0
RIGHT = 1
DOWN = 2

CellScore = namedtuple('CellScore',['score', 'direction'])

def backtrack(matrix, scores):
    """
    """
    y = len(scores)-1
    x = len(scores[0])-1
    yield matrix[y][x]
    while scores[y][x].direction > 0:
        direction = scores[y][x].direction
        if direction == RIGHT:
            x -= 1
        if direction == DOWN:
            y -= 1
        yield matrix[y][x]

def findOptimalPath(matrix):
    '''
    Find the optimum path through a matrix
    It returns the score of the best possible path followed by the elements in the path.
    '''
    # Accumulator Function which creates the Top Row in the Score Matrix
    topRow = lambda iterable : accumulate(iterable,
                                          lambda x : CellScore(x, HOME), 
                                          lambda x, previous: CellScore(previous.score + x, RIGHT))
    
    # Accumulator Function to create the subsequent rows in the Score Matrix
    # 
    nextRow = lambda iterable, prevIterator : accumulate(iterable, 
                                            lambda x : CellScore(x + next(prevIterator).score, DOWN),
                                            lambda x, previous :
                                                    min([CellScore(previous.score + x, RIGHT),
                                                         CellScore(next(prevIterator).score + x,DOWN)]))
    
    scores = list(accumulate(matrix, lambda x : list(topRow(x)),
                                lambda x, previous : list(nextRow(x,iter(previous)))))

    # Collect the 
    path = list(backtrack(matrix, scores))[::-1]
    
    #Best Score is held in the last cell in the Scores matrix
    return scores[-1][-1].score, path


if __name__ == '__main__':
    score, path = findOptimalPath([[1,7,9,2],[8,6,3,2],[1,6,7,8],[2,9,8,2]])
    print "Score: {0}   Path: {1}".format(score, ' -> '.join(map(str, path)))


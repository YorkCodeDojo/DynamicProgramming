'''
Created on 18 Apr 2016

@author: Andy
'''
from collections import namedtuple
from operator import concat

MileScore = namedtuple('MileScore', ['score','placements'])

def calculatePlacements(potentialLocations,distance,minSeparation=5):
    """
    Calculate the placements of billboards which will give the maximum possible
    revenue.
    """
    scores = [MileScore(0,[])]*(minSeparation+1)
    for x in xrange(1,distance+1):
        if x in potentialLocations:
            firstScore = scores[0]
            scores.append(max([scores[-1], 
                               MileScore(firstScore.score+potentialLocations.get(x),
                                          concat(firstScore.placements,[x]))]))
        else:
            scores.append(scores[-1])
        scores = scores[1:]
    return scores[-1]

if __name__ == '__main__':
    print calculatePlacements({6:5, 7:6, 12:5, 13:3, 14:1}, 20, 5)
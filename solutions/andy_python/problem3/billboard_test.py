'''
Created on 18 Apr 2016

@author: Andy
'''
import unittest
from billboard import calculatePlacements

class TestBillboardPlacements(unittest.TestCase):

    def testPlacement1(self):
        optimumScore = calculatePlacements({6:5, 7:6, 12:5, 13:3, 14:1}, 20, 5)
        self.assertEqual(10, optimumScore.score)
        self.assertEqual([6,12],optimumScore.placements)

    def testPlacement2(self):
        optimumScore = calculatePlacements({6:5, 7:6, 12:5, 13:3, 14:1}, 20, 4)
        self.assertEqual(11, optimumScore.score)
        self.assertEqual([7,12],optimumScore.placements)


    def testPlacement3(self):
        optimumScore = calculatePlacements({6:4, 7:11, 10:8, 12:5, 13:3, 14:5, 
                                            17:9, 19:5, 22:4, 23:5, 26:3, 28:3, 29:4, 30:7}, 30, 4)
        self.assertEqual(37, optimumScore.score)
        self.assertEqual([7,12,17,23,30],optimumScore.placements)

    def testPlacement4(self):
        optimumScore = calculatePlacements({1:3, 3:6, 6:4, 7:11, 10:8, 12:5, 13:3, 14:5, 
                                            17:9, 19:5, 22:4, 23:5, 26:3, 28:3, 29:4, 30:7}, 30, 5)
        self.assertEqual(35, optimumScore.score)
        self.assertEqual([3,10,17,23,30],optimumScore.placements)

    

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
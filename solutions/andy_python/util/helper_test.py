'''
Created on 15 Apr 2016

@author: Andy
'''
import unittest
from helper import accumulate
import operator


class AccumulateTest(unittest.TestCase):

    def testDefaultFunction(self):
        """
        Apply default function which adds each element to the sum of its 
        predecessors
        """
        result = list(accumulate(xrange(5)))
        self.assertEqual([0,1,3,6,10], result)
        
    def testMultiplication(self):
        """
        Apply multiplier operator to successive elements
        """
        result = list(accumulate(xrange(1,5),
                                 subsequentFn=operator.mul))
        self.assertEqual([1,2,6,24], result)

    def testMultiplicationWithOffset(self):
        result = list(accumulate(xrange(1,5),
                                 firstFn=lambda x: x+2,
                                 subsequentFn=operator.mul))
        self.assertEqual([3,6,18,72], result)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
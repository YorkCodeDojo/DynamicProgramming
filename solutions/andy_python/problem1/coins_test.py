'''
Created on 12 Apr 2016

@author: Andy
'''
import unittest

from coins import makeChange

class Test(unittest.TestCase):

    def testBritishCoins(self):
        coins = [1,2,5,10,20,50]
        numberOfCoins, change = makeChange(1, coins)
        self.assertEqual(1, numberOfCoins)
        self.assertEqual([1], change)
        numberOfCoins, change = makeChange(3, coins)
        self.assertEqual(2, numberOfCoins)
        self.assertEqual([2,1], change)
        numberOfCoins, change = makeChange(5, coins)
        self.assertEqual(1, numberOfCoins)
        self.assertEqual([5], change)
        numberOfCoins, change = makeChange(9, coins)
        self.assertEqual(3, numberOfCoins)
        self.assertEqual([5,2,2], change)
        numberOfCoins, change = makeChange(11, coins)
        self.assertEqual(2, numberOfCoins)
        self.assertEqual([10,1], change)
        numberOfCoins, change = makeChange(23, coins)
        self.assertEqual(3, numberOfCoins)
        self.assertEqual([20,2,1], change)
        numberOfCoins, change = makeChange(49, coins)
        self.assertEqual(5, numberOfCoins)
        self.assertEqual([20,20,5,2,2], change)
        numberOfCoins, change = makeChange(87, coins)
        self.assertEqual(5, numberOfCoins)
        self.assertEqual([50,20,10,5,2], change)

    def testLilliputianCoins(self):
        coins = [1,4,15,20,50]
        numberOfCoins, change = makeChange(3, coins)
        self.assertEqual(3, numberOfCoins)
        self.assertEqual([1,1,1], change)
        numberOfCoins, change = makeChange(10, coins)
        self.assertEqual(4, numberOfCoins)
        self.assertEqual([4,4,1,1], change)
        numberOfCoins, change = makeChange(23, coins)
        self.assertEqual(3, numberOfCoins)
        self.assertEqual([15,4,4], change)
        numberOfCoins, change = makeChange(32, coins)
        self.assertEqual(4, numberOfCoins)
        self.assertEqual([15,15,1,1,], change)

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testBritishCoins']
    unittest.main()
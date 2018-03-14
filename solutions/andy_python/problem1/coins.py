'''
Created on 11 Apr 2016

@author: Andy
'''

def makeChange(value, coins):
    """
    Calculate the minimum number of coins required to generate a given value.
    
    Params:
    ------
    value: The target value
    coins: A list of integers which contains the face values of the coins.
    
    Returns:
    A Tuple containing the number and list of the coins used.
    """
    def addBestStep(previousSteps, target):
        """
        Find the best individual step to a target value.
        
        previousSteps is a list of all of the best final steps to the values lower than the target value.
        
        This iterates through all of the coins whose face value is less than or equal to the target value and 
        selects the coin which has the lowest number of steps so far.
        
        Append a Tuple which contains the count of the number of steps and the face value of the coin used
        to make this additional step.
        """
        previousSteps.append(min([(previousSteps[target-coin][0] + 1, coin) for coin in coins if coin <= target]))
        return previousSteps

    def backtrack(steps):
        """
        List all of the coins used to reach the final target value.
        Start at the end of the list of steps.
        Find the coin used to make that step and then move the pointer back by the
        value of the coin to find the previous step in the chain.
        Keep going back until the first step has been reached.
        """
        ptr = len(steps)-1
        while ptr > 0:
            coin = steps[ptr][1] 
            ptr -= coin
            yield coin
    steps = reduce(lambda previousSteps,target: addBestStep(previousSteps,target), xrange(1,value+1), [(0,None)])
    return steps[-1][0], sorted(list(backtrack(steps)), reverse=True)
    

if __name__ == '__main__':
    noOfCoins, coinsUsed = makeChange(71, [1,4,15,20,50])
    print 'No of Coins Used: {0}'.format(noOfCoins)
    print ','.join(map(str,coinsUsed))
    
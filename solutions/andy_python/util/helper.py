from operator import add
def accumulate(iterable, firstFn=lambda x:x, subsequentFn=add):
    """
    
    """
    it = iter(iterable)
    try:
        previous = firstFn(next(it))
    except StopIteration:
        return
    yield previous
    for item in it:
        previous = subsequentFn(item, previous)
        yield previous
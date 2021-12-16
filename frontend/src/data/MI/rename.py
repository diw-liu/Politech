import os
import sys


def main():
    dir = sys.argv[1]
    i = 1
    for file in os.listdir(dir):
        os.rename(os.path.join(dir, file), os.path.join(dir, "26PL{}.json".format(i)))
        i += 1
    
if __name__ == "__main__":
    main()
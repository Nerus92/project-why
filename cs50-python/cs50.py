from helpers.user_input import *
from initials_extractor import InitialsExtractor

if __name__ == '__main__':
    name = get_user_name()
    print "Hello,", InitialsExtractor.extract_initials(name)

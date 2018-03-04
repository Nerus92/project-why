def get_user_name():
    return str(raw_input("Name: "))


def extract_initials(full_name):
    initials = ""
    for (index, character) in enumerate(full_name):
        if character != ' ' and (index == 0 or (index > 0 and full_name[index - 1] == ' ')):
            initials += str(character).capitalize()
    return initials

if __name__ == '__main__':
    name = get_user_name()
    print "Hello,", extract_initials(name)

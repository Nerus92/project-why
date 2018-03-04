class InitialsExtractor:

    def __init__(self):
        pass

    @staticmethod
    def extract_initials(full_name):
        initials = ""
        for (index, character) in enumerate(full_name):
            if character != ' ' and (index == 0 or (index > 0 and full_name[index - 1] == ' ')):
                initials += str(character).capitalize()
        return initials

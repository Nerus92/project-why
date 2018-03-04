from unittest import TestCase
from initials_extractor import InitialsExtractor


class TestInitialsExtractor(TestCase):

    def test_normal_input(self):
        self.assertEqual("N", InitialsExtractor.extract_initials("Nerus"))

    def test_dual_words_input(self):
        self.assertEqual("NB", InitialsExtractor.extract_initials("Nerus Blatia"))

    def test_dual_lowercase_words_input(self):
        self.assertEqual("NB", InitialsExtractor.extract_initials("nerus blatia"))

    def test_front_spaces_input(self):
        self.assertEqual("NB", InitialsExtractor.extract_initials("   Nerus Blatia"))

    def test_multiple_spaces_input(self):
        self.assertEqual("NB", InitialsExtractor.extract_initials("  Nerus   Blatia   "))

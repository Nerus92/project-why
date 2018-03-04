//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <gtest/gtest.h>

extern "C" void extractInitials(char*, const char*);

TEST(initials_tests, test_eq) {
    ASSERT_EQ(1, 1);
}

TEST(initials_tests, test_normal_input) {
    char initials[16];
    char name[64];
    strcpy(name, "Nerus");
    extractInitials(initials, name);
    ASSERT_STREQ("N", initials);
}
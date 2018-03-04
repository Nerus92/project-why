//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <gtest/gtest.h>
#include "../../constants.h"

extern "C" void extractInitials(char*, const char*);

TEST(initials_tests, test_normal_input) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "Nerus");
    extractInitials(initials, name);
    ASSERT_STREQ("N", initials);
    free(name);
    free(initials);
}

TEST(initials_tests, test_dual_words_input) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "Nerus Blatia");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}

TEST(initials_tests, test_dual_lowercase_words_input) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "nerus blatia");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}

TEST(initials_tests, test_front_spaces_input) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "     Nerus Blatia");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}

TEST(initials_tests, test_multiple_spaces_input) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "   Nerus   Blatia   ");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}
//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <gtest/gtest.h>
#include "../../constants.h"

extern "C" void extractInitials(char*, const char*);

TEST(initialsTests, testNormalInput) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "Nerus");
    extractInitials(initials, name);
    ASSERT_STREQ("N", initials);
    free(name);
    free(initials);
}

TEST(initialsTests, testDualWordsInput) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "Nerus Blatia");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}

TEST(initialsTests, testDualLowercaseWordsInput) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "nerus blatia");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}

TEST(initialsTests, testFrontSpacesInput) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "     Nerus Blatia");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}

TEST(initialsTests, testMulitpleSpacesInput) {
    char *name = (char*) malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = (char*) malloc(sizeof(char) * MAX_INITIALS_SIZE);
    strcpy(name, "   Nerus   Blatia   ");
    extractInitials(initials, name);
    ASSERT_STREQ("NB", initials);
    free(name);
    free(initials);
}
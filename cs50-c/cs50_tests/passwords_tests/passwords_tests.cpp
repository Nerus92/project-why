//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <gtest/gtest.h>

extern "C" int run_password(int argc, char **argv);
extern "C" char* read_file(const char *filepath);
extern "C" void crack_passwords();

TEST(passwordsTests, testReadFromFile) {
    char test_file_content[200] = "caesar:50zPJlUFIYY0o\n"
            "cs50:50gyRGMzn6mi6\n"
            "jharvard:50yoN9fp966dU\n"
            "malan:HA610l/.LeOak\n"
            "nate:50AcIG/VnV3D2\n"
            "rbowden:50q.zrL5e0Sak\n"
            "skroob:50Bpa7n/23iug\n"
            "tmacwilliam:50WZ/Wy2GdA1Y\n"
            "zamyla:50lMLvy/mlPIE\n";
    char* computed_file_content;

    computed_file_content = read_file("/Users/jpchatain/Documents/dev/Perso/project-why/repo/cs50-c/res/passwd");
    ASSERT_STREQ(test_file_content, computed_file_content);
    free(computed_file_content);
}

TEST(passwordsTests, testShouldReturnFailIfNoArgument) {
    ASSERT_NE(0, run_password(0, nullptr));
}

TEST(passwordsTests, testShouldReturnFailIfMoreThanOneArgument) {
    ASSERT_NE(0, run_password(2, nullptr));
}

//TEST(passwordsTests, testOutputScrambling) {
//    char original_content[200];
//    strcpy(original_content, "test String\n");
//
//
////    char* tobetested_content;
////    tobetested_content = (char *) malloc(sizeof(char) * 13);
////    strcpy(tobetested_content, "test String\n");
////
////    ASSERT_STREQ(original_content, tobetested_content);
////    free(tobetested_content);
//
//    char **tobetested_content;
//    *tobetested_content = (char *) malloc(sizeof(char) * 13);
//    strcpy(*tobetested_content, "test String\n");
//
//    ASSERT_STREQ(original_content, *tobetested_content);
//    free(*tobetested_content);
//}
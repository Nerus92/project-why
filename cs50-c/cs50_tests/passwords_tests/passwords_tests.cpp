//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <gtest/gtest.h>

extern "C" int run_password(int argc, char **argv);
extern "C" long read_file(const char *filepath, char **output);
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

    read_file("../../../cs50-c/res/passwd", &computed_file_content);
    ASSERT_STREQ(test_file_content, computed_file_content);
    free(computed_file_content);
}

TEST(passwordsTests, testShouldReturnFailIfNoArgument) {
    ASSERT_NE(0, run_password(0, nullptr));
}

TEST(passwordsTests, testShouldReturnFailIfMoreThanOneArgument) {
    ASSERT_NE(0, run_password(2, nullptr));
}
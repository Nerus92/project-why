//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <gtest/gtest.h>

extern "C" int run_password(int argc, char **argv);
extern "C" long read_file(const char *filepath, char **output);
extern "C" void encrypt_password(char *clear_pwd, char *key, char **encrypted_pwd);
extern "C" void crack_password(char *encrypted_pwd);

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
    char *computed_file_content;

    read_file("../../../cs50-c/res/passwd", &computed_file_content);
    ASSERT_STREQ(test_file_content, computed_file_content);
    free(computed_file_content);
}

TEST(passwordsTests, testShouldReturnFailIfNoArgument) {
    char  arg0[] = "programName";
    char* argv[] = { &arg0[0], NULL };
    int   argc   = (int)(sizeof(argv) / sizeof(argv[0])) - 1;
    ASSERT_NE(0, run_password(argc, &argv[0]));
}

TEST(passwordsTests, testShouldReturnFailIfMoreThanOneArgument) {
    char  arg0[] = "programName";
    char  arg1[] = "testpwd";
    char  arg2[] = "additionalArg";
    char* argv[] = { &arg0[0], &arg1[0], &arg2[0], NULL };
    int   argc   = (int)(sizeof(argv) / sizeof(argv[0])) - 1;
    ASSERT_NE(0, run_password(argc, &argv[0]));
}

//TEST(passwordsTests, testShouldReturnSuccessIfOneArgument) {
//    char  arg0[] = "programName";
//    char  arg1[] = "testpwd";
//    char* argv[] = { &arg0[0], &arg1[0], NULL };
//    int   argc   = (int)(sizeof(argv) / sizeof(argv[0])) - 1;
//    ASSERT_EQ(0, run_password(argc, &argv[0]));
//}

TEST(passwordsTests, testShouldEncryptWithGivenSalt) {
    char *pwd = "NerusBlatia";
    char *key = "aa";
    char *encrypted_pwd;
    encrypt_password(pwd, key, &encrypted_pwd);
    ASSERT_STREQ(encrypted_pwd, "aaElvsibZriXk");
}

TEST(passwordsTests, testCrackPassword) {
    crack_password("aa7/Qve3ZPyvo");
}
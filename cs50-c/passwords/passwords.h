//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#ifndef CS50_PASSWORDS_H
#define CS50_PASSWORDS_H

int run_password(int argc, char **argv);
long read_file(const char *filepath, char **output);
void encrypt_password(char *clear_pwd, char *key, char **encrypted_pwd);
void crack_password(char *encrypted_pwd);

#endif //CS50_PASSWORDS_H

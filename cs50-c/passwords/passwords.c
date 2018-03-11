//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include "passwords.h"

int run_password(int argc, char **argv) {
    if(argc != 1) {
        return 1;
    }
    crack_password(argv[0]);
    return 0;
}

long read_file(const char *filepath, char **output) {
    FILE  *file;
    size_t file_size;

    // Open the file
    file = fopen(filepath, "rb");
    if (file == NULL) {
        printf("Could not open file: %s", filepath);
        return -1L;
    }

    // Seek to the end
    if (fseek(file, 0L, SEEK_END) != 0) {
        printf("Could not seek to the end\n");
        return -1L;
    }

    // Get file size
    file_size = (size_t) ftell(file);
    if (0 > file_size) {
        printf("Error getting file size\n");
        return -1L;
    }

    // Allocate buffer to contain the whole thing
    *output = (char *) malloc(sizeof(char) * (file_size + 1));
    if (NULL == *output) {
        printf("Could not allocate memory buffer\n");
        return -1L;
    }

    // Rewind to beginning of file
    if (fseek(file, 0L, SEEK_SET) != 0) {
        printf("Could not seek to the start\n");
        return -1L;
    }

    file_size = fread(*output, sizeof(char), file_size, file);
    if ( ferror( file ) != 0 ) {
        printf("Error reading file\n");
        free(*output);
    } else {
        (*output)[file_size++] = '\0';
    }

    // Close file
    if (EOF == fclose(file)) {
        printf("Error closing out file");
        free(*output);
        return -1L;
    }

    return (long) file_size;
}

void crack_password(char *encrypted_pwd) {
    char *clear_pwd = malloc(sizeof(char) * 9);

    strcpy(clear_pwd, "TESTPWD");

    printf(clear_pwd);
    printf("\n");
    free(clear_pwd);
}
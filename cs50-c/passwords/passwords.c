//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include <stdio.h>
#include <stdlib.h>
#include "passwords.h"

long read_file(const char *filepath, char **output) {

    *output = malloc(sizeof(char) * 193);

    *output = "caesar:50zPJlUFIYY0o\n"
            "cs50:50gyRGMzn6mi6\n"
            "jharvard:50yoN9fp966dU\n"
            "malan:HA610l/.LeOak\n"
            "nate:50AcIG/VnV3D2\n"
            "rbowden:50q.zrL5e0Sak\n"
            "skroob:50Bpa7n/23iug\n"
            "tmacwilliam:50WZ/Wy2GdA1Y\n"
            "zamyla:50lMLvy/mlPIE\n";

    return (long) 193;

//    FILE  *file;
//    size_t file_size;
//
//    // Open the file
//    file = fopen(filepath, "rb");
//    if (file == NULL) {
//        printf("Could not open file: %s", filepath);
//        return -1L;
//    }
//
//    // Seek to the end
//    if (fseek(file, 0L, SEEK_END) != 0) {
//        printf("Could not seek to the end\n");
//        return -1L;
//    }
//
//    // Get file size
//    file_size = (size_t) ftell(file);
//    if (0 > file_size) {
//        printf("Error getting file size\n");
//        return -1L;
//    }
//
//    // Allocate buffer to contain the whole thing
//    *output = (char *) malloc(sizeof(char) * (file_size + 1));
//    if (NULL == *output) {
//        printf("Could not allocate memory buffer\n");
//        return -1L;
//    }
//
//    // Rewind to beginning of file
//    if (fseek(file, 0L, SEEK_SET) != 0) {
//        printf("Could not seek to the start\n");
//        return -1L;
//    }
//
//    file_size = fread(*output, sizeof(char), file_size, file);
//    if ( ferror( file ) != 0 ) {
//        printf("Error reading file\n");
//        free(*output);
//    } else {
//        (*output)[file_size++] = '\0';
//    }
//
//    // Close file
//    if (EOF == fclose(file)) {
//        printf("Error closing out file");
//        free(*output);
//        return -1L;
//    }
//
//    return (long) file_size;
}

void crack_passwords() {

}
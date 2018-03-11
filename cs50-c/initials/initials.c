//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include "initials.h"
#include "../constants.h"

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

void extractInitials(char *initials, const char *name) {
    int initialsCounter = 0;
    for (int i = 0; i < strlen(name); ++i) {
        if (name[i] != ' ' && (i == 0 || (i > 0 && name[i-1] == ' '))) {
            initials[initialsCounter++] = toupper(name[i]);
        }
    }
    initials[initialsCounter] = '\0';
}

int run_initials() {
    char *name = malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = malloc(sizeof(char) * MAX_INITIALS_SIZE);

    if (name == NULL || initials == NULL) {
        printf("Not enough memory available, exiting.");
        return 1;
    }

    printf("Name: ");
    fgets(name, MAX_NAME_SIZE, stdin);

    extractInitials(initials, name);

    printf("Hello, %s", initials);

    free(name);
    free(initials);

    return 0;
}
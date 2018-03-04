//
// Created by Jean-Philippe Chatain on 3/4/18.
//

#include "initials.h"

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void extractInitials(char *initials, const char *name) {
    int initialsCounter = 0;
    for (int i = 0; i < strlen(name); ++i) {
        if (name[i] != ' ' && (i == 0 || (i > 0 && name[i-1] == ' '))) {
            initials[initialsCounter++] = toupper(name[i]);
        }
    }
    initials[initialsCounter] = '\0';
}
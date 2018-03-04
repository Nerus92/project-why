#include <stdio.h>
#include <stdlib.h>
#include "initials/initials.h"
#include "constants.h"

int main() {
    char *name = malloc(sizeof(char) * MAX_NAME_SIZE);
    char *initials = malloc(sizeof(char) * MAX_INITIALS_SIZE);

    if(name == NULL || initials == NULL) {
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
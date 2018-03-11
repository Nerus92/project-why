project(passwords)

set(HEADER_FILES
        passwords.h
        )

set(SOURCE_FILES
        passwords.c
        )

add_library(passwords STATIC ${SOURCE_FILES} ${HEADER_FILES})
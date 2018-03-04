project(initials)

set(HEADER_FILES
        initials.h
        )

set(SOURCE_FILES
        initials.c
        )

add_library(initials STATIC ${SOURCE_FILES} ${HEADER_FILES})
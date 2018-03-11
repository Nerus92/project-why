cmake_minimum_required(VERSION 3.9)
project(cs50 C)

set(CMAKE_C_STANDARD 99)

set(EXECUTABLE_NAME cs50)

set(SOURCE_FILES
        main.c
        constants.h
        )

add_executable(${EXECUTABLE_NAME} ${SOURCE_FILES})

include_directories(initials)
include_directories(passwords)

add_subdirectory(initials)
add_subdirectory(passwords)
add_subdirectory(cs50_tests)

target_link_libraries(${EXECUTABLE_NAME} initials)
target_link_libraries(${EXECUTABLE_NAME} passwords)
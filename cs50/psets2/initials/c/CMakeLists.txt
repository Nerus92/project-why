cmake_minimum_required(VERSION 3.9)
project(c)

set(CMAKE_CXX_STANDARD 11)

add_executable(c
        cmake-build-debug/cmake_install.cmake
        cmake-build-debug/CMakeCache.txt
        cmake-build-debug/initials
        cmake-build-debug/initials.cbp
        cmake-build-debug/Makefile
        CMakeLists.txt
        main.c)

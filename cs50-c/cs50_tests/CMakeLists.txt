project(cs50_tests)

add_subdirectory(../../gtest/googletest/ lib)

add_executable(runAllTests
        initials_tests/initials_tests.cpp
        further_tests/further_tests.cpp)

target_link_libraries(runAllTests gtest gtest_main)
target_link_libraries(runAllTests initials)
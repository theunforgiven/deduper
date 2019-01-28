# Running

To cleanly compile and run the application you do the following via maven:
`mvn spring-boot:run`
You can then view the expected output on routes
`http://localhost:8080/dedupe.json`
and 
`http://localhost:8080/dedupe`
# Explanation
Build an inverted index of metaphone to user record objects for each field we are deduping on. Then we reiterate the dataset and mark any row where every column matches with a Levenshtein distance of less than six as a duplicate. This should help to prevent typos and is more efficient than comparing every row to every other row in the dataset.

# Notes
Had some oddness with the sample files that i got rid of via converting them with my Editor.
Only deduping on the first name, last name, and email fields but they seemed to work for the data set. it would be easy to add mroe fields in the deduper service.
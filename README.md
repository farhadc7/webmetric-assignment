# assignment's hints
## frameworks and tools:
- Java 17
- Spring boot
- postgres

## firsts question
### upload impression events from json file:

  *curl --location 'localhost:8080/impression/v1/save-file' \
--form 'impression=@"./impression.json"'*

sample input file will be found inside the project


### upload click evenets from json file

 *curl --location 'localhost:8080/click/v1/save-file' \
--form 'click=@"./click.json"'*

sample input file will be found inside the project.

to upload click events we will be needed impression id which already exists in database.

## Second question
### to get performance statistics we can use following api:

 *curl --location 'localhost:8080/stat/v1/app-country-performance'*


## third question:
### to get 5 top advertiser based on app_id and country_code we can use following api:
 *curl --location 'localhost:8080/stat/v1/top-advertisers'*



## Improvement ideas:
    - using swagger to expose APIs
    - perform more generic patterns in the structure.
    - exception handling
    - separating statistic services in another module or using micro-service.
    - Improve the performance of third questions by using multi-thread.
    - For the third question I used java streams to solve it .ofcourse using tools like those mentioned in the question will be more efficient but learning and using them need some adequate time.

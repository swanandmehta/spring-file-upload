# spring-file-upload

### Assumption

1. I assumed that Uploading File was processed just once and we do not consider the failed or broken attepts to upload the file
2. I have assumed that inserted records are less then 10000 per file although there isnt any code which prevents for uploading more data but i would have used differnt approch
3. I do not know if given data is similer to prod so all columns except Id are marked as nullable 
4. I assumed that field "fromDate" will contain "dd-MM-yyyy" and server has TZ as UTC

### Things i wanted to do but ran out of the time 

1. I could not complete unit test cases I covered part of upload endpoint but sadly ran out of time,
2. Normalizing table should have been priorty since we have duplicate values in some of the field.
3. I would like to decouple the logic between uploading and processing,
4. I would like to process each file in paraller
5. I understand that field "code" is unique and can be used as 
    primary key however that also mean primary key with b-tree index would be created 
    I could not make a decision if that was a good choice since i was not aware if application is read heavy or write heavy
    more context would be need to make that decision
6. I would like to create Advice to handle the application errors to give more proper response to user

### Assumption
* I assumed that the Uploading File was processed just once and we do not consider the failed or broken attempts to upload the file
* I have assumed that inserted records are less than 10000 per file although there isn't any code which prevents uploading more data but i would have used different approach
* I do not know if the given data is similar to prod so all columns except Id are marked as nullable
* I assumed that field "fromDate" will contain "dd-MM-yyyy" and server has TZ as UTC

### Things i wanted to do but ran out of the time
* I could not complete unit test cases I covered part of upload endpoint but sadly ran out of time,
* Normalizing table should have been a priorty since we have duplicate values in some of the fields.
* I would like to decouple the logic between uploading and processing,
* I would like to process each file or n row in parallel
* I understand that field "code" is unique and can be used as the primary key however that also mean a primary key with b-tree index would be created I could not decide if   that was a good choice since i was not aware if the application is read-heavy or write-heavy more context would need to make that decision
* I would like to create Advice to handle the application errors to give more proper responses to user

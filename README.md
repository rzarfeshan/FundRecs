# Transaction Service
Transaction Service is a web service that accepts transactions data in JSON format and saves them to file.
Technology Used: SpringBoot and Java

A transaction is uniquely identified by date and type.
The TransactionBean is having 3 member field i.e. Date, type and Amount.
All the valid transaction is saved in the TransactionHistory.txt file. Data in file is saved in sorted ordered by dates.

If a transaction already exists in a file with same date and type, then the existing transaction amount and requested transaction amout is summed up and saved as a single transaction.
This event is logged in a EventHistory.txt file with all the details about the 2 transactions.

Two REST API are designed:
1. GET  - To get the transaction on the basis of Date and Type. (/api/v1/transactions)
Sample Get Request
                {
       "date": "11-12-2019",
       "type": "credit"
                }
Sample Get Response
                {
       "date": "11-12-2019",
       "type": "credit",
       "amount": "98.36"
   }
   
2. POST - This API is used to create the transaction record. (/api/v1/transactions)

Sample Input Request:
[
   {
       "date": "11-12-2018",
       "type": "credit",
       "amount": "9898.36"
   },
   {
       "date": "11-12-2019",
       "type": "credit",
       "amount": "98.36"
   }
]

POST API response tells you if the data is successfully inserted or not.

For efficient retrieval of data on GET request, a in memory cache is built that holds the records. This cache is always updated. Whenever a get request is made, response will be sent from cache instead of reading it from the TransactionHistory.txt file.

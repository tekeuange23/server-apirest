# client-handling-apirest
Freelance Spring-Boot Api to handle Clients management including crud operation, Input validation, Succes and Error messages.


## GET
`retrieve all client` [http://localhost:8080/api/clients](#http://localhost:8080/api/clients) <br/>

## POST
`add new client` [http://localhost:8080/api/clients](#http://localhost:8080/api/clients) <br/>

## PUT
`update an existing client` [http://localhost:8080/api/clients/{clientNumberID}](#http://localhost:8080/api/clients/{clientNumberID}) <br/>


## GET
`Search by ID Number` [http://localhost:8080/api/clients/{clientNumberID}](#http://localhost:8080/api/clients/{clientNumberID}) <br/>

## GET
`Search by Mobile Number` [http://localhost:8080/api/clients/phone/{mobileNumber}](#http://localhost:8080/api/clients/phone/{mobileNumber}) <br/>

## GET
`Search by FirstName` [http://localhost:8080/api/clients/name/{firstName}](#http://localhost:8080/api/clients/name/{firstName}) <br/>
___

### GET http://localhost:8080/api/clients
Get all avaible clients                                                                 |

**Response**

```
// List of clients
[
    {
        "idNumber": 9609144954087,
        "firstName": "tekeu",
        "lastName": "ange",
        "mobileNumber": "111111111111",
        "physicalAddress": "physicalAddress"
    }
]
```
___

### POST http://localhost:8080/api/clients

**Parameters**

|           Name | Required |  Type   | Description                                                                                                           |
| -----------------------:|:--------:|:--------:| ----------------------------------------------------------------------------------------------------------- |
|              `idNumber` | unique   | integer  | `correspond to a valid South African ID number`.                                                             |
|             `firstName` | optional | string   |                                                                                                             |
|              `lastName` | optional | integer  |                                                                                                             |
|          `mobileNumber` | unique   | string   |                                                                                                             |
|       `physicalAddress` | optional | string   |                                                                                                             |

**Response**

```
// success
{
    "client": {
        "idNumber": 9609144954087,
        "firstName": "tekeu",
        "lastName": "ange",
        "mobileNumber": "111111111111",
        "physicalAddress": "physicalAddress"
    },
    "message": "The client has been successfully created!"
}

// error due to the fact the ID Number cannot be duplicated
{
    "message": "Cannot create the user.",
    "error": "This action cannot be performed due to unique constraint on ID Number."
}

// error caused by an invalid ID Number
{
    "message": "Cannot create the user.",
    "error": "The entered ID number is not a valid one."
}

// error due to the fact the Mobile Number cannot be duplicated
{
    "message": "Cannot create the user.",
    "error": "This action cannot be performed due to unique constraint on MOBILE Number."
}
```



### PUT http://localhost:8080/api/clients/{clientNumberID}

**Parameters**

|           Name | Required |  Type   | Description                                                                                                           |
| -----------------------:|:--------:|:--------:| ----------------------------------------------------------------------------------------------------------- |
|        `clientNumberID` | require  | integer  | `ID number retrieved in the URL path which represent the ID of the user to update`.                         |
|              `idNumber` | unique   | integer  | `correspond to a valid South African ID number of the new user`.                                            |
|             `firstName` | optional | string   | `new user`                                                                                                  |
|              `lastName` | optional | integer  | `new user`                                                                                                  |
|          `mobileNumber` | unique   | string   | `new user`                                                                                                  |
|       `physicalAddress` | optional | string   | `new user`                                                                                                  |

**Response**

```
// success
{
    "client": {
        "idNumber": 2001014800086,
        "firstName": "tekeu nana",
        "lastName": "ange",
        "mobileNumber": "6666666666",
        "physicalAddress": "0000000000"
    },
    "message": "The client has been successfully updated!"
}

// error due to the fact the ID Number cannot be duplicated
{
    "message": "Cannot update the user.",
    "error": "This action cannot be performed due to unique constraint on ID Number."
}

// error caused by an invalid ID Number
{
    "message": "Cannot update the user.",
    "error": "The entered ID number is not a valid one."
}

// error due to the fact the Mobile Number cannot be duplicated
{
    "message": "Cannot update the user.",
    "error": "This action cannot be performed due to unique constraint on MOBILE Number."
}
```

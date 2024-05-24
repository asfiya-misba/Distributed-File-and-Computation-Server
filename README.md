# Distributed File and Computation Server

**University of Texas at Arlington - Fall 2022**

This project demonstrates the implementation of file servers and computation servers with both synchronous and asynchronous Remote Procedure Calls (RPCs). The project is divided into four main assignments.

## Assignments

### 1. Single-Threaded File Server
Developed a single-threaded file server with the following functionalities:
- UPLOAD
- DOWNLOAD
- DELETE
- RENAME

### 2. Multi-Threaded File Server
Extended the file server to support multiple simultaneous client requests using multi-threading. The functionalities include:
- UPLOAD
- DOWNLOAD
- DELETE
- RENAME

### 3. Synchronous RPC Computation Server
Implemented a computation server supporting synchronous RPCs for the following operations:
- `calculate_pi()`
- `add(i, j)`
- `sort(array A)`
- `matrix_multiply(matrix A, matrix B, matrix C)`

### 4. Asynchronous RPC Computation Server
Developed a computation server supporting asynchronous and deferred synchronous RPCs for the following operations:
- `calculate_pi()`
- `add(i, j)`
- `sort(array A)`
- `matrix_multiply(matrix A, matrix B, matrix C)`

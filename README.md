# cinemaProject
                                                                        Run project locally
                                      
1.Download from repository

2.Download java 11 and setup it

3.Download and configure PostgresqlDB

4.Configure envirepment variables.
     Run -> edit configurations -> Spring boot -> envirepment variables
     ![image](https://user-images.githubusercontent.com/44407006/190893968-bdbc56f0-7900-4fff-826f-daf876aff019.png)
    ![image](https://user-images.githubusercontent.com/44407006/190893894-924a877f-8c8c-4ecd-babb-3c76b712ce4a.png)
    
5.Run project

                                                                         Endpoints
                                                                         
For convenient use of endpoints, you can use swagger, which is available at the link: http://localhost:8080/swagger-ui/

  Movie:
                                                                            
    POST http://localhost:8080/api/cinema/movie
     
    GET http://localhost:8080/api/cinema/movie/{id}

    GET http://localhost:8080/api/cinema/movie/param

    PUT http://localhost:8080/api/cinema/movie/{id}

    DELETE http://localhost:8080/api/cinema/movie/{id}
    
  Order:
  
     POST http://localhost:8080/api/cinema/order
  
    GET http://localhost:8080/api/cinema/order/{id}
    
    GET http://localhost:8080/api/cinema/order/param
    
    PUT http://localhost:8080/api/cinema/order/{id}
    
    DELETE http://localhost:8080/api/cinema/order{id}


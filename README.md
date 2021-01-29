# InvalidItemPDFReporter

Spring Boot system which Uses ItemStock services to get Invalid Items

# To Access Services
Our server, or Localhost:9090 for this instance, following by "/report/" and service with parameters
http://localhost:9090/report/service?parameters

| Service | Parameters | return type |
| ------- | ---------- | ----------- |
| getReport | LocalDate validUntil | PDF report file |

# Open API methods
with parameters and information could be accessed here after running application
ItemStockApplication main method:

http://localhost:9090/swagger-ui.html
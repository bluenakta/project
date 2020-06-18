package contracts.ticketIssuance

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'PUT'
        url ('/ticketIssuances/canceled/1')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
                bookId: 1,
                issueStatus: "Canceled"
        )
        bodyMatchers {
            jsonPath('$.bookId', byRegex(nonEmpty()).asLong())
            jsonPath('$.issueStatus', byRegex(nonEmpty()).asString())
        }
        headers {
            contentType(applicationJson())
        }
    }
}